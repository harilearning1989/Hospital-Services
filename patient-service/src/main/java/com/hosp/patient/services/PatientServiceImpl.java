package com.hosp.patient.services;

import com.hosp.patient.dtos.PatientDTO;
import com.hosp.patient.mapper.DataMappers;
import com.hosp.patient.models.Patient;
import com.hosp.patient.repos.PatientRepository;
import com.web.demo.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private DataMappers dataMappers;

    @Autowired
    public PatientServiceImpl setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        return this;
    }

    @Autowired
    public PatientServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Override
    public PatientDTO registerPatient(PatientDTO dto) {
        Patient patient = dataMappers.patientDtoToEntity(dto);
        patient = patientRepository.save(patient);
        return dataMappers.patientEntityToDto(patient);
    }

    @Override
    public List<PatientDTO> listAllPatientDetails() {
        List<Patient> patientList = patientRepository.findAll();
        return Optional.ofNullable(patientList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> dataMappers.patientEntityToDto(m) )
                .toList();
    }

    @Override
    public PatientDTO updatePatient(int id,PatientDTO dto) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        Patient patient = patientOpt.get();
        dataMappers.updatePatientDetails(patient,dto);
        patient = patientRepository.save(patient);
        return dataMappers.patientEntityToDto(patient);
    }

    @Override
    public String deleteById(int id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT,id));
        }
        patientRepository.deleteById(id);
        Patient patient = patientOpt.get();
        return patient.getFirstName()+CommonConstants.SINGLE_SPACE+patient.getLastName();
    }

    @Override
    public int countByCreatedDateBetween(Date startDate, Date endDate) {
        return patientRepository.countByCreatedDateBetween(startDate,endDate);
    }

    @Override
    public Page<Patient> findAll(PageRequest pageRequest){
        Page<Patient> recordsPage = patientRepository.findAll(pageRequest);
        return recordsPage;
    }

}
