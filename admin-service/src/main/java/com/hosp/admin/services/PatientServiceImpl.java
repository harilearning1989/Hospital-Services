package com.hosp.admin.services;

import com.hosp.admin.records.Patient;
import com.hosp.admin.records.PatientResponse;
import com.hosp.admin.services.client.PatientClientService;
import com.web.demo.utils.HospitalUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientClientService patientClientService;

    @Autowired
    public PatientServiceImpl setPatientClientService(PatientClientService patientClientService) {
        this.patientClientService = patientClientService;
        return this;
    }

    private HttpServletRequest httpServletRequest;

    @Autowired
    public PatientServiceImpl setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

    @Override
    public Patient registerPatient(Patient patientDTO) {
        return null;
    }

    @Override
    public Patient getPatientById(int id) {
        return patientClientService.getPatientById(id);
    }

    @Override
    public PatientResponse listAllPatientDetails() {
        /*Map<String, List<String>> headersMap = Collections.list(httpServletRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpServletRequest.getHeaders(h))
                ));*/
        Map<String, String> headersMap = HospitalUtils.getHttpHeaders(httpServletRequest);
        return patientClientService.listAllPatientDetails(headersMap);
    }

    @Override
    public Patient updatePatient(int id, Patient dto) {
        return null;
    }

    @Override
    public String deleteById(int id) {
        return null;
    }

    @Override
    public int countByCreatedDateBetween(Date sellDate, Date expiryDate) {
        return 0;
    }
}
