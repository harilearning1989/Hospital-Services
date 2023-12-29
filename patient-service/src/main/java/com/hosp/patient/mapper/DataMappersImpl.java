package com.hosp.patient.mapper;

import com.hosp.patient.models.Patient;
import com.hosp.patient.records.PatientRec;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Patient patientDtoToEntity(PatientRec dto) {
        Patient patient = Patient.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .password(dto.password())
                .contact(dto.contact())
                .age(dto.age())
                .email(dto.email())
                .gender(dto.gender())
                .bloodGroup(dto.bloodGroup())
                .address(dto.address())
                .city(dto.city())
                .pincode(dto.pincode())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return patient;
    }

    @Override
    public PatientRec patientEntityToDto(Patient patient) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());

        PatientRec dto = new PatientRec(
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

        return dto;
    }

    @Override
    public void updatePatientDetails(Patient patient, PatientRec dto) {
        if (StringUtils.isNotBlank(dto.firstName())) {
            patient.setFirstName(dto.firstName());
        }
        if (StringUtils.isNotBlank(dto.lastName())) {
            patient.setLastName(dto.lastName());
        }
        if (dto.contact() > 10
                && HospitalUtils.validateNumber(String.valueOf(dto.contact()))
                && dto.contact() != patient.getContact()) {
            patient.setContact(dto.contact());
        }
        if (dto.age() != patient.getAge()) {
            patient.setAge(dto.age());
        }
        if (StringUtils.isNotBlank(dto.email())
                && !dto.email().equalsIgnoreCase(patient.getEmail())) {
            patient.setEmail(dto.email());
        }
        if (StringUtils.isNotBlank(dto.address())
                && !dto.address().equalsIgnoreCase(patient.getAddress())) {
            patient.setAddress(dto.address());
        }
        if (StringUtils.isNotBlank(dto.city())
                && !dto.city().equalsIgnoreCase(patient.getCity())) {
            patient.setCity(dto.city());
        }
        if (dto.pincode() > 10
                && dto.pincode() != patient.getPincode()) {
            patient.setPincode(dto.pincode());
        }
        patient.setUpdatedDate(new Date());
    }

}
