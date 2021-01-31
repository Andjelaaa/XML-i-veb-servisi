import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { MaterialModule } from './material-module';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { NavbarGradjaninComponent } from './core/navbar-gradjanin/navbar-gradjanin.component';
import { NavbarSluzbenikComponent } from './core/navbar-sluzbenik/navbar-sluzbenik.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    NavbarGradjaninComponent,
    NavbarSluzbenikComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
