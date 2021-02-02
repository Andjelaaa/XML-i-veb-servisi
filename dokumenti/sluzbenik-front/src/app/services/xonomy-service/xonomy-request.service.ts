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
        podnosilac: {
          title: 'Podnosilac zahteva',
        },
        korisnickoIme: {
          title: 'Unesi korisnicko ime',
          hasText: true,
          asker: Xonomy.askString
        },
        ime : {},
        prezime: {
          attributes: {
          property: {
          isInvisible: true
          }
        },
        hasText: true,
        asker: Xonomy.askString
        },

        adresa: {},
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
        drugiPodaciZaKontakt: {},
        nazivOrganaVlasti: {},
        sedisteOrgana: {},
        a: {
          isReadOnly: true,
        },
        b: {
          isReadOnly: true,
        },
        c: {
          isReadOnly: true,
        },
        d: {
          isReadOnly: true,
        },
        podizbori: {
          menu: [
            {
              caption: 'Obrisite izbor',
              action: Xonomy.deleteElement
            },
            {
              caption: 'Postom',
              action: Xonomy.newElementChild,
              actionParameter: '<a>postom</a>',
            },
            {
              caption: 'Elektronskom postom',
              action: Xonomy.newElementChild,
              actionParameter: '<b>elektronskom postom</b>',
            },
            {
              caption: 'Faksom',
              action: Xonomy.newElementChild,
              actionParameter: '<c>faksom</c>',
            },
            {
              caption: 'Na neki drugi nacin',
              action: Xonomy.newElementChild,
              actionParameter: '<d>na neki drugi nacin</d>',
            }
          ]
        },
        izbor: {
          hasText: false,
          oneliner: true,
          menu: [
          {
            caption: 'Obrisite izbor',
            action: Xonomy.deleteElement
          },
          {
            hideIf: function (jsElement : any) {
              console.log(jsElement.children[0].children[0].value);
              return jsElement.children[0].children[0].value !== "dostavljanje kopije dokumenta koji sadrži traženu informaciju";
            },
            caption: 'Dodajte podizbor',
            action: Xonomy.newElementChild,
            actionParameter: '<podizbori></podizbori>',
          },
          

          ]
        },
        tekst: {
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["obaveštenje da li poseduje traženu informaciju", "uvid u dokument koji sadrži traženu informaciju", "kopiju dokumenta koji sadrži traženu informaciju", "dostavljanje kopije dokumenta koji sadrži traženu informaciju"]
        },
        trazeneInformacije: {},
        datum: {},
        mesto: {},
        fusnote: {
          isInvisible: true,
        },
        izbori: {
          title: 'Kliknite da biste dodali zahtev',
          menu: [
          {
            caption: 'Dodajte izbor',
            action: Xonomy.newElementChild,
            actionParameter: '<izbor><tekst>Izaberite opciju</tekst></izbor>',
          },
        ]
        },
        naslov: {
          isReadOnly: true,
        }
    }
  };

}
