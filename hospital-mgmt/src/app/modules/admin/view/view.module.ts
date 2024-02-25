import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ViewRoutingModule} from './view-routing.module';
import {ViewComponent} from '../../../components/admin/view/view.component';
import {DataTablesModule} from "angular-datatables";


@NgModule({
  declarations: [
    ViewComponent
  ],
  imports: [
    CommonModule,
    ViewRoutingModule,
    DataTablesModule
  ]
})
export class ViewModule {
}
