import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarUserComponent } from './core/navbar-user/navbar/navbar.component';
import { Interceptor } from './interceptors/intercept.service';
import { MaterialModule } from './pages/material-module';
import { NavbarAdminComponent } from './core/navbar-admin/navbar-admin/navbar-admin.component';
import { AuthModule } from './pages/auth/auth.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarUserComponent,
    NavbarAdminComponent,

     ],
    imports: [
     BrowserModule,
    AppRoutingModule,
    MaterialModule,
    AuthModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
