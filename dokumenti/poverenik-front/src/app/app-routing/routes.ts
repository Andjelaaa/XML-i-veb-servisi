import { Routes } from '@angular/router';
import { LoginGuard } from '../guards/login/login.service';
import { RoleGuard } from '../guards/role/role.service';
import { AllDecisionListComponent } from '../pages/all-decision-list/all-decision-list.component';
import { AppealDecisionListComponent } from '../pages/appeal-decision-list/appeal-decision-list.component';
import { AppealDecisionPageComponent } from '../pages/appeal-decision-page/appeal-decision-page.component';
import { AppealDecisionReviewComponent } from '../pages/appeal-decision-review/appeal-decision-review.component';
import { AppealSilenceListComponent } from '../pages/appeal-silence-list/appeal-silence-list.component';
import { AppealSilencePageComponent } from '../pages/appeal-silence-page/appeal-silence-page.component';
import { AppealSilenceReviewComponent } from '../pages/appeal-silence-review/appeal-silence-review.component';
import { DecisionListComponent } from '../pages/decision-list/decision-list.component';
import { DecisionPageComponent } from '../pages/decision-page/decision-page.component';
import { DecisionReviewComponent } from '../pages/decision-review/decision-review.component';
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
      //  canActivate: [LoginGuard]
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
        path: 'make_decision/:id',
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
    },
    {
        path: 'decision_review/:id',
        component: DecisionReviewComponent,
      //  canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'appeal_decision_review/:id',
        component: AppealDecisionReviewComponent,
      //  canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'appeal_silence_review/:id',
        component: AppealSilenceReviewComponent,
      //  canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'user_appeal_decision',
        component: AppealDecisionListComponent,
      //  canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
        path: 'user_appeal_silence',
        component: AppealSilenceListComponent,
      //  canActivate: [RoleGuard],
      //  data: {expectedRoles: 'GRADJANIN'}
    },
    {
      path: 'user_decision',
      component: DecisionListComponent,
    //  canActivate: [RoleGuard],
    //  data: {expectedRoles: 'GRADJANIN'}
  },
  {
    path: 'all_decision',
    component: AllDecisionListComponent,
  //  canActivate: [RoleGuard],
  //  data: {expectedRoles: 'GRADJANIN'}
  }
];
