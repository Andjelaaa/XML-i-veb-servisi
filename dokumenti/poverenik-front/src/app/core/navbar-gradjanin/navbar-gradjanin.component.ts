import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-gradjanin',
  templateUrl: './navbar-gradjanin.component.html',
  styleUrls: ['./navbar-gradjanin.component.scss']
})
export class NavbarGradjaninComponent implements OnInit {

  constructor(
    private router: Router) { }

  ngOnInit(): void {
  }
}
