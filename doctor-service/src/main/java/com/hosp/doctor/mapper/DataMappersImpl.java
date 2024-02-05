package com.hosp.doctor.mapper;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.records.DoctorRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.UserResponse;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {

    @Override
    public Doctor doctorRecToEntity(DoctorRec rec) {
        Doctor doctor = Doctor.builder()
                .doctorName(rec.doctorName())
                .specialist(rec.specialist())
                .experience(rec.experience())
                .age(rec.age())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return doctor;
    }

    @Override
    public DoctorRec doctorEntityToDto(Doctor entity, UserResponse userResponse) {
        String createdDateTmp = HospitalUtils.convertDateToString(entity.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(entity.getUpdatedDate());
        DoctorRec rec = null;
        if (userResponse != null) {
            rec = new DoctorRec(
                    entity.getDoctorId(),
                    entity.getDoctorName(),
                    userResponse.username(),
                    null,
                    entity.getSpecialist(),
                    entity.getExperience(),
                    userResponse.phone(),
                    entity.getAge(),
                    userResponse.email(),
                    createdDateTmp,
                    updatedDateTmp,
                    userResponse.userId());

        } else {
            rec = new DoctorRec(
                    entity.getDoctorId(),
                    entity.getDoctorName(),
                    null,
                    null,
                    entity.getSpecialist(),
                    entity.getExperience(),
                    0,
                    entity.getAge(),
                    null,
                    createdDateTmp,
                    updatedDateTmp,
                    0
            );
        }

        return rec;
    }

    @Override
    public void updateDoctorDetails(Doctor entity, DoctorRec dto) {
        if (StringUtils.isNotBlank(dto.doctorName())) {
            entity.setDoctorName(dto.doctorName());
        }
        /*if (dto.phone() > 10
                && HospitalUtils.validateNumber(String.valueOf(dto.phone()))
                && dto.phone() != entity.getPhone()) {
            entity.setPhone(dto.phone());
        }*/
        if (dto.age() != entity.getAge()) {
            entity.setAge(dto.age());
        }
        /*if (StringUtils.isNotBlank(dto.email())
                && !dto.email().equalsIgnoreCase(entity.getEmail())) {
            entity.setEmail(dto.email());
        }*/
        /*if (StringUtils.isNotBlank(dto.city())
                && !dto.city().equalsIgnoreCase(entity.getCity())) {
            entity.setCity(dto.city());
        }*/
        /*if (dto.pincode() > 10
                && dto.pincode() != entity.getPincode()) {
            entity.setPincode(dto.pincode());
        }*/
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

    @Override
    public DoctorRec entityToUserRecord(Doctor doctor, SignupRequest signupRequest) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(doctor.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(doctor.getUpdatedDate());

        return new DoctorRec(
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                signupRequest.username(),
                null,
                doctor.getSpecialist(),
                doctor.getExperience(),
                signupRequest.phone(),
                doctor.getAge(),
                signupRequest.email(),
                createdDateTmp,
                updatedDateTmp,
                doctor.getUserId()
        );
    }

}
