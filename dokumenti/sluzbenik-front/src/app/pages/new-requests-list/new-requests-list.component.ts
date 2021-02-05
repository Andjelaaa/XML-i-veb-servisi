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
    this.getNewRequests();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/zahtev/'+name+'/pdf';
  }

  getNewRequests(){
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

  denyRequest(zahtev: any, i: any) {
    const convert = require('xml-js');
    const zahtevRoot = {zahtevDTO: zahtev};
    const zahtevXML = convert.js2xml(zahtevRoot, {compact: true, ignoreComment: true, spaces: 4});
    console.log(zahtevXML);
    this.requestService.denyRequest(zahtevXML).subscribe(
      result => {
        this.list.splice(i,1);
        console.log("Zahtev uspesno odbijen");
      },
      error => {
          console.log(error);
      }
    );

  }

}
