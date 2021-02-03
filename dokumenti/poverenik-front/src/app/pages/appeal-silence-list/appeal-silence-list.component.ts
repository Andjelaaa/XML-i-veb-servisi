import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';

@Component({
  selector: 'app-appeal-silence-list',
  templateUrl: './appeal-silence-list.component.html',
  styleUrls: ['./appeal-silence-list.component.css']
})
export class AppealSilenceListComponent implements OnInit {

  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private appealSilenceService: AppealSilenceService) { }

  ngOnInit(): void {
    this.getUserAppeals();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/zalba_cutanje/'+name+'/pdf';
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
    this.appealSilenceService.getUserAppealSilence(this.username).subscribe(
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
