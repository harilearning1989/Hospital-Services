package com.hosp.doctor.services.client;

import com.web.demo.records.SignupRequest;
import com.web.demo.response.EmpResponseRec;
import com.web.demo.response.SignupResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

@HttpExchange(url = "", accept = "application/json", contentType = "application/json")
public interface CreateUserClientService {

    @GetExchange("/employees")
    Flux<EmpResponseRec> getAll();

    @PostExchange("/auth/signup")
    SignupResponse createUser(@RequestBody SignupRequest signupRequest);
}
