package com.hosp.patient.services;

import com.hosp.patient.mapper.DataMappers;
import com.hosp.patient.models.Patient;
import com.hosp.patient.records.PatientRec;
import com.hosp.patient.repos.PatientRepository;
import com.hosp.patient.services.client.CreateUserClientService;
import com.web.demo.constants.CommonConstants;
import com.web.demo.exception.UserAlreadyExistsException;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.SignupResponse;
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
    private CreateUserClientService createUserClientService;

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

    @Autowired
    public PatientServiceImpl setCreateUserClientService(CreateUserClientService createUserClientService) {
        this.createUserClientService = createUserClientService;
        return this;
    }

    @Override
    public PatientRec registerPatient(PatientRec rec) {
        Set<String> role = new HashSet<>();
        role.add("patient");
        SignupRequest signupRequest =
                new SignupRequest(0, rec.username(), rec.email(), role, rec.password(), rec.phone());
        SignupResponse signupResponse = createUserClientService.createUser(signupRequest);
        if (signupResponse != null
                && signupResponse.status() != 200) {
            throw new UserAlreadyExistsException(signupResponse.message());
        }
        Patient patient = dataMappers.recordToEntity(rec);
        patient.setUserId(signupResponse.data().get(0).userId());
        patient = patientRepository.save(patient);
        return dataMappers.entityToUserRecord(patient,signupRequest);
    }

    @Override
    public List<PatientRec> listAllPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return Optional.ofNullable(patientList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> dataMappers.entityToRecord(m))
                .toList();
    }

    @Override
    public PatientRec updatePatient(int id, PatientRec dto) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        Patient patient = patientOpt.get();
        dataMappers.updatePatientDetails(patient, dto);
        patient = patientRepository.save(patient);
        return dataMappers.entityToRecord(patient);
    }

    @Override
    public String deletePatientById(int id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        patientRepository.deleteById(id);
        Patient patient = patientOpt.get();
        return patient.getPatientName();
    }

    @Override
    public int countByCreatedDateBetween(Date startDate, Date endDate) {
        return patientRepository.countByCreatedDateBetween(startDate, endDate);
    }

    @Override
    public Page<Patient> findAll(PageRequest pageRequest) {
        Page<Patient> recordsPage = patientRepository.findAll(pageRequest);
        return recordsPage;
    }

}
