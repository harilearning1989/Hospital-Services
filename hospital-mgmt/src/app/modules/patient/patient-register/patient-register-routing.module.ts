import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PatientRegisterComponent} from "../../../components/patient/patient-register/patient-register.component";

const routes: Routes = [
  {path: '', component: PatientRegisterComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRegisterRoutingModule {
}
