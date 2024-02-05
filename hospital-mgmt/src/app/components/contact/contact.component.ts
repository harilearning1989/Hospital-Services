import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InputValidation} from "../../validations/input-validation";
import {Utils} from "../../utils/utils";
import {Contact} from "../../models/contact";
import {ContactService} from "../../services/contact.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  model = new Contact();
  submitted = false;
  error: {};

  form!: FormGroup;
  loading = false;
  selectedRoles: string[] = [];
  errorMessage: string;

  constructor(
    private router: Router,
    private contactService: ContactService,
    private formBuilder: FormBuilder
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
    this.form.value.roles = this.selectedRoles;
    console.log(this.form.value);
    this.loading = true;

    this.contactService.contactForm(this.form.value)
      .subscribe((data: any) => {
          console.log("Submitted Successfully");
          this.errorMessage = 'Added Successful';
          this.loading = false;
          this.submitted = false;
          this.formFields();
        },
        (error: any) => {
          console.log("Submission Failed ::" + error);
          this.errorMessage = error.status+' '+error.error.message;
          this.loading = false;
        });
  }

  omitSpecialChars(event: KeyboardEvent) {
    return Utils.omitSpecialChars(event);
  }

  allowOnlyNumbers(event: KeyboardEvent) {
    return Utils.allowOnlyNumbers(event);
  }

  omitSpaces(event: KeyboardEvent) {
    return Utils.omitSpaces(event);
  }

  omitSpecialCharsAndNumbers(event: KeyboardEvent) {
    return Utils.omitSpecialCharsAndNumbers(event);
  }
  clearFields() {
    this.errorMessage = '';
    this.formFields();
  }

  private formFields() {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20)]],
      mobile: ['', [Validators.required, Validators.minLength(10),
        Validators.maxLength(10), InputValidation.cannotContainSpace]],
      email: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20)]],
      message: ['', [Validators.required, Validators.minLength(4),
        Validators.maxLength(20)]]
    });
  }
}
