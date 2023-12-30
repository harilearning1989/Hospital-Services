package com.hosp.patient.mapper;

import com.hosp.patient.models.Appointment;
import com.hosp.patient.models.Patient;
import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.records.PatientRec;

public interface DataMappers {
    Patient recordToEntity(PatientRec record);

    PatientRec entityToRecord(Patient patient);

    void updatePatientDetails(Patient patient, PatientRec record);

    Appointment recordToEntity(AppointmentRec record);

    AppointmentRec entityToRecord(Appointment appointment);

    void updateAppointment(Appointment appointment, AppointmentRec dto);
}
