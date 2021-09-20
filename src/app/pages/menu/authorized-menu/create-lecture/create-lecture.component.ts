import { Component, OnInit } from '@angular/core';
import { Lecture } from 'src/app/models/Lecture';
import { LectureService } from 'src/app/services/lecture.service';

@Component({
  selector: 'app-create-lecture',
  templateUrl: './create-lecture.component.html',
  styleUrls: ['./create-lecture.component.css']
})
export class CreateLectureComponent implements OnInit {

  lecture: Lecture = {};
  lectures: Lecture [] = [];
  constructor(private lectureService: LectureService) { }

  ngOnInit(): void {
    this.getLectures();
  }
  getLectures = () => this.lectureService.getLectures().subscribe(lectures => this.lectures = lectures);

  addLecture = (lecture: Lecture) => this.lectureService.addLecture(lecture).subscribe(lecture => this.lectures.push(lecture));

}
