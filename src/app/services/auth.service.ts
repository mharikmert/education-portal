import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080'
  constructor(private httpClient: HttpClient) { }

  login(username: string, password: string){
    const headers = new HttpHeaders({Authorization: 'Basic' + btoa(username + ':' + password)})
    return this.httpClient.get(this.apiUrl, {headers, responseType : 'text' as 'json'})
  }
}
