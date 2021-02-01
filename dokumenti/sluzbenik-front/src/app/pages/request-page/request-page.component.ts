import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';
import { XonomyRequestService } from 'src/app/services/xonomy-service/xonomy-request.service';

declare const Xonomy: any;

@Component({
  selector: 'app-request-page',
  templateUrl: './request-page.component.html',
  styleUrls: ['./request-page.component.css']
})
export class RequestPageComponent implements OnInit, AfterViewInit{

  constructor(
    private xonomyService: XonomyRequestService,
    private requestService: RequestService,
    private router: Router,
    private route: ActivatedRoute) { }
  ngOnInit(): void {
      // mozda podesavanje parametara od kog je usera
  }
  ngAfterViewInit(): void {
    const element = document.getElementById('zahtev');
    const xmlString = `<?xml version="1.0" encoding="UTF-8" ?>
    <root>
    <adresa>
       <broj>12</broj>
       <grad>Novi Sad</grad>
       <ulica>Bul. Kralja Petra</ulica>
    </adresa>
    <datum>21.11.2020.</datum>
    <drugiPodaciZaKontakt>069784532</drugiPodaciZaKontakt>
    <fusnote>
       <element>* U kućici označiti koja zakonska prava na pristup informacijama želite da ostvarite.</element>
       <element>** U kućici označiti način dostavljanja kopije dokumenata.</element>
       <element>*** Kada zahtevate drugi način dostavljanja obavezno upisati koji način dostavljanja zahtevate.</element>
    </fusnote>
    <mesto>Novi Sad</mesto>
    <naslov>
             ZAHTEV
             za pristup informaciji od javnog značaja
         </naslov>
    <nazivOrganaVlasti>SOPV</nazivOrganaVlasti>
    <paragrafi>
       <element>
          <text>
             Na osnovu člana 15. st. 1. Zakona o slobodnom pristupu informacijama od 
             javnog značaja (Službeni glasnik RS, br. 120/04, 54/07, 104/09 i 36/10),
             od gore navedenog organa zahtevam:*
         </text>
       </element>
       <element>
          <izbori>
          </izbori>
       </element>
       <element>
          <text>
             Ovaj zahtev se odnosi na sledeće informacije:
         </text>
       </element>
    </paragrafi>
    <podnosilac>
       <ime>Marko</ime>
       <nazivFirme>FIRMA doo</nazivFirme>
       <prezime>Markovic</prezime>
    </podnosilac>
    <sedisteOrgana>Novi Sad</sedisteOrgana>
    <trazeneInformacije>Zahtev za dokument </trazeneInformacije>
 </root>`;
    Xonomy.setMode('laic');
    Xonomy.render(xmlString, element, this.xonomyService.requestSpecification);
  }
  submit(): void {
    const text = Xonomy.harvest();
    console.log(text);
    this.requestService.sendRequest(text).subscribe(
      response => {
        this.router.navigateByUrl('/home');
      },
      error => {
        console.log(error);
      }
    );
  }

}
