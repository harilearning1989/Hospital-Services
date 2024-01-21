import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../../services/login.service";
import {RegisterService} from "../../../services/register.service";
import {Utils} from "../../../utils/utils";
import {InputValidation} from "../../../validations/input-validation";

@Component({
  selector: 'app-patient-register',
  templateUrl: './patient-register.component.html',
  styleUrls: ['./patient-register.component.scss']
})
export class PatientRegisterComponent implements OnInit {

  patientForm!: FormGroup;
  loading = false;
  submitted = false;
  //selectedRoles: string[] = [];
  errorMessage: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private registerService: RegisterService
  ) {
  }

  ngOnInit() {
    this.patientFormFields();
  }

  // convenience getter for easy access to form fields
  get patientControls() {
    return this.patientForm.controls;
  }

  onPatientSubmit() {
    this.submitted = true;
    // reset alerts on submit
    // stop here if form is invalid
    if (this.patientForm.invalid) {
      return;
    }
    //this.patientForm.value.roles = this.selectedRoles;
    console.log(this.patientForm.value);
    this.loading = true;
    /*this.loginService.register(this.form.value)
      .subscribe({
        next: () => {
          console.log("Success Logged In");
          this.alertService.success('Registration successful', { keepAfterRouteChange: true });
          this.router.navigate(['../login'], { relativeTo: this.route });
        },
        error: error => {
          console.log("Register Failed ::"+error);
          //this.alertService.error(error);
          this.loading = false;
        }
      });*/

    this.registerService.registerPatient(this.patientForm.value)
      .subscribe((data: any) => {
          console.log("Submitted Successfully");
          this.errorMessage = data.status + ' ' + data.message;
          this.loading = false;
          this.submitted = false;
          this.patientFormFields();
          //this.router.navigate(['../login'], {relativeTo: this.route});
        },
        (error: any) => {
          console.log("Submission Failed ::" + error);
          this.errorMessage = error;
          this.loading = false;
        });
  }

  omitSpecialChars(event: KeyboardEvent) {
    return Utils.omitSpecialChars(event);
  }

  omitSpecialCharsAndNumbers(event: KeyboardEvent) {
    return Utils.omitSpecialCharsAndNumbers(event);
  }
  allowOnlyNumbers(event: KeyboardEvent) {
    return Utils.allowOnlyNumbers(event);
  }

  private patientFormFields() {
    this.patientForm = this.formBuilder.group({
      patientName: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      email: ['', [Validators.required, Validators.email,
        Validators.maxLength(20),
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      phone: ['', [Validators.required,
        Validators.minLength(10), Validators.maxLength(10),
        InputValidation.cannotContainSpace]],
      gender: ['', [Validators.required,
        Validators.minLength(10), Validators.maxLength(10),
        InputValidation.cannotContainSpace]],
      username: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      address: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      password: ['', [Validators.required, Validators.minLength(6),
        Validators.maxLength(20), InputValidation.cannotContainSpace]]
    });
  }

}
