package com.hosp.doctor.services;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.mapper.DataMappers;
import com.hosp.doctor.records.DoctorRec;
import com.hosp.doctor.repos.DoctorRepository;
import com.hosp.doctor.services.client.CreateUserClientService;
import com.web.demo.constants.CommonConstants;
import com.web.demo.exception.UserAlreadyExistsException;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.SignupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private CreateUserClientService createUserClientService;
    private DataMappers dataMappers;

    @Autowired
    public DoctorServiceImpl setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        return this;
    }

    @Autowired
    public DoctorServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }
    @Autowired
    public DoctorServiceImpl setCreateUserClientService(CreateUserClientService createUserClientService) {
        this.createUserClientService = createUserClientService;
        return this;
    }

    @Override
    public DoctorRec registerDoctor(DoctorRec rec) {
        Set<String> role = new HashSet<>();
        role.add("doctor");
        SignupRequest signupRequest =
                new SignupRequest(0, rec.username(), rec.email(), role, rec.password(), rec.phone());
        SignupResponse signupResponse = createUserClientService.createUser(signupRequest);
        if (signupResponse != null
                && signupResponse.status() != 200) {
            throw new UserAlreadyExistsException(signupResponse.message());
        }
        Doctor doctor = dataMappers.doctorDtoToEntity(rec);
        doctor.setUserId(signupResponse.data().get(0).userId());
        doctor = doctorRepository.save(doctor);
        return dataMappers.entityToUserRecord(doctor,signupRequest);
    }

    @Override
    public List<DoctorRec> listAllDoctorDetails() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return Optional.ofNullable(doctorList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> dataMappers.doctorEntityToDto(m))
                .toList();
    }

    @Override
    public DoctorRec updateDoctor(int id, DoctorRec rec) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.DOCTOR, id));
        }
        Doctor doctor = doctorOpt.get();
        dataMappers.updateDoctorDetails(doctor, rec);
        doctor = doctorRepository.save(doctor);
        return dataMappers.doctorEntityToDto(doctor);
    }

    @Override
    public String deleteById(int id) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.DOCTOR, id));
        }
        doctorRepository.deleteById(id);
        Doctor doctor = doctorOpt.get();
        return doctor.getDoctorName();
    }
}
