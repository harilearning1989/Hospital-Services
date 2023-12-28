package com.hosp.doctor.services;

import com.hosp.doctor.entity.Doctor;
import com.hosp.doctor.mapper.DataMappers;
import com.hosp.doctor.records.DoctorRec;
import com.hosp.doctor.repos.DoctorRepository;
import com.web.demo.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        return this;
    }

    private DataMappers dataMappers;

    @Autowired
    public DoctorServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Override
    public DoctorRec registerDoctor(DoctorRec rec) {
        Doctor doctor = dataMappers.doctorDtoToEntity(rec);
        doctor = doctorRepository.save(doctor);
        return dataMappers.doctorEntityToDto(doctor);
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
        return doctor.getFirstName() + CommonConstants.SINGLE_SPACE + doctor.getLastName();
    }
}
