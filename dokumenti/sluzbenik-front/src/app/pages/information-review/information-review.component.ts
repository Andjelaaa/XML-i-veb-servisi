import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { ObavestenjeService } from 'src/app/services/obavestenje-service/obavestenje.service';

@Component({
  selector: 'app-obavestenje-prikaz',
  templateUrl: './information-review.component.html',
  styleUrls: ['./information-review.component.css']
})
export class ObavestenjePrikazComponent implements OnInit {
  @Input() id: any;
  public html = '';
  constructor(private obavestenjeService: ObavestenjeService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getObavestenje();
  }

  getObavestenje(): void {
    this.obavestenjeService.toHtml(this.id).subscribe(
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
