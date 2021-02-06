import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { User } from '../../model/User';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class AppealSilenceService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    sendAppealSilence(request: any): Observable < any > {
        return this.http.post(`${environment.baseUrl}/${environment.appealSilence}`, request,
         {headers: this.headers, responseType: 'text'});
      }
      toHtml(id: String){
        const headersJson = new HttpHeaders({
          'Content-Type': 'application/json'});
       return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}` + id ,{headers: headersJson, responseType: 'text'});
    }

    getUserAppealSilence(username: string): Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}userAppeal/${username}`,
        {headers: this.headers, responseType: 'text'}); 
    }

    getAll() :Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}all`,
        {headers: this.headers, responseType: 'text'}); 
    }

    getNew() :Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}new`,
        {headers: this.headers, responseType: 'text'}); 
    }
    
    getSearchRequests(search: string): Observable<any> {
        return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}searchRequests/${search}`,
           {headers: this.headers, responseType: 'text'});
      
    }
    getMetadataSearchRequests(search: any): Observable<any> {
    return this.http.post(`${environment.baseUrl}/${environment.apiAppealSilence}searchByMetadata`, search,
        {headers: this.headers, responseType: 'text'});
    }
    sendMail(id: string) {
         return this.http.get(`${environment.baseUrl}/${environment.apiAppealSilence}sendMail/` + id ,{responseType: 'text'});
    }
}
