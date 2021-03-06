import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DecisionService } from 'src/app/services/decision-service/decision.service';
import { XonomyDecisionService } from 'src/app/services/xonomy-service/xonomy-decision.service';
import { ToastrService } from 'ngx-toastr';
import { AppealSilenceService } from 'src/app/services/appeal-silence-service/appeal-silence.service';
import { AppealDecisionService } from 'src/app/services/appeal-decision-service/appeal-decision.service';


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
    private username: string = '';
    constructor(
        private appealSilenceService : AppealSilenceService,
        private appealDecisionService: AppealDecisionService,
        private xonomyService: XonomyDecisionService,
        private service: DecisionService,
        private router: Router,
        private route: ActivatedRoute,
        private toastr: ToastrService,
        private datePipe: DatePipe) { }
      ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id') as string;
        if(this.id.startsWith('o')){
            this.idOdluka = this.id.substring(1);
            this.appealDecisionService.getUsername(this.idOdluka).subscribe(
              res => {
                this.username = res;
                this.ngAfterViewInitt();
              },
              error => {
                console.log(error);
              }
            )
        }else{
          this.idCutanje = this.id.substring(1);
          this.appealSilenceService.getUsername(this.idCutanje).subscribe(
            res => {
              this.username = res;
              this.ngAfterViewInitt();
            },
            error => {
              console.log(error);
            }
          )
        }
        
      }
      ngAfterViewInitt(): void {
        console.log(this.username+"snksb");
        this.time = this.datePipe.transform(new Date(), 'dd.MM.yyyy.');
        const element = document.getElementById('resenje');
        const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
        <root>
        <zalbaCutanjeURI>${this.idCutanje}</zalbaCutanjeURI>
        <zalbaOdlukeURI>${this.idOdluka}</zalbaOdlukeURI>
        <korisnickoIme>${this.username}</korisnickoIme>
        <naziv>Rešenje</naziv>
        <odluka></odluka>
        <opisPostupka></opisPostupka>
        <potpisPoverenika>Poverenik za informacije od javnog znacaja</potpisPoverenika>
        <tekstObrazlozenja>
        <tekst>
        Obrazloženje
        </tekst>
        <paragrafi>
        <element></element>
        </paragrafi>
        </tekstObrazlozenja>
        <tekstResenja>
        <tekst>
        REŠENjE
        </tekst>
        <paragrafi>
        <element></element>
        </paragrafi>
        </tekstResenja>
        <text null="true" />
        <zaglavlje>
        <brojResenja></brojResenja>
        <datum>${this.time}</datum>
        </zaglavlje>
        </root>`;
        Xonomy.setMode('laic');
        Xonomy.render(xmlString, element, this.xonomyService.decisionSpecification);
      }
      submit(): void {
        const text = Xonomy.harvest();
        if(Xonomy.warnings.length !== 0) {
          this.toastr.error('Morate popuniti sva obavezna polja!')
          return
        }
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
