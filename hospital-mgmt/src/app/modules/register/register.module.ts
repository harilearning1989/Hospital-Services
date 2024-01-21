import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {RegisterRoutingModule} from './register-routing.module';
import {RegisterComponent} from '../../components/register/register.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NgbNavModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [
    RegisterComponent,
  ],
  imports: [
    CommonModule,
    RegisterRoutingModule,
    ReactiveFormsModule,
    NgbNavModule,
  ]
})
export class RegisterModule {
}
