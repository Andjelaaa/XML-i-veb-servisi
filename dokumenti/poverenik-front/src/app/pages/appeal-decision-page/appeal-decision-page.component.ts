import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { AppealDecisionService } from 'src/app/services/appeal-decision-service/appeal-decision.service';
import { XonomyAppealDecisionService } from 'src/app/services/xonomy-service/xonomy-appeal-decision.service';

declare const Xonomy: any;

@Component({
  selector: 'app-appeal-decision-page',
  templateUrl: './appeal-decision-page.component.html',
  styleUrls: ['./appeal-decision-page.component.css']
})
export class AppealDecisionPageComponent implements OnInit {

    time: any;
    constructor(
        private xonomyService: XonomyAppealDecisionService,
        private service: AppealDecisionService,
        private router: Router,
        private route: ActivatedRoute,
        private toastr: ToastrService,
        private datePipe: DatePipe) { }
      ngOnInit(): void {
      }
      ngAfterViewInit(): void {
         const token = localStorage.getItem('user') || '';
         const jwt: JwtHelperService = new JwtHelperService();       
         const info = jwt.decodeToken(token);
         const username = info.sub;
        this.time = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
        const element = document.getElementById('zalba');
        const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
        <root>
        <zahtevURI></zahtevURI>
           <adresaPodnosioca>
              <broj></broj>
              <grad></grad>
              <ulica></ulica>
           </adresaPodnosioca>
           <datum>${this.time}</datum>
           <drugiPodaciZaKontakt></drugiPodaciZaKontakt>
           <mesto />
           <naslov>
                   ŽALBA PROTIV ODLUKE ORGANA VLASTI KOJOM JE ODBIJEN ILI ODBAČEN ZATEV ZA PRISTUP INFORMACIJI
            </naslov>
           <nazivOrganaVlasti></nazivOrganaVlasti>
           <nazivPodnosioca>
              <ime></ime>
              <nazivFirme></nazivFirme>
              <prezime></prezime>
              <korisnickoIme>${username}</korisnickoIme>
           </nazivPodnosioca>
           <nazivPoverenika>
                        Povereniku za informacije od javnog značaja i zaštitu podataka o ličnosti
                </nazivPoverenika>
           <paragrafi>
              <element>
                 <brojZalbe></brojZalbe>
                 <godinaOdbijanja></godinaOdbijanja>
                 <tekst>Broj
                        
                        od
                        
                        godine.</tekst>
              </element>
              <element>
                 <datum />
                 <razlog />
                 <tekst>Navedenom odlukom organa vlasti(rešenjem, zaključkom, obaveštenjem u pisanoj
                        formi sa elementima odluke) , suprotno zakonu, odbijen-odbačen je moj zahtev koji
                        sam podneo/la- uputio/la dana
                        
                        godine i tako mi uskraćeno-onemogućeno ostvarivanje ustavnog i zakonskog prava na 
                        slobodan pristup informacijama od javnog značaja. Odluku pobijam u celosti, odnosno 
                        u delu kojim
                        
                        jer nije zasnovana na Zakonu o slobodnom pristupu informacijama od javnog značaja.</tekst>
              </element>
              <element>
                 <tekst>Na osnovu iznetih razloga, predlažem da Poverenik uvaži moju žalbu, poništi odluka prvostepenog organa i omogući
                        mi pristup traženoj/im informaciji/ma.</tekst>
              </element>
              <element>
                 <tekst>Žalbu podnosim blagovremeno, u zakonskom roku utvrđenom u članu 22.st. 1. Zakona o slobodnom
                        pristupu informacijama od javnog značaja.</tekst>
              </element>
           </paragrafi>
           <sedistePoverenika>
              <broj></broj>
              <grad></grad>
              <ulica></ulica>
           </sedistePoverenika>
           <tackeNapomene>
              <element>
                        U žalbi se mora navesti odluka koja se pobija( rešenje, zaknjučak, obaveštenje)
                        naziv organa koji je odluku doneo, kao i broj i datum odluke. Dovoljno je da žalilac
                        navede u žalbi u kom pogledu je nezadovoljan odlukom, s tim da žalbu ne mora posebno
                        priložiti. 
                    </element>
              <element>
                        Uz žalbu obavezno priložiti kopiju podnetog zahteva i dokaz
                        o njegovoj predaji-upućivanju organu kao i kopiju odluke organa koja se osporava žalbom.
                    </element>
           </tackeNapomene>
           <text null="true" />
        </root>`;
        Xonomy.setMode('laic');
        Xonomy.render(xmlString, element, this.xonomyService.appealDecisionSpecification);
      }
      submit(): void {
        const text = Xonomy.harvest();
        if(Xonomy.warnings.length !== 0) {
         this.toastr.error('Morate popuniti sva obavezna polja!')
         return
       }
        this.service.sendAppealDecision(text).subscribe(
          response => {
            this.router.navigateByUrl('/home');
          },
          error => {
            console.log(error);
          }
        );
      }

}
