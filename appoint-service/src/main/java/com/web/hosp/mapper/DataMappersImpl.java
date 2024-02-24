package com.web.hosp.mapper;

import com.web.demo.utils.HospitalUtils;
import com.web.hosp.models.Appointment;
import com.web.hosp.records.AppointRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Appointment recordToEntity(AppointRecord record) {
        Appointment.AppointmentBuilder appointmentBuilder = Appointment.builder();
        appointmentBuilder.patientId(record.patientId())
                .doctorId(record.doctorId())
                .createdDate(new Date())
                .updatedDate(new Date());
        if (StringUtils.isNotBlank(record.appointDate())) {
            appointmentBuilder.appointDate(new Date(record.appointDate()));
        } else {
            appointmentBuilder.appointDate(new Date());
        }
        return appointmentBuilder.build();
    }

    @Override
    public AppointRecord entityToRecord(Appointment appointment) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(appointment.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(appointment.getUpdatedDate());
        String appointDateTmp = HospitalUtils.convertDateToString(appointment.getAppointDate());

        AppointRecord record = new AppointRecord(
                appointment.getAppointId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getConsultation(),
                appointment.getDescription(),
                appointDateTmp,
                createdDateTmp,
                updatedDateTmp
        );
        return record;
    }

    @Override
    public void updateAppointmentDetails(Appointment admin, AppointRecord record) {
        admin.setUpdatedDate(new Date());
    }


}
