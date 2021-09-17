import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Classroom } from '../models/Classroom';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {
  private apiUrl = 'http://localhost:8080'; 
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  constructor(private httpClient: HttpClient) { }
  
  
  getClassrooms() : Observable<Classroom[]> {
    return this.httpClient.get<Classroom []>(`${this.apiUrl}` + '/api/classrooms', {headers: this.headers})
  }

  addClassroom(classroom: Classroom): Observable<Classroom> {
    return this.httpClient.post<Classroom>(`${this.apiUrl}` + '/api/classrooms', classroom, {headers: this.headers} )
  }


}
