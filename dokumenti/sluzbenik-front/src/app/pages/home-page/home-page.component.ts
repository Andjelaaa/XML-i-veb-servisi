import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private service: UserService) { }

  ngOnInit(): void {
  }
  soap():void{
    this.service.soap().subscribe((res) => {
        console.log(res);
    },
    err =>{
        console.log(err);
    });
  }
}
