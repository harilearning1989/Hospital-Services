import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {throwError} from "rxjs";
import {environment} from "../../environments/environment";
import {Contact} from "../models/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private httpLink = {
    contactSaveUrl: environment.apiUrl + 'contact/save'
  }
  errorData: {};

  constructor(private http: HttpClient) {
  }

  /*getPage(slug: string) {
    return this.http.get<Page>(this.ServerUrl + 'api/page/' + slug)
      .pipe(
        catchError(this.handleError)
      );
  }*/

  /*contactForm(formdata: Contact) {
    return this.http.post<Contact>(this.ServerUrl + 'api/contact', formdata, this.httpOptions).pipe(
      catchError(this.handleError)
    );
  }*/

  contactForm(formData: Contact) {
    return this.http.post(this.httpLink.contactSaveUrl, formData);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }
    this.errorData = {
      errorTitle: 'Oops! Request for document failed',
      errorDesc: 'Something bad happened. Please try again later.'
    };
    return throwError(this.errorData);
  }
}
