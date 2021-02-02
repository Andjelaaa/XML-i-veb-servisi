import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { InformationService } from 'src/app/services/information-service/information.service';

@Component({
  selector: 'app-information-list',
  templateUrl: './information-list.component.html',
  styleUrls: ['./information-list.component.css']
})
export class InformationListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private informationService: InformationService) { }

  ngOnInit(): void {
    this.getUserInformations();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/obavestenje/'+name+'/pdf';
  }
  getUsername(){
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    this.username = info.sub;
    console.log(info);

  }

  getUserInformations(){
    this.getUsername();
    const convert = require('xml-js');
    this.informationService.getUserInformations(this.username).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item.adresa){
          this.list.push(listObject.List.item);
        }else{
          this.list = listObject.List.item;
        }
        console.log(this.list);
        
      },
      error => {
          console.log(error);
      }
    );

  }
}
