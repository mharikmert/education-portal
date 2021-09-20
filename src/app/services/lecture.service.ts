import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Classroom } from '../models/Classroom';
import { Lecture } from '../models/Lecture';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private apiUrl = 'http://localhost:8080'; 
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  constructor(private httpClient: HttpClient) { }
  
  
  getLectures() : Observable<Lecture[]> {
    return this.httpClient.get<Lecture []>(`${this.apiUrl}` + '/api/lectures', {headers: this.headers})
  }
  
  addLecture(lecture: Lecture): Observable<Classroom> {
    return this.httpClient.post<Lecture>(`${this.apiUrl}` + '/api/lectures', lecture, {headers: this.headers} )
  }


}
