import { Injectable } from '@angular/core';
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class ReportService {
  constructor(private http: HttpClient) { }
  headers: HttpHeaders = new HttpHeaders({
    'Content-Type' : 'application/xml'});

createReport(): Observable < any > {
    return this.http.get(`${environment.baseUrl}/izvestaj/create`,
     {headers: this.headers, responseType: 'text'});
  }

toHtml(id: String){
  const headersJson = new HttpHeaders({
    'Content-Type': 'application/json'});

  return this.http.get(`${environment.baseUrl}/izvestaj/` + id ,{headers: headersJson, responseType: 'text'});
}


}
