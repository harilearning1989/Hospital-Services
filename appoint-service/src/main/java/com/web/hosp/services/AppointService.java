package com.web.hosp.services;

import com.web.hosp.records.AppointRecord;

import java.util.List;

public interface AppointService {
    AppointRecord takeAppointment(AppointRecord appointRecord);

    List<AppointRecord> listAllAppointments();

    AppointRecord updateAppointment(int id,AppointRecord dto);

    String deleteAppointmentById(int id);

}
