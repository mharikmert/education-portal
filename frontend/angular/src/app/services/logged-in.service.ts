import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggedInService {
  getLoggedInUser(){
    localStorage.getItem('id');
  }
  setLoggedInUser(id: string){
    if(id === '') return; 
    else localStorage.setItem('id', id)
  }
}
