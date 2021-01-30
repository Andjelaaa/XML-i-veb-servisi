import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';

import { LoginPageComponent } from './login-page/login-page.component';

@NgModule({
  declarations: [LoginPageComponent],
  imports: [ MaterialModule],
  exports: [LoginPageComponent],
  providers: []
})

export class AuthModule { }
