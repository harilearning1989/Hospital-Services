package com.hosp.admin.services;

import com.hosp.admin.records.DoctorRec;
import com.hosp.admin.records.DoctorResponse;
import com.hosp.admin.services.client.DoctorClientService;
import com.web.demo.utils.HospitalUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorClientService doctorClientService;

    @Autowired
    public DoctorServiceImpl setDoctorClientService(DoctorClientService doctorClientService) {
        this.doctorClientService = doctorClientService;
        return this;
    }
    private HttpServletRequest httpServletRequest;

    @Autowired
    public DoctorServiceImpl setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

    @Override
    public DoctorResponse registerDoctor(DoctorRec rec) {
        return doctorClientService.registerDoctor(rec);
    }

    @Override
    public DoctorResponse listAllDoctorDetails() {
        Map<String, String> headersMap = HospitalUtils.getHttpHeaders(httpServletRequest);
        return doctorClientService.listAllDoctorDetails(headersMap);
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
