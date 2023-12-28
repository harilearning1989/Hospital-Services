package com.hosp.doctor.mapper;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.records.DoctorRec;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {

    @Override
    public Doctor doctorDtoToEntity(DoctorRec dto) {
        Doctor doctor = Doctor.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .password(dto.password())
                .contact(dto.contact())
                .specialist(dto.specialist())
                .experience(dto.experience())
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
        return doctor;
    }

    @Override
    public DoctorRec doctorEntityToDto(Doctor entity) {
        String createdDateTmp = HospitalUtils.convertDateToString(entity.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(entity.getUpdatedDate());
        DoctorRec rec = new DoctorRec(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getSpecialist(),
                entity.getExperience(),
                entity.getContact(),
                entity.getAge(),
                entity.getEmail(),
                entity.getGender(),
                entity.getBloodGroup(),
                entity.getAddress(),
                entity.getCity(),
                entity.getPincode(),
                createdDateTmp,
                updatedDateTmp
        );
        return rec;
    }

    @Override
    public void updateDoctorDetails(Doctor entity, DoctorRec dto) {
        if (StringUtils.isNotBlank(dto.firstName())) {
            entity.setFirstName(dto.firstName());
        }
        if (StringUtils.isNotBlank(dto.lastName())) {
            entity.setLastName(dto.lastName());
        }
        if (dto.contact() > 10
                && HospitalUtils.validateNumber(String.valueOf(dto.contact()))
                && dto.contact() != entity.getContact()) {
            entity.setContact(dto.contact());
        }
        if (dto.age() != entity.getAge()) {
            entity.setAge(dto.age());
        }
        if (StringUtils.isNotBlank(dto.email())
                && !dto.email().equalsIgnoreCase(entity.getEmail())) {
            entity.setEmail(dto.email());
        }
        if (StringUtils.isNotBlank(dto.address())
                && !dto.address().equalsIgnoreCase(entity.getAddress())) {
            entity.setAddress(dto.address());
        }
        if (StringUtils.isNotBlank(dto.city())
                && !dto.city().equalsIgnoreCase(entity.getCity())) {
            entity.setCity(dto.city());
        }
        if (dto.pincode() > 10
                && dto.pincode() != entity.getPincode()) {
            entity.setPincode(dto.pincode());
        }
        entity.setUpdatedDate(new Date());
        if (StringUtils.isNotBlank(dto.specialist())
                && !dto.specialist().equalsIgnoreCase(entity.getSpecialist())) {
            entity.setSpecialist(dto.specialist());
        }
        if (StringUtils.isNotBlank(dto.experience())
                && !dto.experience().equalsIgnoreCase(entity.getExperience())) {
            entity.setExperience(dto.experience());
        }
    }

}
