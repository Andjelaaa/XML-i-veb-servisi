import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { RequestService } from 'src/app/services/request-service/request.service';

@Component({
  selector: 'app-zahtev-prikaz',
  templateUrl: './request-review.component.html',
  styleUrls: ['./request-review.component.css']
})
export class ZahtevPrikazComponent implements OnInit {
  @Input() id: any;
  public html = '';
  constructor(private requestService: RequestService,
              private route: ActivatedRoute ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getZahtev();
  }

  getZahtev(): void{
    this.requestService.toHtml(this.id).subscribe(
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
