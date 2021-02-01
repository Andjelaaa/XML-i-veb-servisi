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
      <podnosilac>
        <korisnickoIme>Unesi korisnicko ime</korisnickoIme>
        <nazivFirme>Unesi naziv firme</nazivFirme>
        <ime>Unesi ime</ime>
        <prezime>Unesi prezime</prezime>
      </podnosilac>
      <adresa>
        <ulica>Unesi ulicu</ulica>
        <broj>Unesi broj</broj>
        <grad>Unesi grad</grad>
      </adresa>
      <drugiPodaciZaKontakt>podatke unesi</drugiPodaciZaKontakt>
      <nazivOrganaVlasti>Unesi naziv organa</nazivOrganaVlasti>
      <sedisteOrgana>Unesi sediste organa</sedisteOrgana>
            <naslov>
                Zahtev za pristup informaciji od javnog znacaja
            </naslov>
      <paragrafi>
        <text></text>
        <izbori>
          <broj>1</broj>
          <tekst>obavestenje da li poseduje trazenu informaciju</tekst>
          <drugiNacin/>
        </izbori>
        <izbori>
          <broj>2</broj>
          <tekst>uvid u dokument koji sadrzi trazenu informaciju</tekst>
          <drugiNacin/>
        </izbori>
        <izbori>
          <broj>3</broj>
          <tekst>kopiju dokumenta koji sadrzi trazenu informaciju</tekst>
          <drugiNacin/>
        </izbori>
        <izbori>
          <broj>4</broj>
          <tekst>dostavljanje kopije dokumenta koji sadrzi trazenu informaciju:**</tekst>
          <podizbori>
            <a>postom</a>
            <b>elektronskom postom</b>
            <c>faksom</c>
            <d>na drugi nacin:***</d>
          </podizbori>
          <drugiNacin></drugiNacin>
        </izbori>
      </paragrafi>
      <paragrafi>
        <text></text>
        <izbori>
          <broj></broj>
          <tekst></tekst>
          <podizbori/>
          <drugiNacin/>
        </izbori>
      </paragrafi>
      <trazeneInformacije>Zahtev za dokument </trazeneInformacije>
      <datum>21.11.2020.</datum>
      <mesto>Novi Sad</mesto>
      <fusnote><element>* U kucici oznaciti koja zakonska prava na pristup informacijama zelite da ostvarite.</element></fusnote>
      <fusnote><element>** U kucici oznaciti nacin dostavljanaj kopije dokumenata</element></fusnote>
      <fusnote><element>*** Kada zahtevate drugi nacin dostavljanja obavezno upisati koji nacin dostavljanja zahtevates.</element></fusnote>
    </root>`;
    Xonomy.setMode('laic');
    Xonomy.render(xmlString, element, this.xonomyService.requestSpecification);
  }
  submit(): void {
    const text = Xonomy.harvest();
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
