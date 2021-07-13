import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8080/api/auth'

  login(username: string, password: string) : Observable<HttpClientModule> {
    const httpOptions = {
      headers : new HttpHeaders({
        'Content-type' : 'application/json',
        'Authorization': 'Basic ' + btoa(username + ':' + password)
      }) 
    }
    return this.httpClient.get(this.apiUrl, httpOptions)
  }

}
