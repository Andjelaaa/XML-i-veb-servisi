import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { RequestDTO } from '../../model/RequestDTO';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class InformationService {
  constructor(private http: HttpClient) { }
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type' : 'application/xml'});

sendInformation(request: any): Observable < any > {
    return this.http.post(`${environment.baseUrl}/${environment.newInformation}`, request,
     {headers: this.headers, responseType: 'text'});
  }

  getAllInformations(): Observable<any> {
    return this.http.get(`${environment.baseUrl}/${environment.allInformations}`,
     {headers: this.headers, responseType: 'text'});
  }

  getUserInformations(username: string): Observable<any> {
    return this.http.get(`${environment.baseUrl}/${environment.userInformations}/${username}`,
     {headers: this.headers, responseType: 'text'});
  }
}
