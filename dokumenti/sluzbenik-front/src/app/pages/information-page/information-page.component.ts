import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InformationService } from 'src/app/services/information-service/information.service';
import { XonomyInformationService } from 'src/app/services/xonomy-service/xonomy-information.service';

declare const Xonomy: any;

@Component({
  selector: 'app-information-page',
  templateUrl: './information-page.component.html',
  styleUrls: ['./information-page.component.css']
})
export class InformationPageComponent implements OnInit, AfterViewInit{

  constructor(
    private xonomyService: XonomyInformationService,
    private informationService: InformationService,
    private router: Router,
    private route: ActivatedRoute) { }
  ngOnInit(): void {
      // mozda podesavanje parametara od kog je usera
  }
  ngAfterViewInit(): void {
    //podatke o podnosiocu dobija iz zahteva
    const element = document.getElementById('obavestenje');
    const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
    <obavestenje>
    <zahtevURI>${1}</zahtevURI>
    <adresa>
    <broj>123</broj>
    <grad>Novi Sad</grad>
    <ulica>Sterije Popovica</ulica>
    </adresa>
    <brojPredmeta>12</brojPredmeta>
    <datum>21.11.2019.</datum>
    <dostavljeno>
    <element>
    Достављено:
    Именованом
    Архиви
    </element>
    </dostavljeno>
    <mestoPecata>
    (М.П.)
    </mestoPecata>
    <naslov>
    ОБАВЕШТЕЊЕ
    о стављању на увид документа који садржи
    тражену информацију и о изради копије
    </naslov>
    <nazivOrganaVlasti>NOV</nazivOrganaVlasti>
    <sedisteOrgana>Novi Sad</sedisteOrgana>
    <paragrafi>
    <element>
    <brojKancelarije>12</brojKancelarije>
    <brojZgrade>31</brojZgrade>
    <dan>21.</dan>
    <doSati>15:00</doSati>
    <godina>1999.</godina>
    <mesto>Novi Sad</mesto>
    <novcanaNaknada>213.99</novcanaNaknada>
    <odSati>14:00</odSati>
    <sati>14:23</sati>
    <trazenaInformacija>ZELIM OBAVESTENJE</trazenaInformacija>
    <ulica>Jovan Jovanovic Zmaj</ulica>
    </element>
    </paragrafi>
    <podnosilac>
    <ime>Jova</ime>
    <nazivFirme>Firma doo</nazivFirme>
    <prezime>Jovic</prezime>
    <korisnickoIme>Jocy</korisnickoIme>
    </podnosilac>
    <text null="true" />
    </obavestenje>`;
    Xonomy.setMode('laic');
    Xonomy.render(xmlString, element, this.xonomyService.informationSpecification);
  }
  submit(): void {
    const text = Xonomy.harvest();
    this.informationService.sendInformation(text).subscribe(
      response => {
        this.router.navigateByUrl('/home');
      },
      error => {
        console.log(error);
      }
    );
  }

}

