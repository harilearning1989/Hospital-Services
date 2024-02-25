import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "../../components/home/home.component";

const routes: Routes = [
  {
    path: '', component: HomeComponent, children: [
      {
        path: 'about',
        loadChildren: () => import('../about/about.module').then(m => m.AboutModule)
      },
      {
        path: 'login',
        loadChildren: () => import('../login/login.module').then(m => m.LoginModule)
      },
      {
        path: 'patient',
        loadChildren: () => import('../patient/patient.module').then(m => m.PatientModule)
      },
      {
        path: 'doctor',
        loadChildren: () => import('../doctor/doctor.module').then(m => m.DoctorModule)
      },
      {
        path: 'appt',
        loadChildren: () => import('../appointment/appointment.module').then(m => m.AppointmentModule)
      },
      {
        path: 'admin',
        loadChildren: () => import('../admin/view/view.module').then(m => m.ViewModule)
      },
      {
        path: 'contact',
        loadChildren: () => import('../contact/contact.module').then(m => m.ContactModule)
      },
      {
        path: '', redirectTo: 'about', pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule {
}
