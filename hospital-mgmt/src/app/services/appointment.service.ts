import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private httpLink = {
    listAllAppointmentUrl: environment.apiUrl + 'appointment/list',
    saveAppointmentUrl: environment.apiUrl + 'appointment/save',
    deleteAppointmentUrl: environment.apiUrl + 'appointment/delete',
    appointmentByIdUrl: environment.apiUrl + 'appointment/byId',
    updateAppointmentUrl: environment.apiUrl + 'appointment/delete'
  }

  constructor(private httpClient: HttpClient) {
  }

  getAppointmentList(): Observable<any> {
    return this.httpClient.get(`${this.httpLink.listAllAppointmentUrl}`);
  }
  saveAppointment(student: object): Observable<object> {
    return this.httpClient.post(`${this.httpLink.saveAppointmentUrl}`, student);
  }

  deleteAppointment(id: number): Observable<any> {
    return this.httpClient.delete(`${this.httpLink.deleteAppointmentUrl}/${id}`, { responseType: 'text' });
  }

  getAppointmentById(id: number): Observable<Object> {
    return this.httpClient.get(`${this.httpLink.deleteAppointmentUrl}/${id}`);
  }

  updateAppointment(id: number, value: any): Observable<Object> {
    return this.httpClient.post(`${this.httpLink.updateAppointmentUrl}/${id}`, value);
  }
}
