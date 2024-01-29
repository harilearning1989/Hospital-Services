package com.hosp.admin.mapper;

import com.hosp.admin.models.Admin;
import com.hosp.admin.records.AdminRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.utils.HospitalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataMappersImpl implements DataMappers {
    @Override
    public Admin recordToEntity(AdminRec record) {
        Admin patient = Admin.builder()
                .adminName(record.adminName())
                .age(record.age())
                .gender(record.gender())
                .address(record.address())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return patient;
    }

    @Override
    public AdminRec entityToRecord(Admin admin) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(admin.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(admin.getUpdatedDate());

        AdminRec record = new AdminRec(
                admin.getAdminId(),
                admin.getAdminName(),
                null,
                null,
                0,
                admin.getAge(),
                null,
                admin.getGender(),
                admin.getAddress(),
                createdDateTmp,
                updatedDateTmp,
                admin.getUserId()
        );

        return record;
    }

    @Override
    public AdminRec entityToUserRecord(Admin admin, SignupRequest signupRequest) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(admin.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(admin.getUpdatedDate());

        return new AdminRec(
                admin.getAdminId(),
                admin.getAdminName(),
                signupRequest.username(),
                null,
                signupRequest.phone(),
                admin.getAge(),
                signupRequest.email(),
                admin.getGender(),
                admin.getAddress(),
                createdDateTmp,
                updatedDateTmp,
                admin.getUserId()
        );
    }

    @Override
    public void updatePatientDetails(Admin admin, AdminRec record) {
        if (StringUtils.isNotBlank(record.adminName())) {
            admin.setAdminName(record.adminName());
        }
        if (record.age() != admin.getAge()) {
            admin.setAge(record.age());
        }
        if (StringUtils.isNotBlank(record.address())
                && !record.address().equalsIgnoreCase(admin.getAddress())) {
            admin.setAddress(record.address());
        }
        admin.setUpdatedDate(new Date());
    }


}
