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
  days: string [] =  ['Pazartesi', 'Sali', 'Carsamba', 'Persembe', 'Cuma'];
  startingTime: string [] = ['09:00', '10:00','11:00','12:00','13:00','14:00','15:00', '16:00','17:00','18:00']//,'19:00','20:00','21:00']

  ngOnInit(): void {
    this.classroomService.sharedClassrom.subscribe(classroom => this.classroom = classroom);
    this.classroomService.getSchedule(this.classroom.id).subscribe(section => this.schedule = section);
  }

  filterSchedule(startingTime: string, day: string){
    let lectureName;
    const filterSchedule: Section [] = this.schedule.filter( schedule => schedule.startingTime === startingTime && schedule.day === day);

    if(filterSchedule.length != 0)
      filterSchedule.map(element => lectureName = element.lecture?.name );
    
    return lectureName;
  }


}
