import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DecisionService } from 'src/app/services/decision-service/decision.service';
import { XonomyDecisionService } from 'src/app/services/xonomy-service/xonomy-decision.service';


declare const Xonomy: any;

@Component({
  selector: 'app-decision-page',
  templateUrl: './decision-page.component.html',
  styleUrls: ['./decision-page.component.css']
})
export class DecisionPageComponent implements OnInit {
    time: any;
    private id: string = "";
    private idOdluka: string = '';
    private idCutanje: string = '';
    constructor(
        private xonomyService: XonomyDecisionService,
        private service: DecisionService,
        private router: Router,
        private route: ActivatedRoute,
        private datePipe: DatePipe) { }
      ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id') as string;
        if(this.id.startsWith('o')){
            this.idOdluka = this.id.substring(1);
        }else{
          this.idCutanje = this.id.substring(1);
        }
      }
      ngAfterViewInit(): void {
        //podatke o podnosiocu dobija iz zalbe
        this.time = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
        const element = document.getElementById('resenje');
        const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
        <root>
        <zalbaCutanjeURI>${this.idCutanje}</zalbaCutanjeURI>
        <zalbaOdlukeURI>${this.idOdluka}</zalbaOdlukeURI>
        <korisnickoIme>Unesite korisnicko ime</korisnickoIme>
        <naziv>Rešenje</naziv>
        <odluka>kada je zalba osnovana – nalaze se:</odluka>
        <opisPostupka>
        Poverenik za informacije od javnog značaja i zaštitu podataka o ličnosti, u postupku po žalbi
        koju je izjavio AA, zbog nepostupanja Učiteljskog fakulteta u Prizrenu sa privremenim sedištem
        u Leposaviću, ul. Nemanjina bb, po njegovom zahtevu od 16.04.2020. godine za pristup
        informacijama od javnog značaja, na osnovu člana 35. stav 1. tačka 5. Zakona o slobodnom pristupu
        informacijama od javnog značaja („Sl. glasnik RS“, br. 120/04, 54/07, 104/09 i 36/10), a u vezi sa
        članom 4. tačka 22. Zakona o zaštiti podataka o ličnosti („Sl. glasnik RS“, broj 87/18), kao i člana
        23. i člana 24. stav 4. Zakona o slobodnom pristupu informacijama od javnog značaja i člana 173.
        stav 2. Zakona o opštem upravnom postupku („Sl. glasnik RS“, br. 18/2016 i 95/2018-autentično
        tumačenje), donosi
        </opisPostupka>
        <potpisPoverenika>Poverenik</potpisPoverenika>
        <tekstObrazlozenja>
        <tekst>
        Obrazloženje
        </tekst>
        <paragrafi>
        <element>
        AA, kao tražilac informacija, izjavio je dana 07.05.2020. godine žalbu Povereniku, zbog
        nepostupanja Učiteljskog fakulteta u Prizrenu sa privremenim sedištem u Leposaviću po
        njegovom zahtevu od 16.04.2020. godine za pristup informacijama od javnog značaja i u prilogu
        dostavio kopiju istog.
        </element>
        <element>
        Postupajući po žalbi, Poverenik je dana 11.05.2020. godine uputio istu na izjašnjenje
        Učiteljskom fakultetu u Prizrenu sa privremenim sedištem u Leposaviću, kao organu vlasti u
        smislu člana 3. Zakona o slobodnom pristupu informacijama od javnog značaja i zatražio da se
        izjasni o navodima žalbe, posebno o razlozima nepostupanja u zakonskom roku po podnetom zahtevu
        u skladu sa odredbama člana 16. st.1-9. ili st. 10. Zakona, ostavljajući rok od osam dana, povodom
        čega nije dobio odgovor.
        </element>
        <element>
        Po razmatranju žalbe i ostalih spisa ovog predmeta, doneta je odluka kao u dispozitivu
        rešenja iz sledećih razloga:
        </element>
        <element>
        Uvidom u spise predmeta utvrđeno je da je AA, dana 16.04.2020. godine, podneo Učiteljskom
        fakultetu u Prizrenu sa privremenim sedištem u Leposaviću, zahtev za pristup informacijama
        od javnog značaja, elektronskom poštom, kojim je tražio informacije od javnog značaja, bliže
        navedene u stavu I dispozitiva ovog rešenja.
        
        </element>
        <element>
        Takođe je uvidom u spise predmeta utvrđeno da po zahtevu žalioca od 16.04.2020. godine,
        organ vlasti nije postupio, što je bio dužan da učini bez odlaganja, a najkasnije u roku od 15 dana
        od prijema zahteva te da žalioca, u smislu člana 16. stav 1. Zakona o slobodnom pristupu informacijama od javnog značaja, obavesti da li poseduje tražene informacije i da mu, ukoliko
        iste poseduje, stavi na uvid dokument koji iste sadrži odnosno izda mu ili dostavi kopiju
        dokumenta u kome su tražene informacije sadržane, ili da u suprotnom donese rešenje o odbijanju
        zahteva, u smislu stava 10. istog člana.
        </element>
        <element>
        Imajući u vidu da organ vlasti po zahtevu žalioca od 16.04.2020. godine nije postupio u
        skladu sa navedenim odredbama Zakona o slobodnom pristupu informacijama od javnog značaja, a
        da nije opravdao razloge nepostupanja po podnetom zahtevu, Poverenik je u postupku po žalbi, na
        osnovu člana 23. i člana 24. st. 1. i 4. Zakona o slobodnom pristupu informacijama od javnog
        značaja i člana 173. st. 2. Zakona o opštem upravnom postupku, odlučio kao u stavu I dispozitiva
        ovog rešenja, našavši da je žalba osnovana, odnosno da je nesporno pravo žalioca na tražene
        informacije u smislu člana 5. Zakona o slobodnom pristupu informacijama od javnog značaja, po
        kome svako ima pravo da mu bude saopšteno da li organ vlasti poseduje ili mu je dostupna određena
        informacija od javnog značaja, kao i da mu se informacija, ukoliko je u posedu organa, učini
        dostupnom, na način kako je to naloženo u stavu I dispozitiva ovog rešenja, što je u skladu sa
        odredbom člana 12. Zakona o slobodnom pristupu informacijama od javnog značaja, koja predviđa
        mogućnost izdvajanja tražene informacije od javnog značaja od ostalih informacija sadržanih u
        dokumentu u koje organ vlasti nije dužan da tražiocu omogući uvid, jer bi se dostupnošću tim
        informacijama povredilo pravo na privatnost i zaštitu podataka o ličnosti lica na koja se
        tražene informacije odnose.
        </element>
        <element>
        Učiteljski fakultet u Prizrenu sa privremenim sedištem u Leposaviću, je dužan da o
        izvršenju rešenja iz stava I dispozitiva, obavesti Poverenika u skladu sa članom 24. stav 3. Zakona
        o slobodnom pristupu informacijama od javnog značaja.
        </element>
        <element>
        Uputstvo o pravnom sredstvu:
        </element>
        <element>
        Protiv ovog rešenja nije dopuštena žalba već se, u skladu sa Zakonom o upravnim
        sporovima, može pokrenuti upravni spor tužbom Upravnom sudu u Beogradu, u roku od 30 dana od
        dana prijema rešenja. Taksa na tužbu iznosi 390,00 dinara
        </element>
        </paragrafi>
        </tekstObrazlozenja>
        <tekstResenja>
        <tekst>
        REŠENjE
        </tekst>
        <paragrafi>
        <element>
        I Nalaže se Učiteljskom fakultetu u Prizrenu sa privremenim sedištem u Leposaviću, da
        bez odlaganja, a najkasnije u roku od pet dana od dana prijema ovog rešenja, obavesti AA, da li
        poseduje tražene informacije odnosno dokument u kome su iste sadržane, i to: Ugovor o radu koji
        je kao poslednji potpisan između tog Fakulteta i BB, te da mu, ukoliko takav dokument poseduje
        dostavi kopiju istog elektronskom poštom na adresu … ili poštom, s tim što će pre dostavljanja
        zaštititi i učiniti nedostupnim podatke o ličnosti kojima bi se povredilo pravo na privatnost
        lica na koje se informacije odnose, kao što su: adresa stanovanja, lični matični broj građana, ime
        oca, radni staž, prosečna ocena studiranja i sl. ukoliko su takvi podaci sadržani u traženom
        dokumentu.
        </element>
        <element>
        II O izvršenju rešenja Učiteljski fakultet u Prizrenu sa privremenim sedištem u
        Leposaviću, će obavestiti Poverenika u roku od sedam dana od prijema ovog rešenja.
        </element>
        </paragrafi>
        </tekstResenja>
        <text null="true" />
        <zaglavlje>
        <brojResenja>071-01-1114/2020-03</brojResenja>
        <datum>${this.time}</datum>
        </zaglavlje>
        </root>`;
        Xonomy.setMode('laic');
        Xonomy.render(xmlString, element, this.xonomyService.decisionSpecification);
      }
      submit(): void {
        const text = Xonomy.harvest();
        console.log(text);
        this.service.sendDecision(text).subscribe(
          response => {
            this.router.navigateByUrl('/home');
          },
          error => {
            console.log(error);
          }
        );
      }
}
