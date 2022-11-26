import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/Classroom';
import { LectureHour } from 'src/app/models/Lecture';
import { Section } from 'src/app/models/Section';
import { ClassroomService } from 'src/app/services/classroom.service';
import { constants } from 'src/app/utils/constants';

@Component({
  selector: 'app-classroom-schedule',
  templateUrl: './classroom-schedule.component.html',
  styleUrls: ['./classroom-schedule.component.css']
})
export class ClassroomScheduleComponent implements OnInit {

  constructor(private classroomService: ClassroomService) { }
  classroom: Classroom = {}
  schedule: Section[] = []
  days: string[] = constants.DAYS;
  lectureHours: LectureHour[] = constants.LECTURE_HOURS

  ngOnInit(): void {
    this.classroomService.sharedClassrom.subscribe(classroom => this.classroom = classroom);
    this.classroomService.getSchedule(this.classroom.id).subscribe(section => this.schedule = section);
  }

  filterSchedule(startingTime: string, day: string) {
    let lectureName;
    const filterSchedule: Section[] = this.schedule.filter(schedule => schedule.startingTime === startingTime && schedule.day === day);

    if (filterSchedule.length != 0)
      filterSchedule.map(element => lectureName = element.lecture?.name);

    return lectureName;
  }


}
