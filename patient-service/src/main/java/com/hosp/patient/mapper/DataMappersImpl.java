package com.hosp.patient.mapper;

import com.hosp.patient.models.AddressDetails;
import com.hosp.patient.models.Appointment;
import com.hosp.patient.models.Patient;
import com.hosp.patient.records.AddressRecord;
import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.records.PatientRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.UserResponse;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Patient recordToEntity(PatientRec record) {
        AddressDetails addressDetails = AddressDetails.builder()
                .street(record.addressRecord().street())
                .city(record.addressRecord().city())
                .state(record.addressRecord().state())
                .zip(record.addressRecord().zip())
                .build();
        Set<AddressDetails> addressDetailsSet = new HashSet<>();
        addressDetailsSet.add(addressDetails);

        Patient patient = Patient.builder()
                .patientName(record.patientName())
                .age(record.age())
                .gender(record.gender())
                .address(addressDetailsSet)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return patient;
    }

    @Override
    public PatientRec entityToRecord(Patient patient, UserResponse userResponse) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());
        PatientRec record = null;

        Optional<AddressDetails> addrOpt =
                patient.getAddress().stream().findFirst();
        AddressRecord addressRecord = null;
        if(addrOpt.isPresent()){
            AddressDetails address = addrOpt.get();
            addressRecord = new AddressRecord(
                    address.getAddrId(),
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZip()
            );
        }

        if (userResponse != null) {
            record = new PatientRec(
                    patient.getPatientId(),
                    patient.getPatientName(),
                    userResponse.username(),
                    null,
                    userResponse.phone(),
                    patient.getAge(),
                    userResponse.email(),
                    patient.getGender(),
                    addressRecord,
                    createdDateTmp,
                    updatedDateTmp,
                    patient.getUserId()
            );
        } else {
            record = new PatientRec(
                    patient.getPatientId(),
                    patient.getPatientName(),
                    null,
                    null,
                    null,
                    patient.getAge(),
                    null,
                    patient.getGender(),
                    addressRecord,
                    createdDateTmp,
                    updatedDateTmp,
                    patient.getUserId());
        }
        return record;
    }

    @Override
    public PatientRec entityToUserRecord(Patient patient, SignupRequest signupRequest) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());

        AddressDetails addrOpt =
                patient.getAddress().stream().findFirst().get();
        AddressRecord addressRecord = new AddressRecord(
                addrOpt.getAddrId(),
                addrOpt.getStreet(),
                addrOpt.getCity(),
                addrOpt.getState(),
                addrOpt.getZip()
        );

        return new PatientRec(
                patient.getPatientId(),
                patient.getPatientName(),
                signupRequest.username(),
                null,
                signupRequest.phone(),
                patient.getAge(),
                signupRequest.email(),
                patient.getGender(),
                addressRecord,
                createdDateTmp,
                updatedDateTmp,
                patient.getUserId()
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
        /*if (StringUtils.isNotBlank(record.address())
                && !record.address().equalsIgnoreCase(patient.getAddress())) {
            patient.setAddress(record.address());
        }*/
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
