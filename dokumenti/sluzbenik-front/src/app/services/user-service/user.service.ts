import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class UserService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}
    login(loginData: any): Observable<any>{
        return this.http.post(`${environment.baseUrl}/${environment.login}`, loginData, {headers: this.headers, responseType: 'text'});
    }
    isLoggedIn(): boolean {
        if (!localStorage.getItem('user')) {
                return false;
        }
        return true;
    }
    register(registerData: any): Observable<any>{
        return this.http.post(`${environment.baseUrl}/${environment.register}`,
         registerData, {headers: this.headers, responseType: 'text'});
    }
    soap(): Observable<any>{
        return this.http.post(`${environment.baseUrl}/app/soap/hello`,
         {headers: this.headers, responseType: 'text'});
    }

}
