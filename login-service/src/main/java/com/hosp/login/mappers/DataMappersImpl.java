package com.hosp.login.mappers;

import com.hosp.login.models.User;
import com.web.demo.response.UserResponse;
import com.web.demo.utils.HospitalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataMappersImpl implements DataMappers {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataMappersImpl.class);

    @Override
    public UserResponse entityToRecord(User user) {
        //Wed Aug 16 12:29:39 IST 2023
        String createdDateTmp = HospitalUtils.convertDateToString(user.getCreatedDate());
        String updatedDateTmp = HospitalUtils.convertDateToString(user.getUpdatedDate());

        UserResponse userResponse = new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                null,
                createdDateTmp,
                updatedDateTmp
        );
        return userResponse;
    }

}
