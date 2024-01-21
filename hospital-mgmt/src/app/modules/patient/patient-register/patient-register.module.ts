import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRegisterRoutingModule } from './patient-register-routing.module';
import { PatientRegisterComponent } from '../../../components/patient/patient-register/patient-register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PatientRegisterComponent
  ],
  imports: [
    CommonModule,
    PatientRegisterRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class PatientRegisterModule { }
