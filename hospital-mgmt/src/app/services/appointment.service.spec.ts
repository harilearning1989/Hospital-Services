import { TestBed } from '@angular/core/testing';

import { AppointmentService } from './appointment.service';

describe('AppointmentService', () => {
  let appoint: AppointmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    appoint = TestBed.inject(AppointmentService);
  });

  it('should be created', () => {
    expect(appoint).toBeTruthy();
  });

  it('says hello', () => {
    let result = appoint.helloWorld();
    expect(result).toEqual('Hello world!');
  });
});

