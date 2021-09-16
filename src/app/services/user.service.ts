import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080'; 
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  
  constructor(private httpClient: HttpClient) { }
  
  getUsers() : Observable<User []> {
    return this.httpClient.get<User []>(`${this.apiUrl}` + '/api/users', {headers: this.headers})
  }

}
