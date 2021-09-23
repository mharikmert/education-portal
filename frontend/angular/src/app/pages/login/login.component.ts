import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoggedInService } from 'src/app/services/logged-in.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  username:string = ''; 
  password:string = ''; 
  errorType?: string; //should refactor as boolean  
  btnClicked: boolean = false; 
  showPassword:boolean = false;

  constructor(private authService: AuthService, 
    private router: Router, 
    private tokenService: TokenService,
    private loggedInService: LoggedInService) { }

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

    this.authService.login(this.username, this.password).subscribe((response) => {
      console.log(response)
      const token = (<any>response).accessToken;     
      //puts the JWT to local storage 
      this.tokenService.setToken(token)
      
      localStorage.setItem('username', this.username);

      this.loggedInService.setLoggedInUser(this.username)

      this.btnClicked = false
      this.router.navigate(['/admin-menu'])
    
    }, err => {
      this.errorType = 'any-error'
      this.btnClicked = false;
      setTimeout( () => {this.errorType = '' }, 1500)
    })
  }
}
