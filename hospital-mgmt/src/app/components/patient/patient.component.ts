import {Component, OnInit} from '@angular/core';
import {Patient} from "../../models/patient";
import {PatientService} from "../../services/patient.service";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

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
    this.patientService.listAllPatients()
      .subscribe((response: any) => {
        this.patientList = response.data;
        setTimeout(() => {
          $('#patientDataTable').DataTable({
            responsive: true,
            pagingType: 'full_numbers',
            pageLength: 10,
            processing: true,
            scrollCollapse: true,
            scrollY: '550px',
            lengthMenu: [5, 10, 25]
          });
        }, 1);
      }, error => console.error(error));
  }

  deletePatientById(p: Patient) {
    this.patientService.deletePatientById(p.id).subscribe(
      data => {
        console.log('deleted response', data);
        this.listAllPatients();
      }
    )
  }
}
