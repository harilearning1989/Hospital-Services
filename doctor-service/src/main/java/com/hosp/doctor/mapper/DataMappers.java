package com.hosp.doctor.mapper;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.records.DoctorRec;

public interface DataMappers {

    Doctor doctorDtoToEntity(DoctorRec rec);

    DoctorRec doctorEntityToDto(Doctor doctor);

    void updateDoctorDetails(Doctor doctor, DoctorRec dto);

}
