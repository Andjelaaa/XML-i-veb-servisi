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
          isReadOnly : true
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
                text: 'Broj ulice je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        grad: {
          title: 'Unesite naziv grada',
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
        brojPredmeta: {
          title: 'Unesite broj predmeta',
          validate: function (jsElement: any) {
          if (jsElement.getText() == '') {
            Xonomy.warnings.push({
              htmlID: jsElement.htmlID,
              text: 'Broj predmeta je obavezno polje!',
            });
          }
        },
        hasText: true,
        asker: Xonomy.askString},
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
        podnosilac: {
          title: 'Podnosilac zahteva',
        },
        korisnickoIme: {
          title: 'Unesite korisnicko ime',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Korisnicko ime je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        ime : {
          title: 'Unesite ime podnosioca',
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
          title: 'Unesite prezime podnosioca',
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
          title: 'Unesite naziv firme podnosioca',
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
        trazenaInformacija: {
          title: 'Unesite trazenu informaciju',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Trazena informacija je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        brojKancelarije: {
          title: 'Unesite broj kancelarije',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Broj kancelarije je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        brojZgrade: {
          title: 'Unesite broj zgrade',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Broj zgrade je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        dan: {
          title: 'Unesite dan',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Dan je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        doSati: {
          title: 'Unesite do koliko sati',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
  
        },
        godina: {
          title: 'Unesite godinu',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Godina je obavezno polje!',
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
        novcanaNaknada: {
          title: 'Unesite novcanu naknadu',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Novcana naknada je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        odSati: {
          title: 'Unesite od koliko sati',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        sati: {
          title: 'Unesite vreme',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Sati su obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        naslov: {
          title: 'Unesite naslov',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Naslvo je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        },
        dostavljeno: {
          title: 'Unesite kome je dostavljeno',
          validate: function (jsElement: any) {
            if (jsElement.getText() == '') {
              Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: 'Dostavljeno je obavezno polje!',
              });
            }
          },
          hasText: true,
          asker: Xonomy.askString
        }
      }
    };
}
