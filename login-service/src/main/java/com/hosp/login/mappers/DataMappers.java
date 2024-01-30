package com.hosp.login.mappers;

import com.hosp.login.models.User;
import com.web.demo.response.UserResponse;

public interface DataMappers {

    UserResponse entityToRecord(User user);
}
