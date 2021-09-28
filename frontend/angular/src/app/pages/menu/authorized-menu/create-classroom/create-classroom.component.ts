import { Component, OnInit , Output, EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Classroom } from 'src/app/models/Classroom';
import { ClassroomService } from 'src/app/services/classroom.service';

@Component({
  selector: 'app-create-classroom',
  templateUrl: './create-classroom.component.html',
  styleUrls: ['./create-classroom.component.css']
})
export class CreateClassroomComponent implements OnInit {

  classrooms: Classroom [] = [];
  classroom: Classroom = {};
  strGrade: string = ''; 
  strCapacity: string = ''; 
  constructor(private classroomService: ClassroomService, private router: Router) {
   }

  ngOnInit(): void {
    this.getClassroms();
  }

  getClassroms = () => this.classroomService.getClassrooms().subscribe( classrooms => this.classrooms = classrooms)

  getClassroom = (name : string | undefined) => this.classroomService.getClassroomByName(name).subscribe( classroom => this.classroom = classroom);
  
  addClassroom = (classroom: Classroom) => {
    this.classroom.capacity = parseInt(this.strCapacity);
    this.classroom.grade = parseInt(this.strGrade);
    console.log(this.classroom)
    this.classroomService.addClassroom(classroom).subscribe(classrom => this.classrooms.push(classrom));
  }  
  
  shareClassroom(classroom : Classroom, path: string){
    //set the current classroom as sharedClassroom 
    this.classroomService.nextClassroom(classroom);
    this.router.navigate([path])
  }
}
