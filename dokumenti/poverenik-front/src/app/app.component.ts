import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'poverenik-front';
  public type!: string| undefined;

  constructor(private router: Router) {}

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
