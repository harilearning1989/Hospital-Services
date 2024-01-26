import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {BehaviorSubject, map, Observable} from "rxjs";
import {Patient} from "../models/patient";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private httpLink = {
    loginUrl: environment.apiUrl + 'login',
    registerUrl: environment.apiUrl + 'register',
    listAllPatientsUrl: environment.apiUrl + 'patient/list',
    deletePatientById: environment.apiUrl + 'patient/delete',
    listAllDoctorsUrl: environment.apiUrl + 'doctor/list'
  }

  private userSubject: BehaviorSubject<Patient | null>;
  public user: Observable<Patient | null>;

  constructor(
    private router: Router,
    private httpClient: HttpClient
  ) {
    this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userSubject.asObservable();
  }

  register(user: Patient) {
    return this.httpClient.post(this.httpLink.registerUrl, user);
  }

  deletePatientById(id: number | undefined) {
    console.log("Delete Patient By Id in Service::"+`${this.httpLink.deletePatientById}/${id}`);
    return this.httpClient.delete(`${this.httpLink.deletePatientById}/${id}`)
    //return this.http.delete(this.httpLink.deletePatientById + id)
      .pipe(map(x => {
        debugger;
        return x;
      }));
  }

/*  deleteExpense(id: number): Observable<any> {
    return this.httpClient.delete(`${this.getUrl}/${id}`, {responseType: 'text'});
  }*/

  listAllPatients(): Observable<Patient> {
    return this.httpClient.get(this.httpLink.listAllPatientsUrl)
      .pipe(map(data => {
        return data;
      }));
  }




  deleteById(id: number | undefined) {
    return this.httpClient.delete(`${environment.apiUrl}/users/${id}`)
      .pipe(map(x => {
        // auto logout if the logged in user deleted their own record
        //if (id == this.userValue?.id) {
        //this.logout();
        //}
        return x;
      }));
  }

  users(): Observable<any> {
    return this.httpClient.get('https://jsonplaceholder.typicode.com/users');
  }

  login(username: string, password: string) {
    return this.httpClient.post<Patient>(this.httpLink.loginUrl, {username, password})
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }

  isUserSignedin() {
    return localStorage.getItem('user') !== null;
  }


  public get userValue() {
    return this.userSubject.value;
  }

  signout() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');

    this.router.navigateByUrl('login');
  }

  getAll() {
    return this.httpClient.get<Patient[]>(`${environment.apiUrl}/users`);
  }

  getToken() {
    let token = sessionStorage.getItem('token') as string;
    return token;
  }

  getById(id: string) {
    return this.httpClient.get<Patient>(`${environment.apiUrl}/users/${id}`);
  }

  update(id: string, params: any) {
    return this.httpClient.put(`${environment.apiUrl}/users/${id}`, params)
      .pipe(map(x => {
        // update stored user if the logged in user updated their own record
        //if (id == this.userValue?.id) {
        // update local storage
        const user = {...this.userValue, ...params};
        localStorage.setItem('user', JSON.stringify(user));

        // publish updated user to subscribers
        this.userSubject.next(user);
        //}
        return x;
      }));
  }
}
