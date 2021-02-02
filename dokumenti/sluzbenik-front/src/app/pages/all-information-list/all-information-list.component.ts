import { Component, OnInit } from '@angular/core';
import { InformationService } from 'src/app/services/information-service/information.service';

@Component({
  selector: 'app-all-information-list',
  templateUrl: './all-information-list.component.html',
  styleUrls: ['./all-information-list.component.css']
})
export class AllInformationListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private informationService: InformationService) { }

  ngOnInit(): void {
    this.getAllInformations();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/obavestenje/'+name+'/pdf';
  }

  getAllInformations(){
    const convert = require('xml-js');
    this.informationService.getAllInformations().subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item.adresa){
          this.list.push(listObject.List.item);
        }else{
          this.list = listObject.List.item;
        }
        console.log(listObject);
      },
      error => {
          console.log(error);
      }
    );

  }
}
