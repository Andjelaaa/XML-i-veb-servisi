import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';

@Component({
  selector: 'app-new-requests-list',
  templateUrl: './new-requests-list.component.html',
  styleUrls: ['./new-requests-list.component.css']
})
export class NewRequestsListComponent implements OnInit {

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

  getUserRequests(){
    const convert = require('xml-js');
    this.requestService.getNewRequests().subscribe(
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
