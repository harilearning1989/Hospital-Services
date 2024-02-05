package com.hosp.login.controls;

import com.hosp.login.services.HospUserDetailsService;
import com.web.demo.response.GlobalResponse;
import com.web.demo.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("user")
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    private HospUserDetailsService userDetailsService;

    @Autowired
    public UserRestController setUserDetailsService(HospUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        return this;
    }

    @GetMapping("list")
    public GlobalResponse listAllUsers(@RequestParam(name = "userIds") Set<Long> userIds) {
        LOGGER.info("listAllUsers");
        List<UserResponse> userList = userDetailsService.listAllUsers();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched User Data")
                .status(HttpStatus.OK.value())
                .size(userList.size())
                .data(userList)
                .build();
        return globalResponse;
    }

    @DeleteMapping(name = "deleteUserById",value = "deleteUserById")
    public String deleteUserById(@RequestParam(name = "userId") long userId) {
        LOGGER.info("deleteUserById::" + userId);
        return userDetailsService.deleteUserById(userId);
    }
}
