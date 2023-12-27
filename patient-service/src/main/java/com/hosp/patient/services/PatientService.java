package com.hosp.patient.services;

import com.hosp.patient.dtos.PatientDTO;
import com.hosp.patient.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface PatientService {
    PatientDTO registerPatient(PatientDTO patientDTO);

    List<PatientDTO> listAllPatientDetails();

    PatientDTO updatePatient(int id,PatientDTO dto);

    String deleteById(int id);

    int countByCreatedDateBetween(Date sellDate, Date expiryDate);

    Page<Patient> findAll(PageRequest pageRequest);
}
