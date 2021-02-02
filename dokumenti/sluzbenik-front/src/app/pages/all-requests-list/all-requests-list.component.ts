import { Component, OnInit } from '@angular/core';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';

@Component({
  selector: 'app-all-requests-list',
  templateUrl: './all-requests-list.component.html',
  styleUrls: ['./all-requests-list.component.css']
})
export class AllRequestsListComponent implements OnInit {

  public requestList: RequestDTO[] = []; 
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.getAllRequests();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/zahtev/'+name+'/pdf';
  }

  getAllRequests(){
    const convert = require('xml-js');
    this.requestService.getAllRequests().subscribe(
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
