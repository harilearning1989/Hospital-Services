import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HistoryComponent} from "../../../components/doctor/history/history.component";

@NgModule({
  declarations: [
    HistoryComponent
  ],
  exports: [
    HistoryComponent
  ],
  imports: [
    CommonModule
  ]
})
export class HistoryModule { }
