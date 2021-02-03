import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { DecisionService } from 'src/app/services/decision-service/decision.service';

@Component({
  selector: 'app-decision-list',
  templateUrl: './decision-list.component.html',
  styleUrls: ['./decision-list.component.css']
})
export class DecisionListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private decisionService: DecisionService) { }

  ngOnInit(): void {
    this.getUserDecision();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/resenje/'+name+'/pdf';
  }
  getUsername(){
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    this.username = info.sub;
    console.log(info);

  }

  getUserDecision(){
    this.getUsername();
    const convert = require('xml-js');
    this.decisionService.getUserDecision(this.username).subscribe(
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
