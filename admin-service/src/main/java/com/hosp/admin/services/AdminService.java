package com.hosp.admin.services;

import com.hosp.admin.records.AdminRec;

import java.util.List;

public interface AdminService {
    AdminRec registerPatient(AdminRec adminRec);

    List<AdminRec> listAllPatients();

    AdminRec updatePatient(int id,AdminRec dto);

    String deletePatientById(int id);

}
