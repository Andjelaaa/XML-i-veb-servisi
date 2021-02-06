import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { DecisionService } from 'src/app/services/decision-service/decision.service';

@Component({
  selector: 'app-all-decision-list',
  templateUrl: './all-decision-list.component.html',
  styleUrls: ['./all-decision-list.component.css']
})
export class AllDecisionListComponent implements OnInit {

  public list: Array<any> = new Array;
  public searchInput: string = '';



  constructor(private decisionService: DecisionService) { }

  ngOnInit(): void {
    this.getAllDecision();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/resenje/'+name+'/pdf';
  }


  getAllDecision(){
    const convert = require('xml-js');
    this.decisionService.getAll().subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(listObject);
        if(listObject.List.item.naziv){
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
  search(): void{
    this.list = [];
    const convert = require('xml-js');
    this.decisionService.getSearchRequests(this.searchInput).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item.naziv){
          this.list.push(listObject.List.item);
        }else{
          this.list = listObject.List.item;
        }
        
      },
      error => {
          console.log(error);
      }
    );
  }

}
