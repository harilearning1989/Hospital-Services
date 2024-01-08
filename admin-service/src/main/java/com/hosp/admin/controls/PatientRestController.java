package com.hosp.admin.controls;

import com.hosp.admin.records.Patient;
import com.hosp.admin.records.PatientResponse;
import com.hosp.admin.services.PatientService;
import com.hosp.admin.services.client.PatientClientService;
import com.web.demo.constants.CommonConstants;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/patient")
@CrossOrigin(origins = "*")
public class PatientRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientRestController.class);

    @Value("${patient.rest.url}")
    private String patientBaseUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    private PatientService patientService;
    private PatientClientService patientClientService;

    @Autowired
    public PatientRestController setPatientService(PatientService patientService) {
        this.patientService = patientService;
        return this;
    }

    @Autowired
    public PatientRestController setPatientClientService(PatientClientService patientClientService) {
        this.patientClientService = patientClientService;
        return this;
    }

    @GetMapping("list")
    public PatientResponse listAllPatientDetails(@RequestHeader Map<String, String> headers) {
        /*Map<String, Object> returnValue = new HashMap<>();

        Enumeration<String> hearderNames = request.getHeaderNames();
        while(hearderNames.hasMoreElements()) {
            String headerName = hearderNames.nextElement();
            returnValue.put(headerName, request.getHeader(headerName));
        }*/

        headers.forEach((key, value) -> {
            LOGGER.info(String.format("Header '%s' = %s", key, value));
        });
        LOGGER.info("Inside listAllPatientDetails");
        return patientService.listAllPatientDetails();
    }

    @GetMapping("listTemp")
    public PatientResponse listAllPatientDetailsTemp(@RequestHeader Map<String, String> headers) {
        LOGGER.info("Inside listAllPatientDetailsTemp");
        return patientClientService.listAllPatientDetails(headers);
    }

    @GetMapping("listWebClient")
    public PatientResponse listAllPatientDetailsWebClient() {
        LOGGER.info("Inside listAllPatientDetailsWebClient");
        Mono<PatientResponse> result = webClientBuilder.baseUrl(patientBaseUrl)
                .build()
                .get()
                .uri("patient/list")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PatientResponse.class);

        PatientResponse response = result.block();
        List<Patient> patientList = response.data();

        return response;
    }

    @GetMapping("listRestTemplate")
    public PatientResponse listAllPatientDetailsRestTemplate() {
        LOGGER.info("Inside listAllPatientDetailsRestTemplate");
        ResponseEntity<PatientResponse> response =
                restTemplate.getForEntity(patientBaseUrl+"patient/list", PatientResponse.class);
        List<Patient> patientList = response.getBody().data();
        PatientResponse patientResponse=  response.getBody();
        return patientResponse;
    }

    /*@GetMapping("list1")
    public Flux<PatientDTO> getAllShops() {
        return loadBalancedWebClientBuilder().filter(lbFunction).build().get()
                .uri("http://shopservice/myshops")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToFlux(PatientDTO.class);
    }*/

    @PostMapping("/create")
    public Patient registerPatient(@Valid @RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    @PostMapping("register")
    public Patient registerPatientTemp(
            @Valid @RequestBody Patient patient, BindingResult bindingResult) {

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
        return patientService.registerPatient(patient);
    }


    @GetMapping("byId")
    public Patient getPatientById(@RequestParam("id") int id) {
        Patient patient = patientService.getPatientById(id);
        return patient;
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable("id") int id, @RequestBody Patient dto) {
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") int id) {
        String patientName = patientService.deleteById(id);
        return String.format(CommonConstants.DELETED_SUCCESS, CommonConstants.PATIENT, patientName);
    }

    @GetMapping("count")
    public int findByExpiryDateBetween() throws Exception {
        Date sellDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-01");
        Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-01");
        int count = patientService.countByCreatedDateBetween(sellDate, expiryDate);
        System.out.println("countByCreatedDateBetween = " + count);
        return count;
    }

   /* @GetMapping("paging")
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
        return allEmployees;
    }*/

        /*List<Patient> targets = new ArrayList(sourcePage.getContent().size());
        for (Source source : sourcePage) {
            targets.add(convert(source, parameters));
        }
        Pageable pageable = new PageRequest(sourcePage.getNumber(), sourcePage.getSize(), sourcePage.getSort());
        Page<Target> targetPage = new PageImpl(targets, pageable, sourcePage.getTotalElements());
        return targetPage;*/





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
