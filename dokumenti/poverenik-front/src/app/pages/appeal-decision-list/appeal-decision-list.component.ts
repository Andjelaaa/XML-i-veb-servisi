import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppealDecisionService } from 'src/app/services/appeal-decision-service/appeal-decision.service';

@Component({
  selector: 'app-appeal-decision-list',
  templateUrl: './appeal-decision-list.component.html',
  styleUrls: ['./appeal-decision-list.component.css']
})
export class AppealDecisionListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private appealDecisionService: AppealDecisionService) { }

  ngOnInit(): void {
    this.getUserAppeals();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/zalba_odluke/'+name+'/pdf';
  }
  getUsername(){
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    this.username = info.sub;
    console.log(info);

  }

  getUserAppeals(){
    this.getUsername();
    const convert = require('xml-js');
    this.appealDecisionService.getUserAppealDecision(this.username).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(listObject);
        if(listObject.List.item.datum){
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
