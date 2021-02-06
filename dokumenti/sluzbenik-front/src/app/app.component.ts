import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from './services/user-service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'sluzbenik-front';
  public type!: string| undefined;

  constructor(private router: Router,
    private userService: UserService) {}

  ngOnInit(): void {
     this.userService.initializeUsers().subscribe();
  }
  checkType(): void {
    const item = localStorage.getItem('user');

    if (!item) {
        this.type = undefined;
        return;
    }

    const jwt: JwtHelperService = new JwtHelperService();
    this.type = jwt.decodeToken(item).type;
  }

}
