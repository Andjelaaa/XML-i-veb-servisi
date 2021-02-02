import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

declare const Xonomy: any;

@Injectable({
    providedIn: 'root'
})
export class XonomyAppealDecisionService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    public appealDecisionSpecification = {
      elements: {
        adresaPodnosioca: {},
        sedistePoverenika: {},
        datumZalbe:{},
        naslov:{
            isReadOnly: true
        },
        nazivPoverenika:{},
        datum:{},
        podaciOZahtevuIInformacijama:{}
    }
  };

}