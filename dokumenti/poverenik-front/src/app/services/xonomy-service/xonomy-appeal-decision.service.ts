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
        validate: function (jsElement: any) {
            let elementSpec = this.elements[jsElement.name];
            if (elementSpec.validate) elementSpec.validate(jsElement);
            for (let i = 0; i < jsElement.attributes.length; i++) {
              let jsAttribute = jsElement.attributes[i];
              let attributeSpec = elementSpec.attributes[jsAttribute.name];
              if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
            }
            for (let i = 0; i < jsElement.children.length; i++) {
              let jsChild = jsElement.children[i];
              if (jsChild.type == 'element') {
                this.validate(jsChild); 
              }
            }
          },

      elements: {
        zahtevURI: {
            title: "Unesite URI zahteva za koji pisete zalbu",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'URI zahteva je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        adresaPodnosioca: {},
        broj: {
            title: "Unesite ulicni broj",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Broj je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        grad: {
            title: "Unesite naziv grada",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Grad je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        ulica: {
            title: "Unesite ime ulice",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Ulica je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        datum: {
            title: "Unesite datum",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Datum je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        drugiPodaciZaKontakt: {
            title: "Unesite druge podatke za kontakt",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Drugi podaci za konktat su obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        mesto: {
            title: "Unesite mesto",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Mesto je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        nazivOrganaVlasti: {
            title: "Unesite naziv ograna vlasti",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Naziv organa vlasti je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        ime: {
            title: "Unesite Vase ime",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Ime je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        prezime: {
            title: "Unesite Vase prezime",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Prezime je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        nazivFirme: {
            title: "Unesite naziv firme",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Naziv firme je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        korisnickoIme: {
            isReadOnly: true
        },
        nazivPoverenika: {
            title: "Unesite naziv poverenika",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Naziv poverenika je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        brojZalbe: {
            title: "Unesite broj zalbe",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Broj zalbe je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        godinaOdbijanja: {
            title: "Unesite godinu odbijanja",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Godina odbijanja je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        razlog: {
            title: "Unesite razlog",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Razlog je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        sedistePoverenika: {},
        datumZalbe:{},
        naslov:{
            isReadOnly: true
        },
    }
  };

}