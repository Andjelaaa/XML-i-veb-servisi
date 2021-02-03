import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class AppealDecisionService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    sendAppealDecision(request: any): Observable < any > {
        return this.http.post(`${environment.baseUrl}/${environment.appealDecision}`, request,
         {headers: this.headers, responseType: 'text'});
      }
      toHtml(id: String){
        const headersJson = new HttpHeaders({
          'Content-Type': 'application/json'});
       return this.http.get(`${environment.baseUrl}/${environment.apiAppealDecision}` + id ,{headers: headersJson, responseType: 'text'});
    }
    getUserAppealDecision(username: string): Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealDecision}userAppeal/${username}`,
        {headers: this.headers, responseType: 'text'}); 
    }

    getAll() :Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealDecision}all`,
        {headers: this.headers, responseType: 'text'}); 
    }
}
