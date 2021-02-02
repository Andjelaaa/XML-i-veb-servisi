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
}
