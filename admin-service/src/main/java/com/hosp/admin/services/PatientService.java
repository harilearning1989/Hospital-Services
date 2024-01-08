package com.hosp.admin.services;

import com.hosp.admin.records.Patient;
import com.hosp.admin.records.PatientResponse;

import java.util.Date;
import java.util.Map;

public interface PatientService {

    Patient registerPatient(Patient patientDTO);

    PatientResponse listAllPatientDetails();
    Patient getPatientById(int id);

    Patient updatePatient(int id,Patient dto);

    String deleteById(int id);

    int countByCreatedDateBetween(Date sellDate, Date expiryDate);

    //Page<Patient> findAll(PageRequest pageRequest);
}
