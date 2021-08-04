import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  username?:string; 
  password?:string; 
  errorType?: string; //should refactor as boolean  
  btnClicked: boolean = false; 

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  onSubmit(){
    this.btnClicked = true; 

    if(!this.username || !this.password){
      this.errorType = 'empty-data'
      this.btnClicked = false
      setTimeout( () => {this.errorType = ''}, 1500)
      return; 
    }

    let resp  = this.authService.login(this.username, this.password)

    resp.subscribe((response) => {
      const token = (<any>response).token;     
      // console.log(token)
      //puts the JWT to local storage 
      localStorage.setItem("JWT", token)

      console.log(localStorage.getItem("JWT"))  

      this.btnClicked = false
      this.router.navigate(['/admin-menu'])
    
    }, err => {
      this.errorType = 'any-error'
      setTimeout( () => {this.btnClicked = false, this.errorType = '' }, 1500)
    })
  }
}
