import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const homeModule = () => import('./hospital/modules/home/home.module').then(x => x.HomeModule);
const pageNotFoundModule = () => import('./hospital/modules/page-not-found/page-not-found.module').then(x => x.PageNotFoundModule);
const contactModule = () => import('./hospital/modules/contact/contact.module').then(x => x.ContactModule);
const aboutModule = () => import('./hospital/modules/about/about.module').then(x => x.AboutModule);
const createPatientModule = () => import('./hospital/modules/patient/create/create.module').then(x => x.CreateModule);
const viewPatientModule = () => import('./hospital/modules/patient/view/view.module').then(x => x.ViewModule);
const updatePatientModule = () => import('./hospital/modules/patient/update/update.module').then(x => x.UpdateModule);
const loginModule = () => import('./hospital/modules/login/login.module').then(x => x.LoginModule);
const adminViewModule = () => import('./hospital/modules/admin/view/view.module').then(x => x.ViewModule);
const adminCreateModule = () => import('./hospital/modules/admin/create/create.module').then(x => x.CreateModule);
const adminUpdateModule = () => import('./hospital/modules/admin/view/view.module').then(x => x.ViewModule);
const checkAllAppointmentsModule = () =>
  import('./hospital/modules/admin/check-all-appointments/check-all-appointments.module')
    .then(x => x.CheckAllAppointmentsModule);
const createDoctorModule = () => import('./hospital/modules/doctor/create/create.module').then(x => x.CreateModule);
const viewDoctorModule = () => import('./hospital/modules/doctor/view/view.module').then(x => x.ViewModule);
const updateDoctorModule = () => import('./hospital/modules/doctor/update/update.module').then(x => x.UpdateModule);
const viewMyAppointmentsModule = () => import('./hospital/modules/doctor/view-appointments/view-appointments.module').then(x => x.ViewAppointmentsModule);
const viewAppointmentModule = () => import('./hospital/modules/appointment/view/view.module').then(x => x.ViewModule);
const updateAppointmentModule = () => import('./hospital/modules/appointment/update/update.module').then(x => x.UpdateModule);
const takeAppointmentModule = () => import('./hospital/modules/appointment/create/create.module').then(x => x.CreateModule);

const routes: Routes = [
  {path: '', redirectTo: '', pathMatch: 'full'},
  {path: '', data: {title: 'Home Module'}, loadChildren: homeModule},
  {path: 'about', loadChildren: aboutModule},
  {path: 'contact', loadChildren: contactModule},
  {path: 'login', loadChildren: loginModule},
  {path: 'viewMyAppointments', loadChildren: viewMyAppointmentsModule},

  {path: 'createPatient', loadChildren: createPatientModule},
  {path: 'viewPatient', loadChildren: viewPatientModule},
  {path: 'updatePatient', loadChildren: updatePatientModule},

  {path: 'createAdmin', loadChildren: adminCreateModule},
  {path: 'viewAdmin', loadChildren: adminViewModule},
  {path: 'updateAdmin', loadChildren: adminUpdateModule},
  {path: 'checkAllAppointments', loadChildren: checkAllAppointmentsModule},

  {path: 'createDoctor', loadChildren: createDoctorModule},
  {path: 'viewDoctor', loadChildren: viewDoctorModule},
  {path: 'updateDoctor', loadChildren: updateDoctorModule},

  {path: 'takeAppointment', loadChildren: takeAppointmentModule},
  {path: 'viewAppointment', loadChildren: viewAppointmentModule},
  {path: 'updateAppointment', loadChildren: updateAppointmentModule},

  {path: '**', data: {title: 'Page Not Module'}, loadChildren: pageNotFoundModule}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
