import { XmlParser } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user-service/user.service';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
    form!: FormGroup;

    constructor(
        private fb: FormBuilder,
        private userService: UserService,
        private router: Router,
        private toastr: ToastrService
    ) {
        this.createForm();
    }
    ngOnInit(): void {
      //  this.userService.initializeUsers().subscribe();
    }
    createForm(): void{
        this.form = this.fb.group({
            username : [null, Validators.required],
            password: [null, Validators.required]
    });
    }
    submit(): void {
        const auth: any = {};
        auth.username = this.form.value.username;
        auth.password = this.form.value.password;
        const signInUser = { _declaration:
            { _attributes: { version: '1.0', encoding: 'utf-8' } },
          root: {
            username: { _text: '' }, password: { _text: '' } } };
        signInUser.root.username = auth.username;
        signInUser.root.password = auth.password;
        const convert = require('xml-js');
        const signInUserXML = convert.js2xml(signInUser, {compact: true, ignoreComment: true, spaces: 4});
        this.userService.login(signInUserXML).subscribe(
          result => {
            const userToken = JSON.parse(convert.xml2json(result, {compact: true, spaces: 4}));
            localStorage.setItem('user', userToken.UserTokenStateDTO.accessToken._text);
            this.router.navigate(['/home']);
          },
          error => {
            this.toastr.error('Pogrešno korisničko ime ili šifra.');
          }
        );
      }

}
