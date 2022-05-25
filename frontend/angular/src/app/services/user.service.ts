import { HttpHeaders, HttpClient, HttpResponse} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Classroom } from '../models/Classroom';
import { User } from '../models/User';
import { environment } from 'src/environments/environment';
import { Role } from '../models/Role';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Authorization':'Bearer ' + localStorage.getItem('token')
  })
  

  //shares users between unrelated components 
  private user : BehaviorSubject<User>= new BehaviorSubject<User>( new User());
  public sharedUser = this.user.asObservable();
  private roles : BehaviorSubject<string []>= new BehaviorSubject<string []> ([]);
  public sharedRoles = this.roles.asObservable();

  constructor(private httpClient: HttpClient) { }
  
  getUsers(path: string) : Observable<User []> {
    return this.httpClient.get<User []>(`${environment.apiUrl}/api/${path}`, {headers: this.headers})
  }

  assignClassroom(id: bigint | undefined, classroom: Classroom){
    return this.httpClient.post(`${environment.apiUrl}/api/students/${id}/assignClassroom`, classroom, {headers: this.headers, observe: 'response'});
  }
  updateRoles(id: bigint | undefined, roles: Role [] | undefined) : Observable<User>{
    return this.httpClient.post<User>(`${environment.apiUrl}/api/users/${id}/roles`, roles, {headers: this.headers});
  }
  nextUser(user: User){
    this.user.next(user);
  }
  nextRole(authorities: string[]){
    this.roles.next(authorities)
  }
  

}
