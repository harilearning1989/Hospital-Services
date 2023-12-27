package com.hosp.patient.controls;

import com.hosp.patient.dtos.PatientDTO;
import com.hosp.patient.models.Patient;
import com.hosp.patient.repos.PatientRepository;
import com.hosp.patient.response.GlobalResponse;
import com.hosp.patient.response.ResponseHandler;
import com.hosp.patient.services.PatientService;
import com.web.demo.constants.CommonConstants;
import jakarta.validation.Valid;
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
@CrossOrigin(origins = "*")
public class PatientRestController {

    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public PatientRestController setPatientService(PatientService patientService) {
        this.patientService = patientService;
        return this;
    }

    @PostMapping("/create")
    public ResponseEntity<GlobalResponse> registerPatient(@Valid @RequestBody PatientDTO dto) {
        dto = patientService.registerPatient(dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.PATIENT,dto.getFirstName()+" "+dto.getLastName()), HttpStatus.CREATED, dto);
    }
    @PostMapping("register")
    public ResponseEntity<GlobalResponse> registerPatientTemp(
            @Valid @RequestBody PatientDTO dto, BindingResult bindingResult){

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
                        CommonConstants.PATIENT,dto.getFirstName()+" "+dto.getLastName()), HttpStatus.OK, dto);
    }

    @GetMapping("list")
    public GlobalResponse listAllPatientDetails(){
        List<PatientDTO> patientList = patientService.listAllPatientDetails();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched Patient Data")
                .status(HttpStatus.OK.value())
                .size(patientList.size())
                .data(patientList)
                .build();
        return globalResponse;
    }

    @GetMapping("listTmp")
    public ResponseEntity<GlobalResponse> listAllPatientDetailsTmp(){
        List<PatientDTO> patientList = patientService.listAllPatientDetails();
        return ResponseHandler.generateResponseList(null,HttpStatus.OK, patientList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalResponse> updatePatient(@PathVariable("id") int id, @RequestBody PatientDTO dto) {
        dto = patientService.updatePatient(id,dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS, CommonConstants.PATIENT,dto.getFirstName()+CommonConstants.SINGLE_SPACE+dto.getLastName()), HttpStatus.OK, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalResponse> deleteTutorial(@PathVariable("id") int id) {
        String patientName = patientService.deleteById(id);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS, CommonConstants.PATIENT, patientName), HttpStatus.OK, null);
    }

    @GetMapping("count")
    public int findByExpiryDateBetween() throws Exception {
        Date sellDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-01");
        Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-01");
        int count = patientService.countByCreatedDateBetween(sellDate, expiryDate);
        System.out.println("countByCreatedDateBetween = " + count);
        return count;
    }

    @GetMapping("paging")
    private List<Patient> findAll() {
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
