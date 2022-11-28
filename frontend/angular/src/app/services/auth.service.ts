import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient, private router: Router) { }


  login(username: string, password: string): Observable<HttpClientModule> {
    const creds = {
      "username": username,
      "password": password
    }

    return this.httpClient.post(`${environment.apiUrl}/api/auth`, creds)
  }
}
