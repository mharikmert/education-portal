import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from 'src/app/models/Classroom';
import { User } from 'src/app/models/User';
import { ClassroomService } from 'src/app/services/classroom.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-assing-classroom',
  templateUrl: './assing-classroom.component.html',
  styleUrls: ['./assing-classroom.component.css']
})
export class AssingClassroomComponent implements OnInit {
  user: User = {}
  classrooms: Classroom [] = []
  classroom : Classroom ={}

  constructor(private userService: UserService, private router: Router, private classroomService : ClassroomService) { }

  ngOnInit(): void {
    this.userService.sharedUser.subscribe(user => this.user = user);
    this.classroomService.getClassrooms().subscribe(classrooms => this.classrooms = classrooms);
  }
  
  assignClassroom(){
    if(this.user.classroom == this.classroom){
      alert("User is already assigned to this classroom")
      return;
    }
    const request = this.userService.assignClassroom(this.user.id, this.classroom);

    request.subscribe( ( req ) => {
      if(req.status == 200){
        alert("Classroom assigned successfully")
      }
    })
  }
  localError(){
    throw new Error("Local error")
  }

}
