package com.hosp.doctor.controls;

import com.hosp.doctor.records.DoctorRec;
import com.hosp.doctor.services.DoctorService;
import com.web.demo.constants.CommonConstants;
import com.web.demo.response.GlobalResponse;
import com.web.demo.response.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorRestController.class);
    private DoctorService doctorService;

    @Autowired
    public DoctorRestController setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
        return this;
    }

    @PostMapping("register")
    public GlobalResponse registerDoctor(@RequestBody DoctorRec doctor){
        doctor = doctorService.registerDoctor(doctor);

        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.DOCTOR,doctor.doctorName()), HttpStatus.OK, doctor);
    }

    @GetMapping("list")
    public GlobalResponse listAllDoctorDetails(){
        List<DoctorRec> doctorList = doctorService.listAllDoctorDetails();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched Patient Data")
                .status(HttpStatus.OK.value())
                .size(doctorList.size())
                .data(doctorList)
                .build();
        return globalResponse;
    }

    @PutMapping("/update/{id}")
    public GlobalResponse updateDoctor(@PathVariable("id") int id, @RequestBody DoctorRec doctor) {
        doctor = doctorService.updateDoctor(id,doctor);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS,
                        CommonConstants.DOCTOR,doctor.doctorName()), HttpStatus.OK, doctor);
    }

    @DeleteMapping("/delete/{doctorId}/{userId}")
    public GlobalResponse deleteDoctorById(@PathVariable("doctorId") int doctorId,
                                            @PathVariable("userId") long userId) {
        LOGGER.info("deleteDoctorById");
        String doctorName = doctorService.deleteDoctorById(doctorId,userId);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS,
                        CommonConstants.DOCTOR, doctorName), HttpStatus.OK, null);
    }
}
