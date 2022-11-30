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
  actions: string[] = ["Edit", "Delete"]
  lectures: Lecture[] = [
    { name: "Math", lectureCode: "MATH101" },
    { name: "English", lectureCode: "ENG101" },
  ];

  tableColumns = [
    { name: 'Ders Adı', key: 'name', type: 'text' },
    { name: 'Ders Kodu', key: 'lectureCode', type: 'text' },
    { name: 'İşlemler', key: 'actions', type: 'actions' }
  ]
  columnsToDisplay: string[] = this.tableColumns.map(column => column.name);

  constructor(private lectureService: LectureService) { }

  ngOnInit(): void {
    this.getLectures();
  }
  onDeleteLecture(id: bigint | undefined) {
    alert("You're about to delete a lecture.") // this should be a modal 
    const request = this.lectureService.deleteLecture(id);

    request.subscribe((req) => {
      if (req.status == 200) {
        this.lectures = this.lectures.filter(lecture => lecture.id != id);
      }
      else {
        alert("Error deleting lecture")
      }
    })
  }

  onEditLecture(lecture: Lecture) {
    const request = this.lectureService.editLecture(lecture);

    request.subscribe((req) => {
      if (req.status == 200) {
        alert("Lecture edited successfully")
      }
      else {
        alert("Error editing lecture")
      }
    })
  }

  getLectures = () => this.lectureService.getLectures().subscribe(lectures => this.lectures = lectures);

  addLecture = (lecture: Lecture) => {
    // check if lecture is valid
    if (lecture.name && lecture.lectureCode) {
      this.lectureService.addLecture(lecture).subscribe(lecture => this.lectures.push(lecture))
    } else {
      alert("Please fill in all fields.");
    }
  };

}
