import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherRegistrationComponent } from './teacher-registration.component';

describe('TeacherRegistrationComponent', () => {
  let component: TeacherRegistrationComponent;
  let fixture: ComponentFixture<TeacherRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeacherRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
