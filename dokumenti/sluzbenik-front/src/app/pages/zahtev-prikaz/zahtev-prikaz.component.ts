import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { ZahtevService } from 'src/app/services/zahtev-service/zahtev.service';

@Component({
  selector: 'app-zahtev-prikaz',
  templateUrl: './zahtev-prikaz.component.html',
  styleUrls: ['./zahtev-prikaz.component.css']
})
export class ZahtevPrikazComponent implements OnInit {
  @Input() id: any;
  public html: string = "";
  constructor(private zahtevService: ZahtevService,
              private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getZahtev();
  }

  getZahtev(){
    this.zahtevService.toHtml(this.id).subscribe(
      (response => {
        if (!response) {
          alert('File not exist or is empty');
        }
        this.html = response;
      }),
      (error => {
        console.log(JSON.stringify(error));
        alert(error.error.message);
      })
    );
  }
}
