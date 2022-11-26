import { Component, OnInit } from '@angular/core';
import { Classroom } from 'src/app/models/Classroom';
import { Lecture, LectureHour } from 'src/app/models/Lecture';
import { Section } from 'src/app/models/Section';
import { User } from 'src/app/models/User';
import { ClassroomService } from 'src/app/services/classroom.service';
import { LectureService } from 'src/app/services/lecture.service';
import { TeacherService } from 'src/app/services/teacher.service';
import { constants } from 'src/app/utils/constants';
import { Dialog } from 'src/app/common/usecase/dialog-usecase';
@Component({
  selector: 'app-assign-lecture',
  templateUrl: './assign-lecture.component.html',
  styleUrls: ['./assign-lecture.component.css']
})
export class AssignLectureComponent implements OnInit {

  classroom: Classroom = {}
  lectures: Lecture[] = [];
  teachers: User[] = [];
  days: string[] = constants.DAYS;
  lectureHours: LectureHour[] = constants.LECTURE_HOURS
  section: Section = {}


  constructor(private classroomService: ClassroomService,
    private lectureService: LectureService,
    private teacherService: TeacherService,
    private dialog: Dialog) { }

  ngOnInit(): void {
    //subscribe the observable BehaviorSubject in ClassroomService (sharedClassroom) to fetch last set classroom  
    this.classroomService.sharedClassrom.subscribe(classroom => this.classroom = classroom);
    this.lectureService.getLectures().subscribe(lectures => this.lectures = this.filterLectures(lectures));
    this.teacherService.getTeachers().subscribe(teachers => this.teachers = teachers);
    this.section.classroom = this.classroom;

  }

  //Angular lifecycle hook: ngDoCheck() -> It is called every time a change is detected after ngOnInit() and ngOnChanges()
  // ngDoCheck(): void { }

  submit = () => {
    const request = this.classroomService.assignLecture(this.section)

    request.subscribe(response => {
      if (response.status === 201) {
        this.dialog?.openDialog(constants.ASSIGN_LECTURE_SUCCESS);
        const interval = setInterval(() => {
          this.dialog?.closeDialog()
          clearInterval(interval);
        }, 2000);
      }
    })
  };

  filterLectures(lectures: Lecture[]): Lecture[] {
    switch (this.classroom.grade) {
      case 9: return this.filterLecture(lectures, '101');
      case 10: return this.filterLecture(lectures, '202');
      case 11: return this.filterLecture(lectures, '303');
      case 12: return this.filterLecture(lectures, '404');
      default: { console.log('No lecture filtered!, lectures ->', lectures); return [] };
    }
  }
  filterLecture(lectures: Lecture[], lectureCode: string) {
    return lectures.filter(lecture => lecture.lectureCode?.includes(lectureCode));
  }
}