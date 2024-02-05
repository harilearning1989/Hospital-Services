import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientRoutingModule } from './patient-routing.module';
import { PatientComponent } from '../../components/patient/patient.component';
import {DataTablesModule} from "angular-datatables";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PatientComponent
  ],
    imports: [
        CommonModule,
        PatientRoutingModule,
        DataTablesModule,
        ReactiveFormsModule
    ]
})
export class PatientModule { }
