package com.hosp.login.services;

import com.web.demo.response.UserResponse;

import java.util.List;

public interface HospUserDetailsService {
    List<UserResponse> listAllUsers();

    String deleteUserById(long userId);
}
