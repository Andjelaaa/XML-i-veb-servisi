import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { RequestDTO } from '../../model/RequestDTO';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class RequestService {
  constructor(private http: HttpClient) { }
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type' : 'application/xml'});

sendRequest(request: any): Observable < any > {
    return this.http.post(`${environment.baseUrl}/${environment.newRequest}`, request,
     {headers: this.headers, responseType: 'text'});
  }

getUserRequests(username: string): Observable<any> {
  return this.http.get(`${environment.baseUrl}/api/zahtev/userRequests/${username}`,
     {headers: this.headers, responseType: 'text'});

}

toHtml(id: String){
  const headersJson = new HttpHeaders({
    'Content-Type': 'application/json'});

  return this.http.get(`${environment.baseUrl}/${environment.zahtev}/` + id ,{headers: headersJson, responseType: 'text'});
}
}
