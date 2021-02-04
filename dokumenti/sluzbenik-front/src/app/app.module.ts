import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { NavbarGradjaninComponent } from './core/navbar/navbar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './interceptors/intercept.service';
import { MaterialModule } from './pages/material-module';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { RequestPageComponent } from './pages/request-page/request-page.component';
import { InformationPageComponent } from './pages/information-page/information-page.component';
import { ObavestenjePrikazComponent } from './pages/information-review/information-review.component';
import { ZahtevPrikazComponent } from './pages/request-review/request-review.component';
import { SafeHtmlPipe } from './safe-html.pipe';
import { RequestListComponent } from './pages/request-list/request-list.component';
import { NewRequestsListComponent } from './pages/new-requests-list/new-requests-list.component';
import { AllRequestsListComponent } from './pages/all-requests-list/all-requests-list.component';
import { InformationListComponent } from './pages/information-list/information-list.component';
import { AllInformationListComponent } from './pages/all-information-list/all-information-list.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    NavbarGradjaninComponent,
    HomePageComponent,
    RequestPageComponent,
    InformationPageComponent,
    ObavestenjePrikazComponent,
    ZahtevPrikazComponent,
    SafeHtmlPipe,
    RequestListComponent,
    NewRequestsListComponent,
    AllRequestsListComponent,
    InformationListComponent,
    AllInformationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    ToastrModule.forRoot()
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
