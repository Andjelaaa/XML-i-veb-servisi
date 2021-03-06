import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  public requestList: RequestDTO[] = []; 
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.getUserRequests();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/zahtev/'+name+'/pdf';
  }

  getUsername(){
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    this.username = info.sub;
    console.log(info);

  }

  getUserRequests(){
    this.getUsername();
    const convert = require('xml-js');
    this.requestService.getUserRequests(this.username).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item.adresa){
          this.list.push(listObject.List.item);
        }else{
          this.list = listObject.List.item;
          this.requestList = this.list as RequestDTO[];
        }
      },
      error => {
          console.log(error);
      }
    );

  }
}
