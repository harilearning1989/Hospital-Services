package com.hosp.patient.services.client;

import com.web.demo.records.SignupRequest;
import com.web.demo.response.EmpResponseRec;
import com.web.demo.response.SignupResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;

@HttpExchange(url = "", accept = "application/json", contentType = "application/json")
public interface CreateUserClientService {

    @GetExchange("/employees")
    Flux<EmpResponseRec> getAll();

    @PostExchange("/auth/signup")
    SignupResponse createUser(@RequestBody SignupRequest signupRequest);
    @GetExchange("/user/list")
    SignupResponse getAllUsers(@RequestParam Set<Long> userIds,
                               @RequestHeader Map<String, String> headers);

    @DeleteExchange("user/deleteUserById")
    String deleteUserById(@RequestParam(name = "userId") long userId,
                          @RequestHeader Map<String, String> headers);

}
