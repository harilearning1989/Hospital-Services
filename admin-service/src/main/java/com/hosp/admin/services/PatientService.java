package com.hosp.admin.services;

import com.hosp.admin.dtos.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface PatientService {

    PatientDTO registerPatient(PatientDTO patientDTO);

    List<PatientDTO> listAllPatientDetails();
    PatientDTO getPatientById(int id);

    PatientDTO updatePatient(int id,PatientDTO dto);

    String deleteById(int id);

    int countByCreatedDateBetween(Date sellDate, Date expiryDate);

    //Page<Patient> findAll(PageRequest pageRequest);
}
