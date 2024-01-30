package com.hosp.admin.controls;

import com.hosp.admin.records.AdminRec;
import com.hosp.admin.repos.AdminRepository;
import com.hosp.admin.services.AdminService;
import com.web.demo.constants.CommonConstants;
import com.web.demo.response.GlobalResponse;
import com.web.demo.response.ResponseHandler;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestController.class);
    private AdminService adminService;

    private AdminRepository adminRepository;

    @Autowired
    public AdminRestController setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        return this;
    }

    @Autowired
    public AdminRestController setAdminService(AdminService adminService) {
        this.adminService = adminService;
        return this;
    }

    @PostMapping("register")
    public GlobalResponse registerPatientTemp(
            @Valid @RequestBody AdminRec rec) {
        LOGGER.info("registerPatientTemp");
        rec = adminService.registerPatient(rec);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.REGISTER_SUCCESS,
                        CommonConstants.PATIENT, rec.adminName()), HttpStatus.OK, rec);
    }

    @GetMapping("list")
    public GlobalResponse listAllPatients() {
        LOGGER.info("listAllPatientDetails");
        List<AdminRec> patientList = adminService.listAllPatients();
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message("Successfully fetched Patient Data")
                .status(HttpStatus.OK.value())
                .size(patientList.size())
                .data(patientList)
                .build();
        return globalResponse;
    }
    @PutMapping("/update/{id}")
    public GlobalResponse updatePatient(@PathVariable("id") int id, @RequestBody AdminRec dto) {
        LOGGER.info("updatePatient");
        dto = adminService.updatePatient(id, dto);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.UPDATED_SUCCESS,
                        CommonConstants.PATIENT, dto.adminName()), HttpStatus.OK, dto);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deletePatientById(@PathVariable("id") int id) {
        LOGGER.info("deletePatientById");
        String patientName = adminService.deletePatientById(id);
        return ResponseHandler.generateResponse(
                String.format(CommonConstants.DELETED_SUCCESS,
                        CommonConstants.PATIENT, patientName), HttpStatus.OK, null);
    }
}
