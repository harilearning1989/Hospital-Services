import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private loginService: LoginService,
    private router: Router) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('../login');
    //location.reload();
  }

}
