import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Parent } from 'src/app/models/Parent';
import { Student } from 'src/app/models/Student';
import { Teacher } from 'src/app/models/Teacher';
import { User, UserType } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';
import { constants } from 'src/app/utils/constants';

@Component({
  selector: 'app-user-actions',
  templateUrl: './user-actions.component.html',
  styleUrls: ['./user-actions.component.css']
})
export class UserActionsComponent implements OnInit {


  userTypes: UserType[] = constants.USER_TYPES
  users: User[] = [];
  userType: string = 'users';
  tableData: any[] = []

  tableColumns = [
    { name: 'Ad', key: 'firstName', type: 'text' },
    { name: 'Soyad', key: 'lastName', type: 'text' },
    { name: 'E-posta', key: 'email', type: 'text' },
    { name: 'Telefon Numarası', key: 'phoneNumber', type: 'text' },
    { name: 'İşlemler', key: 'actions', type: 'button' }
  ]
  columnsToDisplay: string[] = this.tableColumns.map(column => column.name);

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getUsers(this.userType);
  }

  getUsers = (path: string) => {
    this.userService.getUsers(path).subscribe(users => {
      this.users = users
      this.tableData = this.users.map(user => {
        return {
          ...user,
          actions: ['edit', 'delete']
        }
      });
    })
  }

  filterUsers = (userType: string) => this.users = this.users.filter(users => users.type === userType)

  assignClassroom(user: User) {
    this.userService.nextUser(user);
    this.router.navigate(['assign-classroom']);
  }

  radioChange = (event: any) => {
    this.userType = event.value;
    console.log('radioChange', this.userType, event);
    this.getUsers(this.userType);
  }

  redirectDetails(user: User) {
    switch (user.type) {
      case 'Öğrenci':
        this.userService.nextUser(<Student>user); break;
      case 'Veli':
        this.userService.nextUser(<Parent>user); break;
      case 'Öğretmen':
        this.userService.nextUser(<Teacher>user); break;
    }
    this.router.navigate(['user-details']);
  }

  editUser(user: User) {
    console.log('edit user', user);
    switch (user.type) {
      case 'Öğrenci':
        this.userService.nextUser(<Student>user); break;
      case 'Veli':
        this.userService.nextUser(<Parent>user); break;
      case 'Öğretmen':
        this.userService.nextUser(<Teacher>user); break;
    }
    this.router.navigate(['edit-user']);
  }

  deleteUser(user: User) {
    // this.userService.deleteUser(user).subscribe(() => this.getUsers(this.userType));
    console.log('delete user', user);
  }
}