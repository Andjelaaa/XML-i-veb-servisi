import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

declare const Xonomy: any;

@Injectable({
    providedIn: 'root'
})
export class XonomyAppealSilenceService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    public appealSilenceSpecification = {
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
        nazivOrgana: {
          title: "Unesite naziv organa",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Naziv organa je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        mestoZalbe: {
          title: "Unesite mesto zalbe",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Mesto zalbe je obavezno polje!',
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
                  text: 'Drugi podaci za kontakt su obavezno polje!',
                });
              }
            },
            hasText: true,
            asker: Xonomy.askString

        },
        adresa: {},
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
            title: "Unesite grad",
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
            title: "Unesite naziv ulice",
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
        datumZalbe: {
            title: "Unesite datum podnosenja zalbe",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Datum zalbe je obavezno polje!',
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
        naslov: {
            title: "Unesite naslov zalbe",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Naslov je obavezno polje!',
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
        podaciOZahtevuIInformacijama: {
            title: "Unesite podatke o zahtevu i infomacijama",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Podaci o zahtevu su obavezno polje!',
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
            isReadOnly: true,
        },
        
        sedistePoverenika: {},
        
        nazivPoverenika:{
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
        
    }
  };

}
