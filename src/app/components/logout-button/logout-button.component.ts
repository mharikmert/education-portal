import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout-button',
  templateUrl: './logout-button.component.html',
  styleUrls: ['./logout-button.component.css']
})
export class LogoutButtonComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {}

  performLogout(){
    localStorage.removeItem("JWT");
    this.router.navigate(['login'])
  }
}
