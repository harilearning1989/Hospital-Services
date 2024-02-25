package com.hosp.admin.mapper;

import com.hosp.admin.models.AddressDetails;
import com.hosp.admin.models.Admin;
import com.hosp.admin.records.AddressRecord;
import com.hosp.admin.records.AdminRec;
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
    public Admin recordToEntity(AdminRec record) {
        AddressDetails addressDetails = AddressDetails.builder()
                .street(record.addressRecord().street())
                .city(record.addressRecord().city())
                .state(record.addressRecord().state())
                .zip(record.addressRecord().zip())
                .build();
        Set<AddressDetails> addressDetailsSet = new HashSet<>();
        addressDetailsSet.add(addressDetails);
        Admin admin = Admin.builder()
                .adminName(record.adminName())
                .age(record.age())
                .gender(record.gender())
                .address(addressDetailsSet)
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        return admin;
    }

    @Override
    public AdminRec entityToRecord(Admin admin, UserResponse userResponse) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(admin.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(admin.getUpdatedDate());

        AdminRec record = null;

        Optional<AddressDetails> addrOpt =
                admin.getAddress().stream().findFirst();
        AddressRecord addressRecord = null;
        if (addrOpt.isPresent()) {
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
            record = new AdminRec(
                    admin.getAdminId(),
                    admin.getAdminName(),
                    userResponse.username(),
                    null,
                    userResponse.phone(),
                    admin.getAge(),
                    userResponse.email(),
                    admin.getGender(),
                    addressRecord,
                    createdDateTmp,
                    updatedDateTmp,
                    admin.getUserId()
            );
        } else {
            record = new AdminRec(
                    admin.getAdminId(),
                    admin.getAdminName(),
                    null,
                    null,
                    "No Phone",
                    admin.getAge(),
                    null,
                    admin.getGender(),
                    addressRecord,
                    createdDateTmp,
                    updatedDateTmp,
                    admin.getUserId()
            );
        }
        return record;
    }

    @Override
    public AdminRec entityToUserRecord(Admin admin, SignupRequest signupRequest) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(admin.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(admin.getUpdatedDate());

        Optional<AddressDetails> addrOpt =
                admin.getAddress().stream().findFirst();
        AddressRecord addressRecord = null;
        if (addrOpt.isPresent()) {
            AddressDetails address = addrOpt.get();
            addressRecord = new AddressRecord(
                    address.getAddrId(),
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZip()
            );
        }

        return new AdminRec(
                admin.getAdminId(),
                admin.getAdminName(),
                signupRequest.username(),
                null,
                signupRequest.phone(),
                admin.getAge(),
                signupRequest.email(),
                admin.getGender(),
                addressRecord,
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
        admin.setUpdatedDate(new Date());
    }


}
