import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private httpLink = {
    listAllAdminsUrl: environment.apiUrl + 'admin/list',
    deleteAdminById: environment.apiUrl + 'admin/delete',
  }

  constructor(private http: HttpClient) {
  }

  listAllAdmins() {
    return this.http.get(this.httpLink.listAllAdminsUrl)
      .pipe(map(x => {
        return x;
      }));
  }

  deleteAdminById(doctorId: number | undefined,userId:number | undefined) {
    console.log("Delete Admin By Id in Service::"+`${this.httpLink.deleteAdminById}/${doctorId}/${userId}`);
    return this.http.delete(`${this.httpLink.deleteAdminById}/${doctorId}/${userId}`)
      //return this.http.delete(this.httpLink.deletePatientById + id)
      .pipe(map(x => {
        return x;
      }));
  }
}
