import { Component, OnInit } from '@angular/core';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';

@Component({
  selector: 'app-all-appeal-silence-list',
  templateUrl: './all-appeal-silence-list.component.html',
  styleUrls: ['./all-appeal-silence-list.component.css']
})
export class AllAppealSilenceListComponent implements OnInit {

  public username: string = '';
  public list: Array<any> = new Array;
  public searchInput: string = '';
  public metadataSearch: any = {URI: '', datum: '', korisnicko_ime:'', naziv_poverenika:'', zahtev_uri:''};


  constructor(private appealSilenceService: AppealSilenceService) { }

  ngOnInit(): void {
    this.getAllAppeals();

  }
  getHref(name: string) {
    return 'http://localhost:8082/api/zalba_cutanje/'+name+'/pdf';
  }

  getAllAppeals(){
    const convert = require('xml-js');
    this.appealSilenceService.getAll().subscribe(
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
  search(): void{
    this.list = [];
    const convert = require('xml-js');
    this.appealSilenceService.getSearchRequests(this.searchInput).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item.datumZalbe){
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
      URI: { _text: '' }, datum: { _text: '' }, korisnicko_ime: { _text: '' }, naziv_poverenika: { _text: '' }, zahtev_uri: { _text: '' } } };
    search.root.URI = this.metadataSearch.URI;
    search.root.datum = this.metadataSearch.datum;
    search.root.korisnicko_ime = this.metadataSearch.korisnicko_ime;
    search.root.naziv_poverenika = this.metadataSearch.naziv_poverenika;
    search.root.zahtev_uri = this.metadataSearch.zahtev_uri;
    const convert = require('xml-js');

    const searchXML = convert.js2xml(search, {compact: true, ignoreComment: true, spaces: 4});
    this.list = [];
    this.appealSilenceService.getMetadataSearchRequests(searchXML).subscribe(
      result => {
        const listObject: any = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        if(listObject.List.item == undefined){
          return;
        }
        if(listObject.List.item.datumZalbe){
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
