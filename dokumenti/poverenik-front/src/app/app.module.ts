import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './pages/material-module';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { NavbarGradjaninComponent } from './core/navbar/navbar.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './interceptors/intercept.service';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ToastrModule } from 'ngx-toastr';
import { AppealSilencePageComponent } from './pages/appeal-silence-page/appeal-silence-page.component';
import { AppealDecisionPageComponent } from './pages/appeal-decision-page/appeal-decision-page.component';
import { DecisionPageComponent } from './pages/decision-page/decision-page.component';
import { DatePipe } from '@angular/common';
import { SafeHtmlPipe } from './safe-html.pipe';
import { AppealDecisionReviewComponent } from './pages/appeal-decision-review/appeal-decision-review.component';
import { AppealSilenceReviewComponent } from './pages/appeal-silence-review/appeal-silence-review.component';
import { DecisionReviewComponent } from './pages/decision-review/decision-review.component';
import { AppealDecisionListComponent } from './pages/appeal-decision-list/appeal-decision-list.component';
import { AppealSilenceListComponent } from './pages/appeal-silence-list/appeal-silence-list.component';
import { DecisionListComponent } from './pages/decision-list/decision-list.component';
import { AllDecisionListComponent } from './pages/all-decision-list/all-decision-list.component';
import { NewAppealSilenceListComponent } from './pages/new-appeal-silence-list/new-appeal-silence-list.component';
import { NewAppealDecisionListComponent } from './pages/new-appeal-decision-list/new-appeal-decision-list.component';
import { AllAppealDecisionListComponent } from './pages/all-appeal-decision-list/all-appeal-decision-list.component';
import { AllAppealSilenceListComponent } from './pages/all-appeal-silence-list/all-appeal-silence-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    HomePageComponent,
    NavbarGradjaninComponent,
    AppealSilencePageComponent,
    AppealDecisionPageComponent,
    DecisionPageComponent,
    AppealDecisionReviewComponent,
    AppealSilenceReviewComponent,
    DecisionReviewComponent,
    SafeHtmlPipe,
    AppealDecisionListComponent,
    AppealSilenceListComponent,
    DecisionListComponent,
    AllDecisionListComponent,
    AllAppealDecisionListComponent,
    AllAppealSilenceListComponent,
    NewAppealSilenceListComponent,
    NewAppealDecisionListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ToastrModule.forRoot(),
    MaterialModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}, DatePipe],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
