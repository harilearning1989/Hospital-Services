package com.web.hosp.mapper;

import com.web.hosp.models.Appointment;
import com.web.hosp.records.AppointRecord;

public interface DataMappers {
    Appointment recordToEntity(AppointRecord record);

    AppointRecord entityToRecord(Appointment appointment);

    void updateAppointmentDetails(Appointment appointment, AppointRecord record);

}
