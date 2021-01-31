import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar-sluzbenik',
  templateUrl: './navbar-sluzbenik.component.html',
  styleUrls: ['./navbar-sluzbenik.component.css']
})
export class NavbarSluzbenikComponent implements OnInit {


  constructor(public dialog: MatDialog, private router: Router) {}

  ngOnInit(): void {
  }
}
