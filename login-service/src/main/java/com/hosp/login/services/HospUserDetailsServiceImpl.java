package com.hosp.login.services;

import com.hosp.login.mappers.DataMappers;
import com.hosp.login.models.User;
import com.hosp.login.repos.UserRepository;
import com.web.demo.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospUserDetailsServiceImpl implements HospUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospUserDetailsServiceImpl.class);

    private DataMappers dataMappers;
    private UserRepository userRepository;

    @Autowired
    public HospUserDetailsServiceImpl setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    @Autowired
    public HospUserDetailsServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Override
    public List<UserResponse> listAllUsers() {
        LOGGER.info("Enters in listAllUsers");
        List<User> listUsers = userRepository.findAll();
        List<UserResponse> userResponseList = Optional.ofNullable(listUsers)
                .get()
                .stream()
                .map(m -> dataMappers.entityToRecord(m))
                .toList();
        return userResponseList;
    }

    @Override
    public String deleteUserById(long userId) {
        LOGGER.info("User Deleted Successfully");
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

}
