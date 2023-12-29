package com.hosp.patient.mapper;

import com.hosp.patient.models.Patient;
import com.hosp.patient.records.PatientRec;

public interface DataMappers {
    Patient patientDtoToEntity(PatientRec dto);

    PatientRec patientEntityToDto(Patient patient);

    void updatePatientDetails(Patient patient, PatientRec dto);

}
