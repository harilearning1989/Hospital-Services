package com.hosp.admin.controls;

import com.hosp.admin.services.client.EmployeeClientService;
import com.web.demo.response.EmpResponseRec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/emp")
public class EmployeeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);
    private EmployeeClientService employeeClientService;

    @Autowired
    public EmployeeRestController setEmployeeClientService(EmployeeClientService employeeClientService) {
        this.employeeClientService = employeeClientService;
        return this;
    }

    @GetMapping("list")
    public EmpResponseRec listAllEmployees() {
        LOGGER.info("Inside listAllEmployees");
        EmpResponseRec response = employeeClientService.listAllEmployees();
        return response;
    }
}
