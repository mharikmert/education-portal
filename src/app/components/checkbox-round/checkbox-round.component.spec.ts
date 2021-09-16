import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckboxRoundComponent } from './checkbox-round.component';

describe('CheckboxRoundComponent', () => {
  let component: CheckboxRoundComponent;
  let fixture: ComponentFixture<CheckboxRoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckboxRoundComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckboxRoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
