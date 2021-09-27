import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-assing-classroom',
  templateUrl: './assing-classroom.component.html',
  styleUrls: ['./assing-classroom.component.css']
})
export class AssingClassroomComponent implements OnInit {
  user: User = {}

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.sharedUser.subscribe(user => this.user = user);
  }
  

}
