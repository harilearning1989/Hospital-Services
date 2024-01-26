import {Component, OnInit} from '@angular/core';
import {DoctorService} from "../../services/doctor.service";
import {Patient} from "../../models/patient";
import {Doctor} from "../../models/doctor";

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {

  constructor(private doctorService: DoctorService) {
  }

  doctorsList: Doctor[];

  ngOnInit(): void {
    this.listAllDoctors();
  }

  private listAllDoctors() {
    this.doctorService.listAllDoctors()
      .subscribe((response: any) => {
        this.doctorsList = response.data;
        setTimeout(() => {
          $('#doctorDataTable').DataTable({
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
