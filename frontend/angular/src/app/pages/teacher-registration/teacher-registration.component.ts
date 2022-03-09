import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/City';
import { Term } from 'src/app/models/Term';
import { Lecture } from 'src/app/models/Lecture';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-teacher-registration',
  templateUrl: './teacher-registration.component.html',
  styleUrls: ['./teacher-registration.component.css']
})

export class TeacherRegistrationComponent implements OnInit {

  term: Term = {
    name: 'Term 1',
    startDate: new Date('2020-01-01'),
    endDate: new Date('2020-06-30')
  }

  subject: string = ''; 
  cities?: City[];

  lecture: Lecture = {
    name: this.subject
  }
  teacherFormGroup!: FormGroup;
  submitted: boolean = false;

  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.getCities().subscribe(cities => this.cities = cities);
    this.teacherFormGroup = this.formBuilder.group({
      'firstName': new FormControl(null, Validators.required),
      'lastName': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone' : new FormControl(
        null,
        [
          Validators.required,
          Validators.pattern('^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$')
        ]),
      'city': new FormControl(null, Validators.required),
      'notes': new FormControl(null),
      'terms' : new FormControl(null, Validators.requiredTrue ),
      'term': this.term,
      'lecture': this.lecture


    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.teacherFormGroup.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.teacherFormGroup.valid && this.subject) {
      console.log(JSON.stringify(this.teacherFormGroup.value, null, 2));

      const request = this.httpClient.post(`${environment.apiUrl}/api/teachers`, this.teacherFormGroup.value, {observe: 'response'}); 
      request.subscribe( response => {
          console.log('this is the response code of the request' , response.status)
          if(response.status === 201){
            this.submitted = false; 
            console.log('kaydiniz alinmistir.. ')
            this.router.navigate(['/']);
          }
      }, error => {
        if(error.status === 400){
          console.log('this is the error message from bad request', error)
        }
        else if(error.status === 409){
          console.log('this is the error message from already registered user' , error)
        }
        else if(error.status === 500){
          console.log('this is the error message from interval server error ' , error)
        }
        // deactivates spinner 
        this.submitted = false; 
      });
    } 
    else {
      console.log('Form is invalid');
      return;
    }
  }

  onReset(): void {
    this.submitted = false;
    this.teacherFormGroup.reset();
  }

  getCities() : Observable<City[]>{
    return this.httpClient.get<City[]>(`${environment.apiUrl}/api/cities`); 
  }




}
