import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';

@Component({
  selector: 'app-new-appeal-silence-list',
  templateUrl: './new-appeal-silence-list.component.html',
  styleUrls: ['./new-appeal-silence-list.component.css']
})
export class NewAppealSilenceListComponent implements OnInit {
  public username: string = '';
  public list: Array<any> = new Array;

  constructor(private appealSilenceService: AppealSilenceService,
    private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.getNewAppeals();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/zalba_cutanje/'+name+'/pdf';
  }

  getNewAppeals(){
    const convert = require('xml-js');
    this.appealSilenceService.getNew().subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        console.log(listObject);
        if(listObject.List.item.datumZalbe){
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

  sendMail(zalbaId: any)
  {
    this.appealSilenceService.sendMail(zalbaId).subscribe(
      result => {
        this.toastrService.success("Mail poslat");
        console.log("Mail poslat");
      },
      error =>{
        this.toastrService.error("Mail nije poslat");
        console.log("Mail nije poslat");
      })
  }

}
