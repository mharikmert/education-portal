import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/Classroom';
import { ClassroomService } from 'src/app/services/classroom.service';

@Component({
  selector: 'app-create-classroom',
  templateUrl: './create-classroom.component.html',
  styleUrls: ['./create-classroom.component.css']
})
export class CreateClassroomComponent implements OnInit {

  classrooms: Classroom [] = [];
  classroom: Classroom = {}
  strGrade: string = ''; 
  strCapacity: string = ''; 
  constructor(private classroomService: ClassroomService) { }

  ngOnInit(): void {
    this.getClassroms();
  }

  getClassroms = () => this.classroomService.getClassrooms().subscribe( classrooms => this.classrooms = classrooms)

  addClassroom = (classroom: Classroom) => {
    this.classroom.capacity = parseInt(this.strCapacity);
    this.classroom.grade = parseInt(this.strGrade);
    console.log(this.classroom)
    this.classroomService.addClassroom(classroom).subscribe(classrom => this.classrooms.push(classrom));
  }  

}
