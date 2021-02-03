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

  constructor(private decisionService: DecisionService) { }

  ngOnInit(): void {
    this.getAllDecision();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/resenje/'+name+'/pdf';
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

}
