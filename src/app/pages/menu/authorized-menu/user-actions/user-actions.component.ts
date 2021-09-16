import { Component, OnInit } from '@angular/core';
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
  users: User [] = [];
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.userService.getUsers().subscribe( users => this.users = users)
  }

  checkedUserType(value : boolean){
    console.log(this.isChecked)
    console.log(value)
  }
}
