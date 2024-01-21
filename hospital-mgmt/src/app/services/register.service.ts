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
    registerUrl: environment.apiUrl + 'register'
  }

  constructor(private router: Router,
              private http: HttpClient) {
  }

  registerPatient(patient: Patient) {
    return this.http.post(this.httpLink.registerUrl, patient);
  }

  registerDoctor(doctor: Doctor) {
    return this.http.post(this.httpLink.registerUrl, doctor);
  }

  registerAdmin(admin: Admin) {
    return this.http.post(this.httpLink.registerUrl, admin);
  }
}
