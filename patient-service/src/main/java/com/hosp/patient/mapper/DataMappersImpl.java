package com.hosp.patient.mapper;

import com.hosp.patient.models.Appointment;
import com.hosp.patient.models.Patient;
import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.records.PatientRec;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Patient recordToEntity(PatientRec record) {
        Patient patient = Patient.builder()
                .firstName(record.firstName())
                .lastName(record.lastName())
                .username(record.username())
                .password(record.password())
                .contact(record.contact())
                .age(record.age())
                .email(record.email())
                .gender(record.gender())
                .bloodGroup(record.bloodGroup())
                .address(record.address())
                .city(record.city())
                .pincode(record.pincode())
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
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getContact(),
                patient.getAge(),
                patient.getEmail(),
                patient.getGender(),
                patient.getBloodGroup(),
                patient.getAddress(),
                patient.getCity(),
                patient.getPincode(),
                createdDateTmp,
                updatedDateTmp);

        return record;
    }

    @Override
    public void updatePatientDetails(Patient patient, PatientRec record) {
        if (StringUtils.isNotBlank(record.firstName())) {
            patient.setFirstName(record.firstName());
        }
        if (StringUtils.isNotBlank(record.lastName())) {
            patient.setLastName(record.lastName());
        }
        if (record.contact() > 10
                && HospitalUtils.validateNumber(String.valueOf(record.contact()))
                && record.contact() != patient.getContact()) {
            patient.setContact(record.contact());
        }
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
        if (StringUtils.isNotBlank(record.city())
                && !record.city().equalsIgnoreCase(patient.getCity())) {
            patient.setCity(record.city());
        }
        if (record.pincode() > 10
                && record.pincode() != patient.getPincode()) {
            patient.setPincode(record.pincode());
        }
        patient.setUpdatedDate(new Date());
    }

    @Override
    public Appointment recordToEntity(AppointmentRec record) {
        Appointment appointment = Appointment.builder()
                .patientId(record.patientId())
                .doctorId(record.doctorId())
                .createdDate(new Date())
                .updatedDate(new Date())
                .appointmentDate(new Date())
                .build();

        return appointment;
    }

    @Override
    public AppointmentRec entityToRecord(Appointment appointment) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(appointment.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(appointment.getUpdatedDate());
        String appointmentDateTmp = HospitalUtils.convertDateToString(appointment.getAppointmentDate());

        AppointmentRec record = new AppointmentRec(
                appointment.getId(),
                appointment.getPatientId(),
                null,
                appointment.getDoctorId(),
                null,
                null,
                appointment.getDescription(),
                appointment.getConsultation(),
                createdDateTmp,
                updatedDateTmp,
                appointmentDateTmp);
        return record;
    }

    @Override
    public void updateAppointment(Appointment appointment, AppointmentRec record) {
        appointment.setPatientId(record.patientId());
        appointment.setDoctorId(record.doctorId());
        appointment.setDescription(record.description());
        appointment.setConsultation(record.consultantFees());
    }

}
