import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';
import { XonomyAppealSilenceService } from 'src/app/services/xonomy-service/xonomy-appeal-silence.service';

declare const Xonomy: any;

@Component({
  selector: 'app-appeal-silence-page',
  templateUrl: './appeal-silence-page.component.html',
  styleUrls: ['./appeal-silence-page.component.css']
})
export class AppealSilencePageComponent implements OnInit {

    time: any;
    constructor(
        private xonomyService: XonomyAppealSilenceService,
        private service: AppealSilenceService,
        private router: Router,
        private route: ActivatedRoute,
        private datePipe: DatePipe) { }
      ngOnInit(): void {
      }
      ngAfterViewInit(): void {
        //podatke o podnosiocu dobija iz zalbe
        this.time = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
        const element = document.getElementById('zalba');
        const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
        <root>
           <adresa>
              <broj>32</broj>
              <grad>Novi Sad</grad>
              <ulica>Jugoslovenska</ulica>
           </adresa>
           <datumZalbe>'21.21.2020.'</datumZalbe>
           <drugiPodaciZaKontakt>064872743</drugiPodaciZaKontakt>
           <mestoZalbe />
           <naslov>
                 ŽALBA KADA ORGAN VLASTI NIJE POSTUPIO/ nije postupio u celosti/ PO ZAHTEVU  TRAŽIOCA U ZAKONSKOM 
                 ROKU  (ĆUTANjE UPRAVE)
            </naslov>
           <nazivPoverenika>
                        Povereniky za informacije od javnog značaja i zaštitu podataka o ličnosti
                </nazivPoverenika>
           <paragrafi>
              <element>
                 <nazivOrgana>Sluzbenik</nazivOrgana>
                 <text>U skladu sa članom 22. Zakona o slobodnom pristupu informacija od javnog značaja podnosim:
                    Žalbu
                    protiv</text>
              </element>
              <element>
                 <text>zbog toga što organ vlasti:
                        nije postupio/ nije postupio u celosti/ u zakonskom roku</text>
              </element>
              <element>
                 <datum>10.03.2021.</datum>
                 <podaciOZahtevuIInformacijama>podaci o zahevu</podaciOZahtevuIInformacijama>
                 <text>po mom zahtevu za slobodan pristup informacijama od javnog značaja
                        koji sam podneo tom organu dana
                        
                        godine, a kojim sam tražio/la da mi se u skladu sa Zakonom o slobodnom 
                        pristupu informacijama od javnog značaja omogući uvid- kopija dokumenta 
                        koji sadrži informacije o/u vezi sa:</text>
              </element>
              <element>
                 <text>Na osnovu iznetog, predlažem da Poverenik uvaži moju žalbu i omogući mi
                        pristup traženoj/im informaciji/ma.</text>
              </element>
              <element>
                 <text>Kao dokaz, uz žalbu zbog nepostupanju po zahtevu u celosti, treba priložiti i dobijeni odgovor
                        organa vlasti.</text>
              </element>
              <element>
                 <text>Napomena: Kod žalbe  zbog nepostupanju po zahtevu u celosti, treba priložiti i dobijeni odgovor organa vlasti.</text>
              </element>
           </paragrafi>
           <podnosilac>
              <ime>Slavisa</ime>
              <nazivFirme />
              <prezime>Slavic</prezime>
              <korisnickoIme>slakiiisa</korisnickoIme>
           </podnosilac>
           <sedistePoverenika>
              <broj>15</broj>
              <grad>Beograd</grad>
              <ulica>Bulevar kralja Aleksandra</ulica>
           </sedistePoverenika>
           <text null="true" />
        </root>`;
        Xonomy.setMode('laic');
        Xonomy.render(xmlString, element, this.xonomyService.appealSilenceSpecification);
      }
      submit(): void {
        const text = Xonomy.harvest();
        this.service.sendAppealSilence(text).subscribe(
          response => {
            this.router.navigateByUrl('/home');
          },
          error => {
            console.log(error);
          }
        );
      }

}
