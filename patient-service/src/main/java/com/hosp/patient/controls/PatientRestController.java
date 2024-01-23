package com.hosp.patient.controls;

import com.hosp.patient.models.Patient;
import com.hosp.patient.records.PatientRec;
import com.hosp.patient.repos.PatientRepository;
import com.hosp.patient.response.GlobalResponse;
import com.hosp.patient.response.ResponseHandler;
import com.hosp.patient.services.PatientService;
import com.web.demo.constants.CommonConstants;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestController.class);
    private PatientService patientService;

    private PatientRepository patientRepository;

    @Autowired
    public PatientRestController setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        return this;
    }

    @Autowired
    public PatientRestController setPatientService(PatientService patientService) {
        this.patientService = patientService;
        return this;
    }

    @PostMapping("/create")
    public GlobalResponse registerPatient(@Valid @RequestBody PatientRec dto,
                                          @RequestHeader(value = "id") String userId,
                                          @RequestHeader(value = "role") String role) {
        LOGGER.info("registerPatient");
        dto = patientService.registerPatient(dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.PATIENT, dto.firstName() + " " + dto.lastName()), HttpStatus.CREATED, dto);
    }

    @PostMapping("register")
    public GlobalResponse registerPatientTemp(
            @Valid @RequestBody PatientRec dto, BindingResult bindingResult) {
        LOGGER.info("registerPatientTemp");
        /*if (bindingResult.hasErrors()){
            System.out.println("errors");
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError o : allErrors){
                System.out.println("error -->  " + o.getDefaultMessage());
            }
            GlobalResponse globalResponse = new GlobalResponse();
            globalResponse.setErrors(allErrors);
            return new ResponseEntity<>(globalResponse);
        }*/
        dto = patientService.registerPatient(dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.PATIENT, dto.firstName() + " " + dto.lastName()), HttpStatus.OK, dto);
    }

    @GetMapping("list")
    public GlobalResponse listAllPatients() {
        LOGGER.info("listAllPatientDetails");
        List<PatientRec> patientList = patientService.listAllPatients();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched Patient Data")
                .status(HttpStatus.OK.value())
                .size(patientList.size())
                .data(patientList)
                .build();
        return globalResponse;
    }

    @GetMapping("listTmp")
    public GlobalResponse listAllPatientDetailsTmp() {
        LOGGER.info("listAllPatientDetailsTmp");
        List<PatientRec> patientList = patientService.listAllPatients();
        return ResponseHandler.generateResponseList(null, HttpStatus.OK, patientList);
    }

    @PutMapping("/update/{id}")
    public GlobalResponse updatePatient(@PathVariable("id") int id, @RequestBody PatientRec dto) {
        LOGGER.info("updatePatient");
        dto = patientService.updatePatient(id, dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS,
                        CommonConstants.PATIENT, dto.firstName() + CommonConstants.SINGLE_SPACE
                                + dto.lastName()), HttpStatus.OK, dto);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteTutorial(@PathVariable("id") int id) {
        LOGGER.info("deleteTutorial");
        String patientName = patientService.deleteById(id);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS,
                        CommonConstants.PATIENT, patientName), HttpStatus.OK, null);
    }

    @GetMapping("count")
    public int findByExpiryDateBetween() throws Exception {
        LOGGER.info("findByExpiryDateBetween");
        Date sellDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-01");
        Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-01");
        int count = patientService.countByCreatedDateBetween(sellDate, expiryDate);
        System.out.println("countByCreatedDateBetween = " + count);
        return count;
    }

    @GetMapping("paging")
    private List<Patient> findAll() {
        LOGGER.info("findAll");
        List<Patient> allEmployees = new ArrayList<>();

        // First page of employees -- 5000 results per page
        PageRequest pageRequest = PageRequest.of(0, 5000);
        Page<Patient> employeePage = patientService.findAll(pageRequest);
        allEmployees.addAll(employeePage.getContent());

        // All the remaining employees
        while (employeePage.hasNext()) {
            Page<Patient> nextPageOfEmployees = patientRepository.findAll(employeePage.nextPageable());
            allEmployees.addAll(nextPageOfEmployees.getContent());

            // update the page reference to the current page
            employeePage = nextPageOfEmployees;
        }

        /*List<Patient> targets = new ArrayList(sourcePage.getContent().size());
        for (Source source : sourcePage) {
            targets.add(convert(source, parameters));
        }
        Pageable pageable = new PageRequest(sourcePage.getNumber(), sourcePage.getSize(), sourcePage.getSort());
        Page<Target> targetPage = new PageImpl(targets, pageable, sourcePage.getTotalElements());
        return targetPage;*/


        return allEmployees;
    }

   /* @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/
}
