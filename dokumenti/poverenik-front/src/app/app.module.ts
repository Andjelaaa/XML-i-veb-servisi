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

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    HomePageComponent,
    NavbarGradjaninComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ToastrModule.forRoot(),
    MaterialModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
