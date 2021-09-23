import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentMenuComponent } from './parent-menu.component';

describe('ParentMenuComponent', () => {
  let component: ParentMenuComponent;
  let fixture: ComponentFixture<ParentMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParentMenuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
