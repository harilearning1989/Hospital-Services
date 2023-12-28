package com.hosp.admin.controls;

import com.hosp.admin.records.DoctorRec;
import com.hosp.admin.records.DoctorResponse;
import com.hosp.admin.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/doctor")
@CrossOrigin(origins = "*")
public class DoctorRestController {

    private DoctorService doctorService;

    @Autowired
    public DoctorRestController setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
        return this;
    }

    @PostMapping("register")
    public DoctorResponse registerDoctor(@RequestBody DoctorRec doctor) {
        return doctorService.registerDoctor(doctor);
    }

    @GetMapping("list")
    public DoctorResponse listAllDoctorDetails() {
        return doctorService.listAllDoctorDetails();
    }

    @PutMapping("/update/{id}")
    public DoctorResponse updateDoctor(@PathVariable("id") int id, @RequestBody DoctorRec doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/delete/{id}")
    public DoctorResponse deleteTutorial(@PathVariable("id") int id) {
        return doctorService.deleteById(id);
    }
}
