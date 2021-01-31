import { Routes } from '@angular/router';
//import { RoleGuard } from '../guards/role/role.service';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/register-page/register-page.component';

export const routes: Routes = [
    { path: '',   redirectTo: '/login',pathMatch:'full'},

    {
        path: 'login',
        component: LoginPageComponent
    },
    {
        path: 'register',
        component: RegisterPageComponent
    }
];
