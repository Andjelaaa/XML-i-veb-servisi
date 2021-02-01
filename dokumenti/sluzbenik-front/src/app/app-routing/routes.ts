import { Routes } from '@angular/router';
import { LoginGuard } from '../guards/login/login.service';
import { RoleGuard } from '../guards/role/role.service';
import { HomePageComponent } from '../pages/home-page/home-page.component';
import { InformationPageComponent } from '../pages/information-page/information-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/register-page/register-page.component';
import { RequestPageComponent } from '../pages/request-page/request-page.component';

export const routes: Routes = [
    { path: '',
     redirectTo: '/login',
     pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginPageComponent,
        // canActivate: [LoginGuard]
    },
    {
        path: 'register',
        component: RegisterPageComponent
    },
    {
        path: 'home',
        component: HomePageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'SLUZBENIK|GRADJANIN'}
    },
    {
        path: 'make_info',
        component: InformationPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'SLUZBENIK'}
    },
    {
        path: 'make_request',
        component: RequestPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'GRADJANIN'}
    }
];
