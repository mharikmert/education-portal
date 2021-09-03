import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  getToken(): string | null{ 
    return localStorage.getItem('token');
  }

  setToken(token: string){
    localStorage.setItem('token', token); 
    localStorage.setItem('expireTime', this.extractExpireTimeFromToken(token));
  }

  clearTokenAndExpireTime(){
    localStorage.removeItem('token')
    localStorage.removeItem('expireTime')
  }
  
  getExpireTime(): string | null {
    return localStorage.getItem('expireTime');
  }

  extractExpireTimeFromToken(token: string): string {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    const exp = JSON.parse(window.atob(base64)).exp * 1000;
    return exp.toString();
  }
}



