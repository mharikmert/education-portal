import { JsonpInterceptor } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  username?:string; 
  password?:string; 

  errorType?: string; 
  btnClicked: boolean = false; 


  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }
  onSubmit(){
    this.btnClicked = true; 

    if(!this.username || !this.password){
      this.errorType = 'empty-data'
      setTimeout( () => this.btnClicked = false, 1500)
      return; 
    }

    let resp  = this.authService.login(this.username, this.password)
    console.log(resp)
  }
}
