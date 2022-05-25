import { Component, OnInit } from '@angular/core';
import { Role } from 'src/app/models/Role';
import { Student } from 'src/app/models/Student';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  user: User = {}
  student: Student = {}
  editBtnClicked: boolean = false;
  addRoleBtnClicked: boolean = false; 

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.sharedUser.subscribe(user => this.user = user);
  }

  doesUserHaveRole(role: Role): boolean | undefined {
    return this.user?.roles?.includes(role);
  }

  getRolesUserDoesntHave(){
    return Object.values(Role).filter(role => !this.doesUserHaveRole(role));
  }

  removeRole(role: Role){
    this.user.roles = this.user?.roles?.filter(r => r !== role);
  }

  updateRoles(){ 
    this.userService.updateRoles(this.user.id, this.user.roles).subscribe();
    this.editBtnClicked = false;
  }
}
