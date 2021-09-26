import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/Classroom';
import { Lecture } from 'src/app/models/Lecture';
import { Section } from 'src/app/models/Section';
import { User } from 'src/app/models/User';
import { ClassroomService } from 'src/app/services/classroom.service';
import { LectureService } from 'src/app/services/lecture.service';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-assign-lecture',
  templateUrl: './assign-lecture.component.html',
  styleUrls: ['./assign-lecture.component.css']
})
export class AssignLectureComponent implements OnInit {

  classroom: Classroom = {}
  lectures: Lecture [] = [];
  teachers: User [] = [];
  days: string [] =  ['Pazartesi', 'Sali', 'Carsamba', 'Persembe', 'Cuma'];
  startingTime: string [] = ['09:00', '10:00','11:00','12:00','13:00','14:00','15:00', '16:00','17:00','18:00','19:00','20:00','21:00']
  section: Section = {}
  
  constructor(private classroomService: ClassroomService,
     private lectureService: LectureService,
     private teacherService: TeacherService) { 
  }

  ngOnInit(): void {
    //subscribe the observable BehaviorSubject in ClassroomService (sharedClassroom) to fetch last set classroom  
    this.classroomService.sharedClassrom.subscribe(classroom => this.classroom = classroom);
    this.lectureService.getLectures().subscribe(lectures => this.lectures = lectures);
    this.teacherService.getTeachers().subscribe(teachers => this.teachers = teachers);
    this.section.classroom = this.classroom
  }
  submit(){
    this.classroomService.assignLecture(this.section).subscribe();
  }


}
