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
            //if element
            this.validate(jsChild); //recursion
          }
        }
      },
      elements: {
        podnosilac: {
          title: 'Podnosilac zahteva',
        },
        ime : {
          title: 'Unesite ime',
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
          title: 'Unesite prezime',
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
        adresa: {},
        ulica : {
          title: 'Unesite naziv ulice',
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
        broj: {
          title: 'Unesite ulicni broj',
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
          title: 'Unesite grad',
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
        drugiPodaciZaKontakt: {
          title: 'Unesite druge podatke za kontakt',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Drugi podaci za kontakt su obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        nazivOrganaVlasti: {
          title: 'Unesite naziv organa vlasti',
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
        sedisteOrgana: {
          title: 'Unesite sediste organa vlasti',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Sediste organa vlasti je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
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
              return jsElement.children[0].children[0].value !== "dostavljanje kopije dokumenta koji sadrži traženu informaciju";
            },
            caption: 'Dodajte podizbor',
            action: Xonomy.newElementChild,
            actionParameter: '<podizbori></podizbori>',
          },
          ]
        },
        tekst: {
          validate: function (jsElement: any) {
            if (jsElement.children[0].value === 'Izaberite opciju') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Morate odabrati tekst'
              });
            }
          },
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["obaveštenje da li poseduje traženu informaciju", "uvid u dokument koji sadrži traženu informaciju", "kopiju dokumenta koji sadrži traženu informaciju", "dostavljanje kopije dokumenta koji sadrži traženu informaciju"]
        },
        trazeneInformacije: {
          title: 'Unesite trazene informacije',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Trazene informacije su obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        datum: {
          title: 'Unesite datum',
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
        mesto: {
          title: 'Unesite mesto',
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
        fusnote: {
          isInvisible: true,
        },
        izbori: {
          hasText: false,
          
          validate: function (jsElement: any) {       
            if (jsElement.children[0].type === "text") {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
              });
            }
          },
          title: 'Kliknite da biste dodali izbor',
          menu: [
          {
            caption: 'Dodajte izbor',
            action: Xonomy.newElementChild,
            actionParameter: '<izbor><tekst>Izaberite opciju</tekst></izbor>',
          },
        ]
        },
        naslov: {
          title: 'Unesite naslov zaheva',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Naslov je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString,
        },
        korisnickoIme: {
          isReadOnly: true,
        },
        nazivFirme: {
          title: 'Unesite naziv firme',
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
        }
      }
  };
    
}
