import {Injectable} from '@angular/core';
import {map} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private httpLink = {
    listAllDoctorsUrl: environment.apiUrl + 'doctor/list',
    deleteDoctorById: environment.apiUrl + 'doctor/delete',
  }

  constructor(private http: HttpClient) {
  }

  listAllDoctors() {
    return this.http.get(this.httpLink.listAllDoctorsUrl)
      .pipe(map(x => {
        return x;
      }));
  }

  deleteDoctorById(doctorId: number | undefined,userId:number | undefined) {
    console.log("Delete Doctor By Id in Service::"+`${this.httpLink.deleteDoctorById}/${doctorId}/${userId}`);
    return this.http.delete(`${this.httpLink.deleteDoctorById}/${doctorId}/${userId}`)
      //return this.http.delete(this.httpLink.deletePatientById + id)
      .pipe(map(x => {
        return x;
      }));
  }
}
