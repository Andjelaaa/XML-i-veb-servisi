import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RequestDTO } from 'src/app/model/RequestDTO';
import { RequestService } from 'src/app/services/request-service/request.service';
import { XonomyRequestService } from 'src/app/services/xonomy-service/xonomy-request.service';
import { ToastrService } from 'ngx-toastr';
import { JwtHelperService } from '@auth0/angular-jwt';

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
    private toastr: ToastrService,
    private route: ActivatedRoute) { }
    
  ngOnInit(): void {
      // mozda podesavanje parametara od kog je usera
  }
  ngAfterViewInit(): void {
    const token = localStorage.getItem('user') || '';
    const jwt: JwtHelperService = new JwtHelperService();       
    const info = jwt.decodeToken(token);
    const username = info.sub;
    const element = document.getElementById('zahtev');
    const xmlString = `<?xml version="1.0" encoding="UTF-8" ?>
    <root>
    <adresa>
       <broj></broj>
       <grad></grad>
       <ulica></ulica>
    </adresa>
    <datum></datum>
    <drugiPodaciZaKontakt></drugiPodaciZaKontakt>
    <fusnote>
       <element>* U kućici označiti koja zakonska prava na pristup informacijama želite da ostvarite.</element>
       <element>** U kućici označiti način dostavljanja kopije dokumenata.</element>
       <element>*** Kada zahtevate drugi način dostavljanja obavezno upisati koji način dostavljanja zahtevate.</element>
    </fusnote>
    <mesto></mesto>
    <naslov></naslov>
    <nazivOrganaVlasti></nazivOrganaVlasti>
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
       <ime></ime>
       <nazivFirme></nazivFirme>
       <prezime></prezime>
       <korisnickoIme>${username}</korisnickoIme>
    </podnosilac>
    <sedisteOrgana></sedisteOrgana>
    <trazeneInformacije></trazeneInformacije>
 </root>`;
    Xonomy.setMode('laic');
    Xonomy.render(xmlString, element, this.xonomyService.requestSpecification);
  }
  submit(): void {
    const text = Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Molimo popunite sva obavezna polja!')
      return
    };
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
