import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { analyzeAndValidateNgModules } from '@angular/compiler';

declare const Xonomy: any;

@Injectable({
    providedIn: 'root'
})
export class XonomyDecisionService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    public decisionSpecification = {
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
        naziv: {
            isReadOnly: true
        },
        odluka:{},
        opisPostupka: {
            title: "Unesite opis postupka",
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Opis postupka je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        element:{
            hasText: true
        },
        tekst:{
            isReadOnly: true
        },
        tekstResenja: {
            hasText: false,
            validate: function (jsElement: any) {   
               if (jsElement.children[1].type === "text") {
                  Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                });
              }
            },
        },

        paragrafi: {
            hasText: false,
            validate: function (jsElement: any) {
                console.log(jsElement.children[0]);       
              if (jsElement.children[0].type === "text") {
                Xonomy.warnings.push({
                  htmlID: jsElement.htmlID,
                });
              }
            },
            title: 'Kliknite da biste dodali paragraf',
            menu: [
            {
              caption: 'Dodajte paragraf',
              action: Xonomy.newElementChild,
              actionParameter: '<element></element>',
            },
          ]
        },
        brojResenja:{
            title: 'Unesite broj resenja',
            validate: function (jsElement: any) {
                if (jsElement.getText() == '') {
                  Xonomy.warnings.push({
                    htmlID: jsElement.htmlID,
                    text: 'Broj resenja je obavezno polje!',
                  });
                }
              },
              hasText: true,
              asker: Xonomy.askString
        },
        datum:{
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
        zalbaOdlukeURI:{
            isReadOnly: true
        },
        zalbaCutanjeURI:{
            isReadOnly: true
        }
    }
  };

}
