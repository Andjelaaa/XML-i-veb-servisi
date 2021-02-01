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
export class XonomyRequestService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}


    public requestSpecification = {
          elements: {
            root: {
                podnosilac: {
                        title: 'Podnosilac zahteva',
                      },
                      korisnickoIme: {
                        title: 'Unesi korisnicko ime',
                        hasText: true,
                        asker: Xonomy.askString
                      },
                      ime : {

                      },
                       prezime: {
                        attributes: {
                          property: {
                            isInvisible: true
                          }
                        },
                        hasText: true,
                        asker: Xonomy.askString
                      },
                    }
                },
                adresa: {
                    ulica : {
                        attributes: {
                          property: {
                            isInvisible: true
                          }
                        },
                        hasText: true,
                        asker: Xonomy.askString
                      },
                       broj: {
                        attributes: {
                          property: {
                            isInvisible: true,
                          }
                        },
                        hasText: true,
                        asker: Xonomy.askString
                      },
                      grad: {
                        attributes: {
                          property: {
                            isInvisible: true,
                          }
                        },
                        hasText: true,
                        asker: Xonomy.askString
                      },
                    },
                   drugiPodaciZaKontakt: {},
                   nazivOrganaVlasti: {},
                   sedisteOrgana: {},
                   izbori: {},
                   trazeneInformacije: {},
                   datum: {},
                   mesto: {},
                   fusnote: {
                   isInvisible: false,
                 }
    };
}
