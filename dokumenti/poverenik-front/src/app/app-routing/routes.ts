import { Routes } from '@angular/router';
import { LoginGuard } from '../guards/login/login.service';
import { RoleGuard } from '../guards/role/role.service';
import { AppealDecisionPageComponent } from '../pages/appeal-decision-page/appeal-decision-page.component';
import { AppealSilencePageComponent } from '../pages/appeal-silence-page/appeal-silence-page.component';
import { DecisionPageComponent } from '../pages/decision-page/decision-page.component';
import { HomePageComponent } from '../pages/home-page/home-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/register-page/register-page.component';

export const routes: Routes = [
    { path: '',
     redirectTo: '/login',
     pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginPageComponent,
        canActivate: [LoginGuard]
    },
    {
        path: 'register',
        component: RegisterPageComponent
    },
    {
        path: 'home',
        component: HomePageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'POVERENIK|GRADJANIN'}
    },
    {
        path: 'make_decision',
        component: DecisionPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'POVERENIK'}
    },
    {
        path: 'make_appeal_silence',
        component: AppealSilencePageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'make_appeal_decision',
        component: AppealDecisionPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'GRADJANIN'}
    }
];
