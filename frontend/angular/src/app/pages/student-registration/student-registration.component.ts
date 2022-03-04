import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/City';
import { environment } from 'src/environments/environment';
import { District } from 'src/app/models/Districts';
@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['./student-registration.component.css']
})
export class StudentRegistrationComponent implements OnInit {

  selectedCity: City = {}; 
  hasInternet? :boolean;
  cities: City[] = [];
  districts: District[] = []; 

  studentFormGroup!: FormGroup;
  submitted: boolean = false;

  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getCities().subscribe(cities => this.cities = cities);
    this.studentFormGroup = this.formBuilder.group({
      'firstName': new FormControl(null, Validators.required),
      'lastName': new FormControl(null, Validators.required),
      'tckn': new FormControl(null, Validators.required),
      'birthdate': new FormControl(null, Validators.required),
      'grade': new FormControl(null, Validators.required),
      'section': new FormControl(null, Validators.required),
      'school': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone' : new FormControl(
        null,
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
      'terms' : new FormControl(null, Validators.requiredTrue )

    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.studentFormGroup.controls;
  }

  onSubmit(): void {
    console.log(this.selectedCity.plateNo)
    this.submitted = true;
    if (this.studentFormGroup.valid) {
      console.log(JSON.stringify(this.studentFormGroup.value, null, 2));
      this.httpClient.post(`${environment.apiUrl}/api/students`, this.studentFormGroup.value).subscribe();
    } 
    else {
      console.log(this.studentFormGroup, this.studentFormGroup.errors);
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
