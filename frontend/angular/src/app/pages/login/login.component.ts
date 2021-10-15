import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { LoggedInService } from 'src/app/services/logged-in.service';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

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
    private loggedInService: LoggedInService,
    private userService: UserService) { }

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

    this.authService.login(this.username, this.password).subscribe(response => {
      console.log(response) //response includes username, user authorities and access token  
      const token = (<any>response).accessToken;     
      //puts the JWT to local storage 
      this.tokenService.setToken(token)
      this.loggedInService.setLoggedInUser(this.username)

      this.btnClicked = false
      //only admins have access, not available for all users 
      // this.userService.getUserByUsername(this.loggedInService.getLoggedInUser()).subscribe( user => {
      //   }

        //get roles from the login response
        let roles = (<any>response).authorities;

        //one role condition 
        if(roles.length === 1){ 
         const role = roles[0].split('_')[1].toLowerCase(); // role -> admin, student, teacher, parent
        //  console.log('curront role -> ', role)
         this.router.navigate([`${role}-menu`])
        }
         //multiple roles condition
        else {
            //share roles with loggedin component 
            this.userService.nextRole(roles);
            this.router.navigate(['logged-in'])
        }
    
    }, err => {
      this.errorType = 'any-error'
      this.btnClicked = false;
      setTimeout( () => {this.errorType = '' }, 1500)
    })
  }
}
