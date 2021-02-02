import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  getHref(name: string) {
    return 'http://localhost:8081/api/zahtev/1/pdf';
  }
}
