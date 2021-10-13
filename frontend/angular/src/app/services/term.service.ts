import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Term } from '../models/Term';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TermService {

  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  
  constructor(private httpClient: HttpClient) { }

  getAdmin = () =>  this.httpClient.get(`${environment.apiUrl}/api/users/by/username`+ localStorage.getItem('username'),{headers: this.headers});

  addTerm(term: Term) {
    return this.httpClient.post<Term>(`${environment.apiUrl}/api/terms`,term, {headers: this.headers})  
  }

  getTerms(): Observable<Term []> {
    return this.httpClient.get<Term []>(`${environment.apiUrl}/api/terms`, {headers: this.headers}); 
  }
  
  updateTerms(terms : Term[]): Observable<Term []> {
    return this.httpClient.post<Term []>(`${environment.apiUrl}/api/updateTerms`, terms, {headers: this.headers}); 
  } 
}
