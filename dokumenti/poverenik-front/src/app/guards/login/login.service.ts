import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';

@Injectable({
    providedIn: 'root'
})
export class LoginGuard implements CanActivate {
    constructor(
        public service: UserService,
        public router: Router
    ) { }

    canActivate(): boolean {
        if (this.service.isLoggedIn()) {
            this.router.navigate(['/home']);
            return false;
        }
        return true;
    }
}
