package com.hosp.patient.services;

import com.hosp.patient.models.Patient;
import com.hosp.patient.records.PatientRec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface PatientService {
    PatientRec registerPatient(PatientRec patientDTO);

    List<PatientRec> listAllPatients();

    PatientRec updatePatient(int id,PatientRec dto);

    String deletePatientById(int patientId,long userId);

    int countByCreatedDateBetween(Date sellDate, Date expiryDate);

    Page<Patient> findAll(PageRequest pageRequest);
}
