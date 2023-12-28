package com.hosp.admin.services;

import com.hosp.admin.records.Patient;
import com.hosp.admin.records.PatientResponse;
import com.hosp.admin.services.client.PatientClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientClientService patientClientService;

    @Autowired
    public PatientServiceImpl setPatientClientService(PatientClientService patientClientService) {
        this.patientClientService = patientClientService;
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
        return patientClientService.listAllPatientDetails();
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
