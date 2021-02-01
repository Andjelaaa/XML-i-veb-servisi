import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { ObavestenjeService } from 'src/app/services/obavestenje-service/obavestenje.service';

@Component({
  selector: 'app-obavestenje-prikaz',
  templateUrl: './obavestenje-prikaz.component.html',
  styleUrls: ['./obavestenje-prikaz.component.css']
})
export class ObavestenjePrikazComponent implements OnInit {
  @Input() id: any;
  public html: string = "";
  constructor(private obavestenjeService: ObavestenjeService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getObavestenje();
  }

  getObavestenje() {
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
