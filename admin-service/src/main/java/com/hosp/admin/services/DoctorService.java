package com.hosp.admin.services;

import com.hosp.admin.records.DoctorRec;
import com.hosp.admin.records.DoctorResponse;

public interface DoctorService {
    DoctorResponse registerDoctor(DoctorRec dto);

    DoctorResponse listAllDoctorDetails();

    DoctorResponse updateDoctor(int id, DoctorRec dto);

    DoctorResponse deleteById(int id);

}
