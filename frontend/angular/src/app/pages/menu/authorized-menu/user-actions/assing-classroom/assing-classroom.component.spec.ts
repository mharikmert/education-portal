import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssingClassroomComponent } from './assing-classroom.component';

describe('AssingClassroomComponent', () => {
  let component: AssingClassroomComponent;
  let fixture: ComponentFixture<AssingClassroomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssingClassroomComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssingClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
