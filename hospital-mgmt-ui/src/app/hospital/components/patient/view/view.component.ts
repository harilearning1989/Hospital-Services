import {Component, OnInit} from '@angular/core';
import {PatientService} from "../../../services/patient/patient.service";
import {Patient} from "../../../models/patient";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  patientList: Patient[];
  displayStyle = "none";
  constructor(private patientService: PatientService) {

  }

  ngOnInit(): void {
    this.listAllPatients();
  }

  showPatientHistoryOpenPopUp(p: Patient) {
    this.displayStyle = "block";
  }
  showPatientHistoryClosePopup() {
    this.displayStyle = "none";
  }

  private listAllPatients() {
    this.patientService
      .listAllPatients()
      .subscribe((response: any) => {
        this.patientList = response.data;
        setTimeout(() => {
          $('#patientDataTable').DataTable({
            responsive: true,
            pagingType: 'full_numbers',
            pageLength: 20,
            processing: true,
            scrollCollapse: true,
            scrollY: '550px',
            lengthMenu: [5, 10, 25]
          });
        }, 1);
      }, error => console.error(error));
  }
}

