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
  onDeleteLecture(id: bigint | undefined) {
    alert("You're about to delete a lecture.") // this should be a modal 
    const request = this.lectureService.deleteLecture(id);
    
    request.subscribe( ( req ) => {
      if(req.status == 200){
        this.lectures = this.lectures.filter(lecture => lecture.id != id);
      }
      else {
        alert("Error deleting lecture")
      }
    })
  }
  getLectures = () => this.lectureService.getLectures().subscribe(lectures => this.lectures = lectures);

  addLecture = (lecture: Lecture) => {
    // check if lecture is valid
    if(lecture.name && lecture.lectureCode){
    this.lectureService.addLecture(lecture).subscribe(lecture => this.lectures.push(lecture))
    }else {
      alert("Please fill in all fields.");
    }
  };


}
