import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLectureComponent } from './create-lecture.component';

describe('CreateLectureComponent', () => {
  let component: CreateLectureComponent;
  let fixture: ComponentFixture<CreateLectureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateLectureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLectureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
