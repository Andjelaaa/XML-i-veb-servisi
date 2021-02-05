import { Component, OnInit } from '@angular/core';
import { ReportService } from 'src/app/services/report-service/report.service';

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.css']
})
export class ReportListComponent implements OnInit {

  public list: Array<any> = new Array;
 // public searchInput: string = '';
 // public metadataSearch: any = {URI: '', datum: '', korisnicko_ime:'', zalba_odluke_uri:'', zalba_cutanje_uri:'', brojResenja:''};



  constructor(private reportService: ReportService) { }

  ngOnInit(): void {
    this.getAllReports();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/izvestaj/'+name+'/pdf';
  }


  getAllReports(){
    const convert = require('xml-js');
    this.reportService.getAll().subscribe(
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
