import { Routes } from '@angular/router';
import { RoleGuard } from '../guards/role/role.service';
import { LoginPageComponent } from '../pages/auth/login-page/login-page.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPageComponent
    }
];
