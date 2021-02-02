import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

declare const Xonomy: any;

@Injectable({
    providedIn: 'root'
})
export class XonomyInformationService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    public informationSpecification = {
      elements: {
        zahtevURI: {
          isReadOnly : true
        },
        adresa: {},
        ulica : {},
        broj: {},
        grad: {},
        brojPredmeta: {},
        datum: {},
        nazivOrganaVlasti: {},
        sedisteOrgana: {},
        podnosilac: {
          title: 'Podnosilac zahteva',
        },
        korisnickoIme: {
          title: 'Unesi korisnicko ime',
        },
        ime : {},
        prezime: {}
  
      }
    };
}
