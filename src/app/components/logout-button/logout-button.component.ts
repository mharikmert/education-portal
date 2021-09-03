import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogoutUseCase } from 'src/app/common/usecase/logout-usecase';

@Component({
  selector: 'app-logout-button',
  templateUrl: './logout-button.component.html',
  styleUrls: ['./logout-button.component.css']
})
export class LogoutButtonComponent implements OnInit {

  constructor(private router: Router, public logoutService: LogoutUseCase) { }
  ngOnInit(): void {}
}
