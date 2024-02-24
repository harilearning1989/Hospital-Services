package com.web.hosp.controls;

import com.web.demo.constants.CommonConstants;
import com.web.demo.response.GlobalResponse;
import com.web.demo.response.ResponseHandler;
import com.web.hosp.records.AppointRecord;
import com.web.hosp.repos.AppointRepository;
import com.web.hosp.services.AppointService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointRestController.class);
    private AppointService appointService;

    private AppointRepository appointRepository;

    @Autowired
    public AppointRestController setAppointRepository(AppointRepository appointRepository) {
        this.appointRepository = appointRepository;
        return this;
    }

    @Autowired
    public AppointRestController setAppointService(AppointService appointService) {
        this.appointService = appointService;
        return this;
    }

    @PostMapping("take")
    public GlobalResponse takeAppointment(
            @Valid @RequestBody AppointRecord rec) {
        LOGGER.info("takeAppointment");
        rec = appointService.takeAppointment(rec);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.PATIENT, "Appoint"), HttpStatus.OK, rec);
    }

    @GetMapping("list")
    public GlobalResponse listAllAppointments() {
        LOGGER.info("listAllAppointments");
        List<AppointRecord> listAllAppointments = appointService.listAllAppointments();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched Appointments")
                .status(HttpStatus.OK.value())
                .size(listAllAppointments.size())
                .data(listAllAppointments)
                .build();
        return globalResponse;
    }
    @PutMapping("/update/{id}")
    public GlobalResponse updateAppointment(@PathVariable("id") int id, @RequestBody AppointRecord dto) {
        LOGGER.info("update Appointment");
        dto = appointService.updateAppointment(id, dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS,
                        CommonConstants.PATIENT, "Appointment"), HttpStatus.OK, dto);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteAppointmentById(@PathVariable("id") int id) {
        LOGGER.info("deleteAppointmentById");
        String patientName = appointService.deleteAppointmentById(id);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS,
                        CommonConstants.PATIENT, patientName), HttpStatus.OK, null);
    }
}
