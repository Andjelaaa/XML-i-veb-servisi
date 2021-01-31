import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  form: FormGroup;

  constructor(
      private fb: FormBuilder,
      private userService: UserService,
  ) {
      this.form = this.fb.group({
          ime: [null, Validators.required],
          prezime: [null, Validators.required],
          email: [null, Validators.required],
          password: [null, Validators.required],
          passwordRepeat: [null, Validators.required]
      });

  }

  ngOnInit(): void {
  }
  submit(){}
}
