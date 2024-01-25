import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {environment} from "../../environments/environment";
import {LoginService} from "../services/login.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isApiUrl = req.url.startsWith(environment.apiUrl);
    console.log("HTTP Request ::"+req);
    console.log("Logged In User::" + isApiUrl);
    if (this.loginService.isUserSignedIn()) {//} && isApiUrl) {
      const user = this.loginService.userValue;
      const isLoggedIn = user && user.token;
      if (isLoggedIn) {
        /*const headers = new HttpHeaders()
          //.set('Content-Type', 'application/json')
          .set('Authorization', `Bearer ${user.token}`);*/

        const headers= new HttpHeaders({
          'Authorization': `Bearer ${user.token}`
        });
        console.log(headers)
        //const contentType = headers.get('Content-Type');
        //const authorization = headers.get('Authorization');
        //console.log(authorization);

        req = req.clone({headers});

        return next.handle(req).pipe(
          catchError(err => {
            if (err instanceof HttpErrorResponse && err.status === 401) {
              this.loginService.logout();
            }
            return throwError(err);
          })
        );
      }
    }
    return next.handle(req);
  }
}
