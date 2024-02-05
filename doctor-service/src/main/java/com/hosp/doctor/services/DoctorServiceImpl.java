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
import com.web.demo.response.UserResponse;
import com.web.demo.utils.HospitalUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class);
    private DoctorRepository doctorRepository;
    private CreateUserClientService createUserClientService;
    private DataMappers dataMappers;

    private HttpServletRequest httpServletRequest;

    @Autowired
    public DoctorServiceImpl setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

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
        Doctor doctor = dataMappers.doctorRecToEntity(rec);
        doctor.setUserId(signupResponse.data().get(0).userId());
        doctor = doctorRepository.save(doctor);
        return dataMappers.entityToUserRecord(doctor, signupRequest);
    }

    @Override
    public List<DoctorRec> listAllDoctorDetails() {
        List<Doctor> doctorList = doctorRepository.findAll();

        Set<Long> userIds = Optional.ofNullable(doctorList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Doctor::getUserId)
                .collect(Collectors.toSet());

        Map<Long, Optional<UserResponse>> userMap;
        if (!userIds.isEmpty()) {
            Map<String, String> headersMap = HospitalUtils.getHttpHeaders(httpServletRequest);
            SignupResponse signupResponse = createUserClientService.getAllUsers(userIds, headersMap);

            userMap =
                    Optional.ofNullable(signupResponse.data())
                            .orElseGet(Collections::emptyList)
                            .stream()
                            .collect(groupingBy(UserResponse::userId,
                                    maxBy(comparingLong(UserResponse::userId))));
        } else {
            userMap = new HashMap<>();
        }
        return Optional.ofNullable(doctorList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> {
                    Optional<UserResponse> userOpt = userMap.get(m.getUserId());
                    UserResponse userResponse = userOpt.get();
                    return dataMappers.doctorEntityToDto(m, userResponse);
                })
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
        return dataMappers.doctorEntityToDto(doctor, null);
    }

    @Override
    public String deleteDoctorById(int doctorId, long userId) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.DOCTOR, doctorId));
        }
        doctorRepository.deleteById(doctorId);
        Doctor doctor = doctorOpt.get();
        Map<String, String> headersMap = HospitalUtils.getHttpHeaders(httpServletRequest);
        String response = createUserClientService.deleteUserById(userId, headersMap);
        LOGGER.info("deleteDoctorById::" + response);
        return doctor.getDoctorName();
    }
}
