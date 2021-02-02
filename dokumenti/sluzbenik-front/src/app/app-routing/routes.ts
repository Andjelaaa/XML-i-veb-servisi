import { Routes } from '@angular/router';
import { LoginGuard } from '../guards/login/login.service';
import { RoleGuard } from '../guards/role/role.service';
import { HomePageComponent } from '../pages/home-page/home-page.component';
import { InformationPageComponent } from '../pages/information-page/information-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/register-page/register-page.component';
import { RequestPageComponent } from '../pages/request-page/request-page.component';
import { ObavestenjePrikazComponent } from '../pages/information-review/information-review.component';
import { ZahtevPrikazComponent } from '../pages/request-review/request-review.component';
import { RequestListComponent } from '../pages/request-list/request-list.component';
import { NewRequestsListComponent } from '../pages/new-requests-list/new-requests-list.component';
import { AllRequestsListComponent } from '../pages/all-requests-list/all-requests-list.component';

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
        path: 'make_info/:id',
        component: InformationPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'SLUZBENIK'}
    },
    {
        path: 'make_request',
        component: RequestPageComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'obavestenje/:id',
        component: ObavestenjePrikazComponent,
       // canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'zahtev/:id',
        component: ZahtevPrikazComponent,
       // canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'user_requests',
        component: RequestListComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'new_requests',
        component: NewRequestsListComponent,
       canActivate: [RoleGuard],
       data: {expectedRoles: 'SLUZBENIK'}
    },
    {
        path: 'requests',
        component: AllRequestsListComponent,
        canActivate: [RoleGuard],
        data: {expectedRoles: 'SLUZBENIK'}
    }
];
