import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from 'src/app/services/user-service/user.service';
import { ReportService } from 'src/app/services/report-service/report.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  public type!: string;
  constructor(private service: UserService, private reportService: ReportService) { }

  ngOnInit(): void {
    this.getType();
  }

  getType(){
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    this.type = info.type;
    console.log(info);

  }

  createReport(){
    this.reportService.createReport().subscribe(
      result => {
        console.log('uspesno kreiran izvestaj dodaj toster ');
        
      },
      error => {
          console.log(error);
      }
    );
  }

 
}
