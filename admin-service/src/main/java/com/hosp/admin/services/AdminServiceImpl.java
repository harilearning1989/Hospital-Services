package com.hosp.admin.services;

import com.hosp.admin.mapper.DataMappers;
import com.hosp.admin.models.Admin;
import com.hosp.admin.records.AdminRec;
import com.hosp.admin.repos.AdminRepository;
import com.hosp.admin.services.client.CreateUserClientService;
import com.web.demo.constants.CommonConstants;
import com.web.demo.exception.UserAlreadyExistsException;
import com.web.demo.records.SignupRequest;
import com.web.demo.response.SignupResponse;
import com.web.demo.response.UserResponse;
import com.web.demo.utils.HospitalUtils;
import jakarta.servlet.http.HttpServletRequest;
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
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private DataMappers dataMappers;
    private CreateUserClientService createUserClientService;

    private HttpServletRequest httpServletRequest;

    @Autowired
    public AdminServiceImpl setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        return this;
    }

    @Autowired
    public AdminServiceImpl setPatientRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        return this;
    }

    @Autowired
    public AdminServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Autowired
    public AdminServiceImpl setCreateUserClientService(CreateUserClientService createUserClientService) {
        this.createUserClientService = createUserClientService;
        return this;
    }

    @Override
    public AdminRec registerPatient(AdminRec rec) {
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupRequest signupRequest =
                new SignupRequest(0, rec.username(), rec.email(), role, rec.password(), rec.phone());
        SignupResponse signupResponse = createUserClientService.createUser(signupRequest);
        if (signupResponse != null
                && signupResponse.status() != 200) {
            throw new UserAlreadyExistsException(signupResponse.message());
        }
        Admin admin = dataMappers.recordToEntity(rec);
        admin.setUserId(signupResponse.data().get(0).userId());
        admin = adminRepository.save(admin);
        return dataMappers.entityToUserRecord(admin, signupRequest);
    }

    @Override
    public List<AdminRec> listAllPatients() {
        List<Admin> patientList = adminRepository.findAll();

        Set<Long> userIds = Optional.ofNullable(patientList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Admin::getUserId)
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

        return Optional.ofNullable(patientList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> {
                    Optional<UserResponse> userOpt = userMap.get(m.getUserId());
                    UserResponse userResponse = userOpt.get();
                    return dataMappers.entityToRecord(m, userResponse);
                })
                .toList();
    }

    @Override
    public AdminRec updatePatient(int id, AdminRec dto) {
        Optional<Admin> patientOpt = adminRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        Admin patient = patientOpt.get();
        dataMappers.updatePatientDetails(patient, dto);
        patient = adminRepository.save(patient);
        return dataMappers.entityToRecord(patient, null);
    }

    @Override
    public String deletePatientById(int id) {
        Optional<Admin> adminOpt = adminRepository.findById(id);
        if (adminOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        adminRepository.deleteById(id);
        Admin admin = adminOpt.get();
        return admin.getAdminName();
    }

}
