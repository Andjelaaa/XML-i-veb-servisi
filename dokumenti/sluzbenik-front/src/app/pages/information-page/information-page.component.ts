import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InformationService } from 'src/app/services/information-service/information.service';
import { XonomyInformationService } from 'src/app/services/xonomy-service/xonomy-information.service';
import { ToastrService } from 'ngx-toastr';

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
    private toastr: ToastrService,
    private route: ActivatedRoute) { }
  ngOnInit(): void {
      // mozda podesavanje parametara od kog je usera
  }
  ngAfterViewInit(): void {
    //podatke o podnosiocu dobija iz zahteva
    const zahtev_uri = this.route.snapshot.paramMap.get('id');
    const element = document.getElementById('obavestenje');
    const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
    <obavestenje>
    <zahtevURI>${zahtev_uri}</zahtevURI>
    <adresa>
    <broj></broj>
    <grad></grad>
    <ulica></ulica>
    </adresa>
    <brojPredmeta></brojPredmeta>
    <datum></datum>
    <dostavljeno></dostavljeno>
    <mestoPecata></mestoPecata>
    <naslov></naslov>
    <nazivOrganaVlasti></nazivOrganaVlasti>
    <sedisteOrgana></sedisteOrgana>
    <paragrafi>
    <element>
    <brojKancelarije></brojKancelarije>
    <brojZgrade></brojZgrade>
    <dan></dan>
    <doSati></doSati>
    <godina></godina>
    <mesto></mesto>
    <novcanaNaknada></novcanaNaknada>
    <odSati></odSati>
    <sati></sati>
    <trazenaInformacija></trazenaInformacija>
    <ulica></ulica>
    </element>
    </paragrafi>
    <podnosilac>
    <ime></ime>
    <nazivFirme></nazivFirme>
    <prezime></prezime>
    <korisnickoIme></korisnickoIme>
    </podnosilac>
    <text null="true" />
    </obavestenje>`;
    Xonomy.setMode('laic');
    Xonomy.render(xmlString, element, this.xonomyService.informationSpecification);
  }
  submit(): void {
    const text = Xonomy.harvest();
    if(Xonomy.warnings.length !== 0) {
      this.toastr.error('Molimo popunite sva obavezna polja!')
      return
    }
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

