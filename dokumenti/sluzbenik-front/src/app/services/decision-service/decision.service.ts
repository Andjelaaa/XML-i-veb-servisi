import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class DecisionService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    toHtml(id: String){
        const headersJson = new HttpHeaders({
          'Content-Type': 'application/json'});
       return this.http.get(`${environment.baseUrl}/api/resenje/` + id ,{headers: headersJson, responseType: 'text'});
    }
    getAll() :Observable<any>{
        return this.http.get(`${environment.baseUrl}/api/resenje/all`,
        {headers: this.headers, responseType: 'text'}); 
    }
    getSearchRequests(search: string): Observable<any> {
        return this.http.get(`${environment.baseUrl}/api/resenje/searchRequests/${search}`,
           {headers: this.headers, responseType: 'text'});
      
    }

}
