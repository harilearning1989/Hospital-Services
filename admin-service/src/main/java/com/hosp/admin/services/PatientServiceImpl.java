package com.hosp.admin.services;

import com.hosp.admin.dtos.PatientDTO;
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
    public PatientDTO registerPatient(PatientDTO patientDTO) {
        return null;
    }

    @Override
    public PatientDTO getPatientById(int id) {
        return patientClientService.getPatientById(id);
    }

    @Override
    public List<PatientDTO> listAllPatientDetails() {
        return patientClientService.listAllPatientDetails();
    }

    @Override
    public PatientDTO updatePatient(int id, PatientDTO dto) {
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
