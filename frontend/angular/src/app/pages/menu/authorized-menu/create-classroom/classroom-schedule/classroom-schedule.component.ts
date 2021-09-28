import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/Classroom';
import { Section } from 'src/app/models/Section';
import { ClassroomService } from 'src/app/services/classroom.service';

@Component({
  selector: 'app-classroom-schedule',
  templateUrl: './classroom-schedule.component.html',
  styleUrls: ['./classroom-schedule.component.css']
})
export class ClassroomScheduleComponent implements OnInit {

  constructor(private classroomService : ClassroomService) { }
  classroom: Classroom = {}
  schedule: Section [] = []

  ngOnInit(): void {
    this.classroomService.sharedClassrom.subscribe(classroom => this.classroom = classroom);
    this.classroomService.getSchedule(this.classroom.id).subscribe(section => this.schedule = section);
  }

}
