import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorRoutingModule } from './doctor-routing.module';
import { DoctorComponent } from '../../components/doctor/doctor.component';


@NgModule({
  declarations: [
    DoctorComponent,
  ],
  imports: [
    CommonModule,
    DoctorRoutingModule,
  ],
  exports: [
  ]
})
export class DoctorModule { }
