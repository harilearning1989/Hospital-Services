package com.hosp.patient.services;

import com.hosp.patient.records.AppointmentRec;

import java.util.List;

public interface AppointmentService {
    AppointmentRec takeAppointment(AppointmentRec appointmentRec);

    List<AppointmentRec> listAllAppoinments();

    AppointmentRec updateAppointment(int id, AppointmentRec dto);

    String deleteById(int id);
}
