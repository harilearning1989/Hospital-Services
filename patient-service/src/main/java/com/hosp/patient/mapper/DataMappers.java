package com.hosp.patient.mapper;

import com.hosp.patient.dtos.PatientDTO;
import com.hosp.patient.models.Patient;

public interface DataMappers {
    Patient patientDtoToEntity(PatientDTO dto);

    PatientDTO patientEntityToDto(Patient patient);

    void updatePatientDetails(Patient patient, PatientDTO dto);

}
