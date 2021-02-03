import { Component, OnInit } from '@angular/core';
import { AppealDecisionService } from 'src/app/services/appeal-decision-service/appeal-decision.service';

@Component({
  selector: 'app-new-appeal-decision-list',
  templateUrl: './new-appeal-decision-list.component.html',
  styleUrls: ['./new-appeal-decision-list.component.css']
})
export class NewAppealDecisionListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private appealDecisionService: AppealDecisionService) { }

  ngOnInit(): void {
    this.getAllAppeals();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/zalba_odluke/'+name+'/pdf';
  }

  getAllAppeals(){
    const convert = require('xml-js');
    this.appealDecisionService.getNew().subscribe(
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
