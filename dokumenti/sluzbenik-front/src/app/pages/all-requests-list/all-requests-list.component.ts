import { Component, OnInit } from '@angular/core';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';

@Component({
  selector: 'app-all-requests-list',
  templateUrl: './all-requests-list.component.html',
  styleUrls: ['./all-requests-list.component.css']
})
export class AllRequestsListComponent implements OnInit {

  public requestList: RequestDTO[] = []; 
  public username: string = '';
  public list: Array<any> = new Array;
  public searchInput: string = '';
  public metadataSearch: any = {URI: '', datum: '', korisnicko_ime:'', naziv_organa_vlasti:''};

  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.getAllRequests();

  }
  getHref(name: string) {
    return 'http://localhost:8081/api/zahtev/'+name+'/pdf';
  }

  getAllRequests(){
    this.list = [];
    const convert = require('xml-js');
    this.requestService.getAllRequests().subscribe(
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

  search(): void{
    this.list = [];
    const convert = require('xml-js');
    this.requestService.getSearchRequests(this.searchInput).subscribe(
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

  searchMetadata(): void{
    const search = { _declaration:
      { _attributes: { version: '1.0', encoding: 'utf-8' } },
    root: {
      URI: { _text: '' }, datum: { _text: '' }, korisnicko_ime: { _text: '' }, naziv_organa_vlasti: { _text: '' } } };
    search.root.URI = this.metadataSearch.URI;
    search.root.datum = this.metadataSearch.datum;
    search.root.korisnicko_ime = this.metadataSearch.korisnicko_ime;
    search.root.naziv_organa_vlasti = this.metadataSearch.naziv_organa_vlasti;
    const convert = require('xml-js');

    const searchXML = convert.js2xml(search, {compact: true, ignoreComment: true, spaces: 4});
    this.list = [];
    this.requestService.getMetadataSearchRequests(searchXML).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item == undefined){
          return;
        }
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
}
