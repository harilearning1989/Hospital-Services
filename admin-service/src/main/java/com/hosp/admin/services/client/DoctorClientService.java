package com.hosp.admin.services.client;

import com.hosp.admin.records.DoctorRec;
import com.hosp.admin.records.DoctorResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange(url = "doctor/", accept = "application/json", contentType = "application/json")
public interface DoctorClientService {

    @PostExchange("register")
    DoctorResponse registerDoctor(@RequestBody DoctorRec doctor);

    @GetExchange("list")
    DoctorResponse listAllDoctorDetails();

    @PutExchange("update/{id}")
    DoctorResponse updateDoctor(@PathVariable("id") int id, @RequestBody DoctorRec doctor);

    @DeleteExchange("delete/{id}")
    DoctorResponse deleteTutorial(@PathVariable("id") int id);

}


