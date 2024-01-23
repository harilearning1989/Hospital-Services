import {Injectable} from '@angular/core';
import {map} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private httpLink = {
    listAllDoctorsUrl: environment.doctorUrl + 'doctor/list'
  }

  constructor(private http: HttpClient) {
  }

  listAllDoctors() {
    return this.http.get(this.httpLink.listAllDoctorsUrl)
      .pipe(map(x => {
        return x;
      }));
  }
}
