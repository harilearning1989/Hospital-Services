package com.hosp.doctor.services;

import com.hosp.doctor.records.DoctorRec;

import java.util.List;

public interface DoctorService {
    DoctorRec registerDoctor(DoctorRec dto);

    List<DoctorRec> listAllDoctorDetails();

    DoctorRec updateDoctor(int id, DoctorRec dto);

    String deleteDoctorById(int doctorId,long userId);
}
