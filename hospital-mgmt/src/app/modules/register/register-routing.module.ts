import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "../../components/register/register.component";

const routes: Routes = [
  {
    path: '', component: RegisterComponent,
    children: [
      {
        path: 'patient',
        loadChildren: () => import('../patient/patient-register/patient-register.module').then(m => m.PatientRegisterModule)
      },
      {
        path: 'doctor',
        loadChildren: () => import('../doctor/doctor-register/doctor-register.module').then(m => m.DoctorRegisterModule)
      },
      {
        path: 'admin',
        loadChildren: () => import('../admin/admin-register/admin-register.module').then(m => m.AdminRegisterModule)
      },
      {path: '', redirectTo: 'patient', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule {
}
