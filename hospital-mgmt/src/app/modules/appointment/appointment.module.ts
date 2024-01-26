import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppointmentRoutingModule } from './appointment-routing.module';
import { AppointmentComponent } from '../../components/appointment/appointment.component';
import {DataTablesModule} from "angular-datatables";


@NgModule({
  declarations: [
    AppointmentComponent
  ],
    imports: [
        CommonModule,
        AppointmentRoutingModule,
        DataTablesModule
    ]
})
export class AppointmentModule { }
