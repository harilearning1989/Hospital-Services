import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DoctorRegisterRoutingModule} from './doctor-register-routing.module';
import {DoctorRegisterComponent} from "../../../components/doctor/doctor-register/doctor-register.component";
import {ReactiveFormsModule} from "@angular/forms";
import {DoctorModule} from "../doctor.module";
import {SortSpecialistPipe} from "../../../pipes/sort-specialist.pipe";


@NgModule({
  declarations: [
    DoctorRegisterComponent,
    SortSpecialistPipe
  ],
  imports: [
    CommonModule,
    DoctorRegisterRoutingModule,
    ReactiveFormsModule,
    DoctorModule
  ]
})
export class DoctorRegisterModule {
}
