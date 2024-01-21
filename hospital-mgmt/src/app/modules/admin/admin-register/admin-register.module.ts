import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRegisterRoutingModule } from './admin-register-routing.module';
import { AdminRegisterComponent } from '../../../components/admin/admin-register/admin-register.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AdminRegisterComponent
  ],
  imports: [
    CommonModule,
    AdminRegisterRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminRegisterModule { }
