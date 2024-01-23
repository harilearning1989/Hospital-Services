import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {first} from "rxjs";
import {Utils} from "../../utils/utils";
import {InputValidation} from "../../validations/input-validation";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form!: FormGroup;
  loading = false;
  submitted = false;
  errorMessage: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService
  ) {
  }

  ngOnInit() {
    this.formFields();
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }
    this.loading = true;
    this.loginService.login(this.f['username'].value, this.f['password'].value)
      .pipe(first())
      .subscribe({
        next: () => {
          // get return url from query parameters or default to home page
          //const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/home';
          //this.router.navigateByUrl(returnUrl);
          this.errorMessage = '';
          this.router.navigateByUrl('/home');
        },
        error: error => {
          console.log("Submission Failed ::" + error);
          this.errorMessage = error;
          this.loading = false;
        }
      });
  }

  omitSpecialCharsAndNumbers(event: KeyboardEvent) {
    return Utils.omitSpecialCharsAndNumbers(event);
  }

  omitSpecialChars(event: KeyboardEvent) {
    return Utils.omitSpecialChars(event);
  }

  private formFields() {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4),
        InputValidation.cannotContainSpace]],
      password: ['', [Validators.required, Validators.minLength(4),
        InputValidation.cannotContainSpace]]
    });
  }

}
