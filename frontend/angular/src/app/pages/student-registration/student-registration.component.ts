import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AbstractControl, FormBuilder, FormControl, FormGroup, MaxLengthValidator, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/City';
import { environment } from 'src/environments/environment';
import { District } from 'src/app/models/Districts';
import { Router } from '@angular/router';
import { Term } from 'src/app/models/Term';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['./student-registration.component.css']
})
export class StudentRegistrationComponent implements OnInit {

  term: Term = {
    name: '2022-2023',
    startDate: new Date('2022-09-01'),
    endDate: new Date('2023-08-31'),
    active: true
  }
  selectedCity: City = {}; 
  hasInternet: boolean = true;
  cities: City[] = [];
  districts: District[] = []; 

  studentFormGroup!: FormGroup;
  submitted: boolean = false;

  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.getCities().subscribe(cities => this.cities = cities);
    this.studentFormGroup = this.formBuilder.group({
      'firstName': new FormControl(null, Validators.required),
      'lastName': new FormControl(null, Validators.required),
      'id': new FormControl(null, Validators.required), 
      'birthDate': new FormControl(null, Validators.required),
      'grade': new FormControl(null, Validators.required),
      'section': new FormControl(null, Validators.required),
      'schoolName': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phoneNumber' : new FormControl(null, 
        [
          Validators.required,
          Validators.pattern('^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$')
        ]),
      'city': new FormControl(null, Validators.required),
      'district': new FormControl(null, Validators.required),
      'hasInternet': this.hasInternet,
      'pfirstName': new FormControl(null),
      'plastName': new FormControl(null),
      'pPhone': new FormControl(null),
      'notes': new FormControl(null, Validators.required),
      'terms' : new FormControl(null, Validators.requiredTrue),
      'term': this.term
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.studentFormGroup.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    console.table(this.studentFormGroup.value);
    if (this.studentFormGroup.valid) {
      console.log(JSON.stringify(this.studentFormGroup.value, null, 2));
      const request = this.httpClient.post(`${environment.apiUrl}/api/students`, this.studentFormGroup.value , {observe: 'response'});

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
        //deactivates spinner 
        this.submitted = false; 
      });
    }
    else {
      // console.log(this.studentFormGroup.value, this.studentFormGroup.errors);
      console.log('Form is invalid');
      return;
    }
  }

  onReset(): void {
    this.submitted = false;
    this.studentFormGroup.reset();
  }

  getCities() : Observable<City[]>{
    return this.httpClient.get<City[]>(`${environment.apiUrl}/api/cities`); 
  }

  setDistricts(cityId: number | undefined) {
    this.httpClient.get<District[]>(`${environment.apiUrl}/api/cities/${cityId}/districts`).subscribe(districts => this.districts = districts);
  }

  getValue(event: Event) {
    return parseInt((<HTMLInputElement>event.target).value);
  }
}
