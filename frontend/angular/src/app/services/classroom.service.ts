import { HttpHeaders, HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Classroom } from '../models/Classroom';
import { Section } from '../models/Section';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {


  //shares classroom objects between unrelated components 
  private classroom: BehaviorSubject<Classroom> = new BehaviorSubject<Classroom>(new Classroom());
  public sharedClassrom = this.classroom.asObservable();

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  })
  constructor(private httpClient: HttpClient) { }


  getClassrooms(): Observable<Classroom[]> {
    return this.httpClient.get<Classroom[]>(`${environment.apiUrl}/api/classrooms`, { headers: this.headers })
  }

  addClassroom(classroom: Classroom): Observable<Classroom> {
    return this.httpClient.post<Classroom>(`${environment.apiUrl}/api/classrooms`, classroom, { headers: this.headers })
  }

  getClassroomByName(classroomName: string | undefined): Observable<Classroom> {
    return this.httpClient.get<Classroom>(`${environment.apiUrl}/api/classrooms/byName`, {
      params: { name: `${classroomName}` },
      headers: this.headers
    });
  }

  assignLecture(section: Section): Observable<HttpResponse<Section>> {
    return this.httpClient.post<Section>(`${environment.apiUrl}/api/classrooms/assignLecture`, section, { headers: this.headers, observe: 'response' })
  }

  getSchedule(classroomId: bigint | undefined): Observable<Section[]> {
    return this.httpClient.get<Section[]>(`${environment.apiUrl}/api/classrooms/${classroomId}/schedule`, { headers: this.headers });
  }

  nextClassroom(classroom: Classroom) {
    this.classroom.next(classroom);
  }

}
