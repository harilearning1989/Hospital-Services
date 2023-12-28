package com.hosp.admin.services;

import com.hosp.admin.records.DoctorRec;
import com.hosp.admin.records.DoctorResponse;
import com.hosp.admin.services.client.DoctorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorClientService doctorClientService;

    @Autowired
    public DoctorServiceImpl setDoctorClientService(DoctorClientService doctorClientService) {
        this.doctorClientService = doctorClientService;
        return this;
    }

    @Override
    public DoctorResponse registerDoctor(DoctorRec rec) {
        return doctorClientService.registerDoctor(rec);
    }

    @Override
    public DoctorResponse listAllDoctorDetails() {
        return doctorClientService.listAllDoctorDetails();
    }

    @Override
    public DoctorResponse updateDoctor(int id, DoctorRec rec) {
        return doctorClientService.updateDoctor(id,rec);
    }

    @Override
    public DoctorResponse deleteById(int id) {
        return doctorClientService.deleteTutorial(id);
    }

}
