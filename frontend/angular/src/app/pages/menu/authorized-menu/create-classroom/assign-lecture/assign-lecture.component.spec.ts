import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignLectureComponent } from './assign-lecture.component';

describe('AssignLectureComponent', () => {
  let component: AssignLectureComponent;
  let fixture: ComponentFixture<AssignLectureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignLectureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignLectureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
