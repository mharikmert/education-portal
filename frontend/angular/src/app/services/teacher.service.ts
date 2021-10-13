import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  constructor(private httpClient: HttpClient) { }
  
  
  getTeachers() : Observable<User[]> {
    return this.httpClient.get<User []>(`${environment.apiUrl}` + '/api/teachers', {headers: this.headers})
  }
  
  addTeacher(teacher: User): Observable<User> {
    return this.httpClient.post<User>(`${environment.apiUrl}` + '/api/teachers', teacher, {headers: this.headers} )
  }


}
