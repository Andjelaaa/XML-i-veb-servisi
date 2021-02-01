import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarGradjaninComponent implements OnInit {

  constructor(
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
  }
  signOut(): void {
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}
