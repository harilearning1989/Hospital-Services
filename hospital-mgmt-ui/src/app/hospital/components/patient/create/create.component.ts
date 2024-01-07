import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientService} from "../../../services/patient/patient.service";
import {Utils} from "../../../util/utils";
import {InputValidation} from "../../../validations/input-validation";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  form!: FormGroup;
  loading = false;
  submitted = false;
  selectedRoles: string[] = [];
  errorMessage: string;
  bloodGroupList: any = ['A+', 'A-', 'B+', 'B-','AB+', 'AB-', 'O+', 'O-'];
  genderList: any = ['Male', 'Female', 'Others'];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private patientService: PatientService
  ) {
  }

  ngOnInit() {
    this.formFields();
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.form.controls;
  }

  changeBloodGrp(e: any) {
    console.log(e.value)
    // @ts-ignore
    this.getBloodGrp.setValue(e.target.value, {
      onlySelf: true
    })
  }

  get getBloodGrp() {
    return this.form.get('bloodGroup');
  }

  changeGender(e: any) {
    console.log(e.value)
    // @ts-ignore
    this.getGender.setValue(e.target.value, {
      onlySelf: true
    })
  }

  get getGender() {
    return this.form.get('gender');
  }

  onSubmit() {
    this.submitted = true;
    // reset alerts on submit
    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }
    this.form.value.roles = this.selectedRoles;
    console.log(this.form.value);
    this.loading = true;
    this.patientService.register(this.form.value)
      .subscribe((data: any) => {
          console.log("Submitted Successfully");
          this.errorMessage = data.status +' '+ data.message;
          this.loading = false;
          this.submitted = false;
          this.formFields();
          //this.router.navigate(['../login'], {relativeTo: this.route});
        },
        (error: any) => {
          console.log("Submission Failed ::" + error);
          this.errorMessage = error;
          this.loading = false;
        });
  }

  user_roles: any = [
    {name: 'Admin', value: 'ROLE_ADMIN', selected: false},
    {name: 'Student', value: 'ROLE_STUDENT', selected: false}
  ];

  onChangeCategory(event: any, role: any) {
    this.selectedRoles.push(role.value);
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

  private formFields() {
    this.form = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      lastName: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      email: ['', [Validators.required, Validators.email,
        Validators.maxLength(20),
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      contact: ['', [Validators.required,Validators.pattern("^[0-9]*$"),
        Validators.minLength(10), Validators.maxLength(10),
        InputValidation.cannotContainSpace]],
      username: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      age: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(10), InputValidation.cannotContainSpace]],
      gender: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      bloodGroup: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      address: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      city: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20), InputValidation.cannotContainSpace]],
      pincode: ['', [Validators.required, Validators.minLength(6),
        Validators.maxLength(6), InputValidation.cannotContainSpace]],
      password: ['', [Validators.required, Validators.minLength(6),
        Validators.maxLength(20), InputValidation.cannotContainSpace]]
    });
  }

}

