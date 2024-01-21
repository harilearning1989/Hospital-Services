import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { PatientComponent } from '../../components/patient/patient.component';
import {DataTablesModule} from "angular-datatables";


@NgModule({
  declarations: [
    PatientComponent
  ],
  imports: [
    CommonModule,
    PatientRoutingModule,
    DataTablesModule
  ]
})
export class PatientModule { }
