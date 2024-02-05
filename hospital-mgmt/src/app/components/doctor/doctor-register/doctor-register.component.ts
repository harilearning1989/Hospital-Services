import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../../services/login.service";
import {RegisterService} from "../../../services/register.service";
import {Utils} from "../../../utils/utils";
import {InputValidation} from "../../../validations/input-validation";
import {DefaultDataService} from "../../../services/default-data.service";

@Component({
  selector: 'app-doctor-register',
  templateUrl: './doctor-register.component.html',
  styleUrls: ['./doctor-register.component.scss']
})
export class DoctorRegisterComponent implements OnInit {
  doctorForm!: FormGroup;
  loading = false;
  submitted = false;
  errorMessage: string;
  specialists: any;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private registerService: RegisterService,
    private defaultDataService: DefaultDataService
  ) {
  }

  ngOnInit() {
    this.defaultDataService.getSpecialists().subscribe(data => {
      this.specialists = data;
      console.log(this.specialists);
    });
    this.doctorFormFields();
    //this.doctorForm.controls['country'].setValue(this.default, {onlySelf: true});
  }

  get doctorControls() {
    return this.doctorForm.controls;
  }

  onDoctorSubmit() {
    this.submitted = true;
    if (this.doctorForm.invalid) {
      return;
    }
    //this.patientForm.value.roles = this.selectedRoles;
    console.log(this.doctorForm.value);
    this.loading = true;
    this.registerService.registerDoctor(this.doctorForm.value)
      .subscribe((data: any) => {
          this.loading = false;
          this.submitted = false;
          if (data.status == 400) {
            this.errorMessage = data.status + ' ' + data.message;
          }else{
            this.doctorFormFields();
            this.router.navigate(['/login']);
          }
        },
        (error: any) => {
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

  private doctorFormFields() {
    this.doctorForm = this.formBuilder.group({
      doctorName: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      specialist: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      experience: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      email: ['', [Validators.required, Validators.email,
        Validators.maxLength(20),
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      phone: ['', [Validators.required,
        Validators.minLength(10), Validators.maxLength(10),
        InputValidation.cannotContainSpace]],
      username: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      password: ['', [Validators.required, Validators.minLength(6),
        Validators.maxLength(20), InputValidation.cannotContainSpace]]
    });
  }

}
