import {Injectable} from '@angular/core';
import {Patient} from "../models/patient";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Doctor} from "../models/doctor";
import {Admin} from "../models/admin";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private httpLink = {
    loginUrl: environment.apiUrl + 'login',
    registerPatientUrl: environment.apiUrl + 'patient/register',
    registerDoctorUrl: environment.apiUrl + 'doctor/register',
    registerAdminUrl: environment.apiUrl + 'admin/register'
  }

  constructor(private router: Router,
              private http: HttpClient) {
  }

  registerPatient(patient: Patient) {
    console.log("URL::" + this.httpLink.registerPatientUrl);
    return this.http.post(this.httpLink.registerPatientUrl, patient);
  }

  registerDoctor(doctor: Doctor) {
    console.log("doctor::" + doctor);
    debugger;
    return this.http.post(this.httpLink.registerDoctorUrl, doctor);
  }

  registerAdmin(admin: Admin) {
    return this.http.post(this.httpLink.registerAdminUrl, admin);
  }
}
