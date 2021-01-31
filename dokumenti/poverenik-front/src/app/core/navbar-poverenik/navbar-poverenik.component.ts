import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar-poverenik',
  templateUrl: './navbar-poverenik.component.html',
  styleUrls: ['./navbar-poverenik.component.css']
})
export class NavbarPoverenikComponent implements OnInit {


  constructor(public dialog: MatDialog, private router: Router) {}

  ngOnInit(): void {
  }
}
