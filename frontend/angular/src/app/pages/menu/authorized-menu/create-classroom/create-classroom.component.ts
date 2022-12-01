import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Classroom } from 'src/app/models/Classroom';
import { ClassroomService } from 'src/app/services/classroom.service';

@Component({
  selector: 'app-create-classroom',
  templateUrl: './create-classroom.component.html',
  styleUrls: ['../../../../components/table/table.component.css']
})
export class CreateClassroomComponent implements OnInit {

  classrooms: Classroom[] = [];
  classroom: Classroom = {};

  constructor(private classroomService: ClassroomService, private router: Router) { }

  tableColumns = [
    { name: 'Sınıf Adı', key: 'name', type: 'text' },
    { name: 'Sınıf Kapasitesi', key: 'capacity', type: 'text' },
    { name: 'Sınıf Boyutu', key: 'classroomSize', type: 'text' },
    { name: 'Sınıf Mevcudu', key: 'grade', type: 'text' },
    { name: 'İşlemler', key: 'actions', type: 'actions' }
  ]
  columnsToDisplay: string[] = this.tableColumns.map(column => column.name);

  ngOnInit(): void {
    this.getClassroms();
  }

  getClassroms = () => this.classroomService.getClassrooms().subscribe(classrooms => this.classrooms = classrooms)

  getClassroom = (name: string | undefined) => this.classroomService.getClassroomByName(name).subscribe(classroom => this.classroom = classroom);

  addClassroom = (classroom: Classroom) => {
    if (classroom.capacity && classroom.grade) {
      this.classroomService.addClassroom(classroom).subscribe(classrom => this.classrooms.push(classrom));
    } else {
      alert('Please enter classroom capacity and grade')
    }
  }

  shareClassroom(classroom: Classroom, path: string) {
    //set the current classroom as sharedClassroom 
    this.classroomService.nextClassroom(classroom);
    this.router.navigate([path])
  }

  editClassroom(classroom: Classroom, path: string) {
    this.classroomService.nextClassroom(classroom);
    this.router.navigate([path])
  }

  deleteClassroom(classroom: Classroom) {
    this.classroomService.deleteClassroom(classroom).subscribe(classroom => this.classrooms = this.classrooms.filter(c => c.id !== classroom.id));
  }
}
