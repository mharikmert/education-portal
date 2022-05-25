import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Classroom } from '../models/Classroom';
import { Lecture } from '../models/Lecture';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  constructor(private httpClient: HttpClient) { }
  
  
  getLectures() : Observable<Lecture[]> {
    return this.httpClient.get<Lecture []>(`${environment.apiUrl}/api/lectures`, {headers: this.headers})
  }
  
  addLecture(lecture: Lecture): Observable<Classroom> {
    return this.httpClient.post<Lecture>(`${environment.apiUrl}/api/lectures`, lecture, {headers: this.headers} )
  }

  deleteLecture(id: bigint | undefined){
    return this.httpClient.delete(`${environment.apiUrl}/api/lectures/${id}`, {headers: this.headers, observe: 'response'} )
  }


}
