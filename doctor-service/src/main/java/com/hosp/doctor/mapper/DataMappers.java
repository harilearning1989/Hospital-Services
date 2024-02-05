package com.hosp.doctor.mapper;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.records.DoctorRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.UserResponse;

public interface DataMappers {

    Doctor doctorRecToEntity(DoctorRec rec);

    DoctorRec doctorEntityToDto(Doctor doctor, UserResponse userResponse);

    void updateDoctorDetails(Doctor doctor, DoctorRec dto);

    DoctorRec entityToUserRecord(Doctor doctor, SignupRequest signupRequest);
}
