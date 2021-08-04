import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient, private router: Router) { }

  private apiUrl = 'https://localhost:5001/api/auth'

  login(username: string, password: string) : Observable<HttpClientModule> {
    // const httpOptions = {
    //   headers : new HttpHeaders({
    //     'Content-type' : 'application/json',
    //     'Authorization': 'Basic ' + btoa(username + ':' + password)
    //   }) 
    // }
    const creds = { 
      "username": username,
      "password": password
    }
    
    return this.httpClient.post(this.apiUrl, creds)
  }
}
