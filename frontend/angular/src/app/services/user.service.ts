import { HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Classroom } from '../models/Classroom';
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
  

  //shares users between unrelated components 
  private user : BehaviorSubject<User>= new BehaviorSubject<User>( new User());
  public sharedUser = this.user.asObservable();

  constructor(private httpClient: HttpClient) { }
  
  getUsers(path: string) : Observable<User []> {
    return this.httpClient.get<User []>(`${this.apiUrl}/api/${path}`, {headers: this.headers})
  }

  assignClassroom(id: bigint | undefined, classroom: Classroom) : Observable<User>{
    return this.httpClient.post<User>(`${this.apiUrl}/api/students/${id}/assignClassroom`, classroom, {headers: this.headers});
  }
  nextUser(user: User){
    this.user.next(user);
  }
  

}
