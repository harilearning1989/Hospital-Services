import { Component, OnInit } from '@angular/core';
import {DoctorService} from "../../../services/doctor.service";
import {Doctor} from "../../../models/doctor";
import {AdminService} from "../../../services/admin.service";
import {Admin} from "../../../models/admin";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {

  constructor(private adminService: AdminService) {
  }

  adminList: Admin[];
  displayStyle = "none";

  ngOnInit(): void {
    this.listAllAdmins();
  }

  private listAllAdmins() {
    this.adminService.listAllAdmins()
      .subscribe((response: any) => {
        this.adminList = response.data;
        console.log("DoctorList::"+response);
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

  deleteDoctorById(admin: Admin) {
    console.log("Delete Row");
    this.adminService.deleteAdminById(admin.adminId,admin.userId).subscribe(
      data => {
        console.log('deleted response', data);
        this.listAllAdmins();
      }
    )
  }

  modifyUser() {
    console.log("Modify User")
  }

  showPatientHistoryOpenPopUp(admin: Admin) {
    this.displayStyle = "block";
  }

  showPatientHistoryClosePopup() {
    this.displayStyle = "none";
  }

}
