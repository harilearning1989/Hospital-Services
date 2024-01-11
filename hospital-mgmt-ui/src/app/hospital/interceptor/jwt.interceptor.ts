import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {LoginService} from "../services/login/login.service";
import {environment} from "../../../environments/environment";
import {catchError} from 'rxjs/operators';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const isApiUrl = req.url.startsWith(environment.apiUrl);
    if (this.loginService.isUserSignedin() && isApiUrl) {
      const user = this.loginService.userValue;
      const isLoggedIn = user && user.token;
      if (isLoggedIn) {
        //debugger
        const headers = new HttpHeaders()
          .set('Content-Type', 'application/json')
          .set('Access-Control-Allow-Origin', '*')
          .set('Accept','application/json')
          .set('Authorization', `Bearer ${user.token}`);
        console.log(headers)
        const contentType =headers.get('Content-Type');
        const authorization =headers.get('Authorization');
        console.log(authorization);

        req = req.clone({headers});

        return next.handle(req).pipe(
          catchError(err => {
            if (err instanceof HttpErrorResponse && err.status === 401) {
              this.loginService.signout();
            }
            return throwError(err);
          })
        );
      }
    }
    return next.handle(req);
  }
}

