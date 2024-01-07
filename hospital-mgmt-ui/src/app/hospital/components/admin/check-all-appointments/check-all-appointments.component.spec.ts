import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckAllAppointmentsComponent } from './check-all-appointments.component';

describe('CheckAllAppointmentsComponent', () => {
  let component: CheckAllAppointmentsComponent;
  let fixture: ComponentFixture<CheckAllAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckAllAppointmentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckAllAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
