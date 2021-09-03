import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { faWindows } from "@fortawesome/free-brands-svg-icons";
import { TokenService } from "src/app/services/token.service";
@Injectable() 
export class LogoutUseCase{
    constructor(private router: Router, private tokenService: TokenService){}
    
    logout(){
        localStorage.removeItem('id');
        this.tokenService.clearTokenAndExpireTime(); 
        this.router.navigate(['/'])
    }
}
