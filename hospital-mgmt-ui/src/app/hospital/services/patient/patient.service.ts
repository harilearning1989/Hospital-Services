import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {map} from 'rxjs/operators';
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";
import {environment} from "../../../../environments/environment";
import {Patient} from "../../models/patient";

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private httpLink = {
    loginUrl: environment.apiUrl + 'login',
    registerUrl: environment.apiUrl + 'register',
    listAllPatientsUrl: environment.apiUrl + 'patient/list'
  }

  private userSubject: BehaviorSubject<Patient | null>;
  public user: Observable<Patient | null>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userSubject.asObservable();
  }

  login(username: string, password: string) {
    return this.http.post<Patient>(this.httpLink.loginUrl, {username, password})
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

  register(user: Patient) {
    return this.http.post(this.httpLink.registerUrl, user);
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
    return this.http.get<Patient[]>(`${environment.apiUrl}/users`);
  }

  getToken() {
    let token = sessionStorage.getItem('token') as string;
    return token;
  }

  getById(id: string) {
    return this.http.get<Patient>(`${environment.apiUrl}/users/${id}`);
  }

  update(id: string, params: any) {
    return this.http.put(`${environment.apiUrl}/users/${id}`, params)
      .pipe(map(x => {
        // update stored user if the logged in user updated their own record
        if (id == this.userValue?.id) {
          // update local storage
          const user = {...this.userValue, ...params};
          localStorage.setItem('user', JSON.stringify(user));

          // publish updated user to subscribers
          this.userSubject.next(user);
        }
        return x;
      }));
  }

  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/users/${id}`)
      .pipe(map(x => {
        // auto logout if the logged in user deleted their own record
        if (id == this.userValue?.id) {
          this.logout();
        }
        return x;
      }));
  }

  users(): Observable<any> {
    return this.http.get('https://jsonplaceholder.typicode.com/users');
  }

  listAllPatients(): Observable<Patient> {
    return this.http.get(this.httpLink.listAllPatientsUrl)
      .pipe(map(x => {
        return x;
      }));
  }
}
