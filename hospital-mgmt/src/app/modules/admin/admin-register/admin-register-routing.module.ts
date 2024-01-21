import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminRegisterComponent} from "../../../components/admin/admin-register/admin-register.component";

const routes: Routes = [
  {path: '', component: AdminRegisterComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRegisterRoutingModule {
}
