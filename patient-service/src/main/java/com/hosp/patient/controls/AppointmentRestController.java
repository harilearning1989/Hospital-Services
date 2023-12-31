package com.hosp.patient.controls;

import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.response.GlobalResponse;
import com.hosp.patient.response.ResponseHandler;
import com.hosp.patient.services.AppointmentService;
import com.web.demo.constants.CommonConstants;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient/appointment")
@CrossOrigin(origins = "*")
public class AppointmentRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentRestController.class);

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentRestController setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
        return this;
    }

    @PostMapping("/create")
    public GlobalResponse registerPatient(@Valid @RequestBody AppointmentRec appointmentRec) {
        LOGGER.info("registerPatient");
        appointmentRec = appointmentService.takeAppointment(appointmentRec);
        return ResponseHandler.generateResponse("Appointment has taken successfully", HttpStatus.CREATED, appointmentRec);
    }

    @GetMapping("list")
    public GlobalResponse listAllAppoinments() {
        LOGGER.info("listAllAppoinments");
        List<AppointmentRec> patientList = appointmentService.listAllAppoinments();
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS), HttpStatus.CREATED, patientList);
    }

    @PutMapping("/update/{id}")
    public GlobalResponse updateAppointment(@PathVariable("id") int id, @RequestBody AppointmentRec dto) {
        LOGGER.info("updatePatient");
        dto = appointmentService.updateAppointment(id, dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS,
                        CommonConstants.PATIENT), HttpStatus.OK, dto);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteTutorial(@PathVariable("id") int id) {
        LOGGER.info("deleteTutorial");
        String patientName = appointmentService.deleteById(id);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS,
                        CommonConstants.PATIENT, patientName), HttpStatus.OK, null);
    }
}
