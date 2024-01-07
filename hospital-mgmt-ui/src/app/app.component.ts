import { Component } from '@angular/core';
import {LoginService} from "./hospital/services/login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'hospital-mgmt';
  userName: any;
  user_role: any;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) {
  }

  ngOnInit() {
    const user = this.loginService.userValue;
    this.user_role = user && user.roles && user.roles[0];
    console.log('this.user_role::' + this.user_role);

    const isLoggedIn = user && user.accessToken;
    if (isLoggedIn) {
      this.userName = user.username;
    }
  }

  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('/home');
    location.reload();
  }
}
