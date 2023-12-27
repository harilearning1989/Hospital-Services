package com.hosp.patient.mapper;

import com.hosp.patient.dtos.PatientDTO;
import com.hosp.patient.models.Patient;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Patient patientDtoToEntity(PatientDTO dto) {
        Patient patient = Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .contact(dto.getContact())
                .age(dto.getAge())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .bloodGroup(dto.getBloodGroup())
                .address(dto.getAddress())
                .city(dto.getCity())
                .pincode(dto.getPincode())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return patient;
    }

    @Override
    public PatientDTO patientEntityToDto(Patient patient) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(patient.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(patient.getUpdatedDate());

        PatientDTO dto = PatientDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .username(patient.getUsername())
                .password(patient.getPassword())
                .contact(patient.getContact())
                .age(patient.getAge())
                .email(patient.getEmail())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .address(patient.getAddress())
                .city(patient.getCity())
                .pincode(patient.getPincode())
                .createdDate(createdDateTmp)
                .updatedDate(updatedDateTmp)
                .build();
        return dto;
    }

    @Override
    public void updatePatientDetails(Patient patient, PatientDTO dto) {
        if (StringUtils.isNotBlank(dto.getFirstName())) {
            patient.setFirstName(dto.getFirstName());
        }
        if (StringUtils.isNotBlank(dto.getLastName())) {
            patient.setLastName(dto.getLastName());
        }
        if (dto.getContact() > 10
                && HospitalUtils.validateNumber(String.valueOf(dto.getContact()))
                && dto.getContact() != patient.getContact()) {
            patient.setContact(dto.getContact());
        }
        if (dto.getAge() != patient.getAge()) {
            patient.setAge(dto.getAge());
        }
        if (StringUtils.isNotBlank(dto.getEmail())
                && !dto.getEmail().equalsIgnoreCase(patient.getEmail())) {
            patient.setEmail(dto.getEmail());
        }
        if (StringUtils.isNotBlank(dto.getAddress())
                && !dto.getAddress().equalsIgnoreCase(patient.getAddress())) {
            patient.setAddress(dto.getAddress());
        }
        if (StringUtils.isNotBlank(dto.getCity())
                && !dto.getCity().equalsIgnoreCase(patient.getCity())) {
            patient.setCity(dto.getCity());
        }
        if (dto.getPincode() > 10
                && dto.getPincode() != patient.getPincode()) {
            patient.setPincode(dto.getPincode());
        }
        patient.setUpdatedDate(new Date());
    }


}
