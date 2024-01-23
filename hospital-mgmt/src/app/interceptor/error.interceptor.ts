import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError, catchError} from 'rxjs';
import {LoginService} from "../services/login.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          //debugger
          let errorMsg = '';
          if (error.error instanceof ErrorEvent) {
            console.log('this is client side error');
            errorMsg = `Error: ${error.error.message}`;
          } else if (error instanceof HttpErrorResponse && error.status === 401) {
            alert('Session Expired or Invalid Login Navigating to Login Page')
            errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
            this.loginService.logout();
          } else {
            errorMsg = `Error Code: ${error.status},  Message: ${error.error.message}`;
          }
          console.log(errorMsg);
          return throwError(errorMsg);
        })
      )
  }
}
