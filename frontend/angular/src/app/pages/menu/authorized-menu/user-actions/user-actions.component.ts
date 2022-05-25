import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Parent } from 'src/app/models/Parent';
import { Student } from 'src/app/models/Student';
import { Teacher } from 'src/app/models/Teacher';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-actions',
  templateUrl: './user-actions.component.html',
  styleUrls: ['./user-actions.component.css']
})
export class UserActionsComponent implements OnInit {
  isChecked : boolean = false;
  isValidChecked: boolean = true;
  userTypes = ['Öğrenci','Veli','Öğretmen'];
  radioButton: string = 'radioButton'
  users: User [] = [];
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getUsers('users');
  }

  getUsers = (path: string) => this.userService.getUsers(path).subscribe( users => this.users = users)

  filterUsers = (userType : string) => this.users = this.users.filter( users => users.type === userType)
  
  assignClassroom(user: User){
    this.userService.nextUser(user);
    this.router.navigate(['assign-classroom']);
  }

  redirectDetails(user: User){
    switch(user.type){
      case 'Öğrenci':
        this.userService.nextUser(<Student>user); break;
      case 'Veli':
        this.userService.nextUser(<Parent>user); break;
      case 'Öğretmen':
        this.userService.nextUser(<Teacher>user); break;
    }
    this.router.navigate(['user-details']);
  }
}
