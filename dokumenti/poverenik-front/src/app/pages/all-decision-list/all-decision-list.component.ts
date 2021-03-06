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
  public metadataSearch: any = {URI: '', datum: '', korisnicko_ime:'', zalba_odluke_uri:'', zalba_cutanje_uri:'', brojResenja:''};



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

  searchMetadata(): void{
    const search = { _declaration:
      { _attributes: { version: '1.0', encoding: 'utf-8' } },
    root: {
      URI: { _text: '' }, datum: { _text: '' }, korisnicko_ime: { _text: '' }, zalba_odluke_uri: { _text: '' }, zalba_cutanje_uri: { _text: '' }, brojResenja: { _text: '' }  } };
    search.root.URI = this.metadataSearch.URI;
    search.root.datum = this.metadataSearch.datum;
    search.root.korisnicko_ime = this.metadataSearch.korisnicko_ime;
    search.root.zalba_odluke_uri = this.metadataSearch.zalba_odluke_uri;
    search.root.zalba_cutanje_uri = this.metadataSearch.zalba_cutanje_uri;
    search.root.brojResenja = this.metadataSearch.brojResenja;
    const convert = require('xml-js');

    const searchXML = convert.js2xml(search, {compact: true, ignoreComment: true, spaces: 4});
    this.list = [];
    this.decisionService.getMetadataSearchRequests(searchXML).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item == undefined){
          return;
        }
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
