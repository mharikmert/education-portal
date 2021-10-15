import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {
  roles: string[] = []
constructor(private userService :UserService, public router: Router) { }

  ngOnInit(): void {
    this.userService.sharedRoles.subscribe(roles => this.roles = roles)
  }
}
