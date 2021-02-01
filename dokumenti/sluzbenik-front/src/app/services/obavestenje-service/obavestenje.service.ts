import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";


@Injectable({
    providedIn: 'root'
})
export class ObavestenjeService {
    private headers = new HttpHeaders({
        'Content-Type': 'application/json'});

    constructor(
        private http: HttpClient
    ) {}

    toHtml(id: String){
        return this.http.get(`${environment.baseUrl}/${environment.obavestenje}/` + id , {headers: this.headers, responseType: 'text'});
    }
}