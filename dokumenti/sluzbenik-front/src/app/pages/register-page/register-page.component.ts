import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  formRegister!: FormGroup;

  constructor(
      private fb: FormBuilder,
      private userService: UserService,
      private router: Router,
      private toastr: ToastrService

  ) {
      this.createForm();
  }

  ngOnInit(): void {
  }
  createForm(): void{
    this.formRegister = this.fb.group({
        name: [null, Validators.required],
        surname: [null, Validators.required],
        username: [null, Validators.required],
        email: [null, Validators.required],
        password: [null, Validators.required],
        passwordRepeat: [null, Validators.required]
    });
  }
  submit(): void {
    if (this.formRegister.invalid){
        this.toastr.error('Sva polja su obavezna.');
        return;
        
    }
    if (this.formRegister.value.password !== this.formRegister.value.passwordRepeat){
        this.toastr.error('Šifre nisu iste.');
        return;
    }
    const user: any = {};
    const newUser = { _declaration:
        { _attributes: { version: '1.0', encoding: 'utf-8' } },
      root: {
        username: { _text: '' }, password: { _text: '' }, lastName: { _text: '' },
        firstName: { _text: '' }, email: { _text: '' } } };
    newUser.root.username = this.formRegister.value.username;
    newUser.root.password = this.formRegister.value.password;
    newUser.root.lastName = this.formRegister.value.surname;
    newUser.root.firstName = this.formRegister.value.name;
    newUser.root.email = this.formRegister.value.email;
    const convert = require('xml-js');

    const convertXML = convert.js2xml(newUser, {compact: true, ignoreComment: true, spaces: 4});
    this.userService.register(convertXML).subscribe(
      result => {
        const userToken = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
        this.router.navigate(['/login']);
      },
      error => {
        this.toastr.error('Zauzeto korisničko ime.');
      }
    );
  }
}
