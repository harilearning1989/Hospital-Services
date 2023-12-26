package com.hosp.login.controls;

import com.web.demo.response.EmpResponseRec;
import com.web.demo.response.EmpSingleResponseRec;
import com.web.demo.services.EmployeeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HelloWorldRestController {

    private EmployeeClientService employeeClientService;

    @Autowired
    public HelloWorldRestController setEmployeeClientService(EmployeeClientService employeeClientService) {
        this.employeeClientService = employeeClientService;
        return this;
    }

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }


    @GetMapping("/list")
    public EmpResponseRec listAllEmployees() {
        return employeeClientService.listAllEmployees();
    }

    @GetMapping("/byId/{id}")
    public EmpSingleResponseRec getById(@PathVariable int id) {
        return employeeClientService.getById(id);
    }

    public void getAll() {
        /*employeeClientService.getAll().subscribe(
                data -> LOGGER.info("All Students: {}", data)
        );
        employeeClientService.getById(1L).subscribe(
                data -> LOGGER.info("Student by studentId: {}", data)
        );
        employeeClientService.save(new Student(1, "Ravi", "Kada", "ravi.kada@xyz.com"))
                .subscribe(
                        data -> LOGGER.info("Student created: {}", data)
                );
        employeeClientService.delete(1L).subscribe(
                data -> LOGGER.info("Student deleted : {}", data)
        );*/
    }

}
