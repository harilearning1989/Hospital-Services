package com.hosp.patient.mapper;

import com.hosp.patient.models.Appointment;
import com.hosp.patient.models.Patient;
import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.records.PatientRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Patient recordToEntity(PatientRec record) {
        Patient patient = Patient.builder()
                .patientName(record.patientName())
                .age(record.age())
                .email(record.email())
                .gender(record.gender())
                .address(record.address())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return patient;
    }

    @Override
    public PatientRec entityToRecord(Patient patient) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());

        PatientRec record = new PatientRec(
                patient.getPatientId(),
                patient.getPatientName(),
                null,
                null,
                0,
                patient.getAge(),
                null,
                patient.getGender(),
                patient.getAddress(),
                createdDateTmp,
                updatedDateTmp
        );

        return record;
    }

    @Override
    public PatientRec entityToUserRecord(Patient patient, SignupRequest signupRequest) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());

        return new PatientRec(
                patient.getPatientId(),
                patient.getPatientName(),
                signupRequest.username(),
                null,
                signupRequest.phone(),
                patient.getAge(),
                signupRequest.email(),
                patient.getGender(),
                patient.getAddress(),
                createdDateTmp,
                updatedDateTmp
        );
    }

    @Override
    public void updatePatientDetails(Patient patient, PatientRec record) {
        if (StringUtils.isNotBlank(record.patientName())) {
            patient.setPatientName(record.patientName());
        }
        /*if (record.phone() > 10
                //&& HospitalUtils.validateNumber(String.valueOf(record.contact()))
                && record.phone() != patient.getPhone()) {
            patient.setPhone(record.phone());
        }*/
        if (record.age() != patient.getAge()) {
            patient.setAge(record.age());
        }
        if (StringUtils.isNotBlank(record.email())
                && !record.email().equalsIgnoreCase(patient.getEmail())) {
            patient.setEmail(record.email());
        }
        if (StringUtils.isNotBlank(record.address())
                && !record.address().equalsIgnoreCase(patient.getAddress())) {
            patient.setAddress(record.address());
        }
        /*if (StringUtils.isNotBlank(record.city())
                && !record.city().equalsIgnoreCase(patient.getCity())) {
            patient.setCity(record.city());
        }
        if (record.pincode() > 10
                && record.pincode() != patient.getPincode()) {
            patient.setPincode(record.pincode());
        }*/
        patient.setUpdatedDate(new Date());
    }

    @Override
    public Appointment recordToEntity(AppointmentRec record) {
        Appointment appointment = Appointment.builder()
                .patientId(record.patientId())
                .doctorId(record.doctorId())
                .consultationFees(record.consultantFees())
                .description(record.description())
                .createdDate(new Date())
                .updatedDate(new Date())
                .appointmentDate(new Date())
                .build();

        return appointment;
    }

    @Override
    public AppointmentRec entityToRecord(Appointment appointment) {
        //Wed Aug 16 12:29:39 IST 2023
        /*String createdDateTmp = HospitalUtils.convertDateToString(appointment.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(appointment.getUpdatedDate());
        String appointmentDateTmp = HospitalUtils.convertDateToString(appointment.getAppointmentDate());*/

        AppointmentRec record = new AppointmentRec(
                appointment.getId(),
                appointment.getPatientId(),
                null,
                appointment.getDoctorId(),
                null,
                null,
                appointment.getDescription(),
                appointment.getConsultationFees(),
                null, null, null);
                /*createdDateTmp,
                updatedDateTmp,
                appointmentDateTmp);*/
        return record;
    }

    @Override
    public void updateAppointment(Appointment appointment, AppointmentRec record) {
        appointment.setPatientId(record.patientId());
        appointment.setDoctorId(record.doctorId());
        appointment.setDescription(record.description());
        appointment.setConsultationFees(record.consultantFees());
    }

}
