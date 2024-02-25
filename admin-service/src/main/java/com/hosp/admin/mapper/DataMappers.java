package com.hosp.admin.mapper;

import com.hosp.admin.models.Admin;
import com.hosp.admin.records.AdminRec;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.UserResponse;

public interface DataMappers {
    Admin recordToEntity(AdminRec record);

    AdminRec entityToRecord(Admin admin, UserResponse userResponse);

    void updatePatientDetails(Admin patient, AdminRec record);

    AdminRec entityToUserRecord(Admin patient, SignupRequest signupRequest);
}
