import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class ReportService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/xml'});

    constructor(
        private http: HttpClient
    ) {}

    toHtml(id: String){
        const headersJson = new HttpHeaders({
          'Content-Type': 'application/json'});
       return this.http.get(`${environment.baseUrl}/${environment.apiReport}` + id ,{headers: headersJson, responseType: 'text'});
    }
   
    getAll() :Observable<any>{
        return this.http.get(`${environment.baseUrl}/${environment.apiReport}all`,
        {headers: this.headers, responseType: 'text'}); 
    }

    /*
    getSearchRequests(search: string): Observable<any> {
        return this.http.get(`${environment.baseUrl}/${environment.apiDecision}searchRequests/${search}`,
           {headers: this.headers, responseType: 'text'});
      
    }
    getMetadataSearchRequests(search: any): Observable<any> {
    return this.http.post(`${environment.baseUrl}/${environment.apiDecision}searchByMetadata`, search,
        {headers: this.headers, responseType: 'text'});
    }
    */
}