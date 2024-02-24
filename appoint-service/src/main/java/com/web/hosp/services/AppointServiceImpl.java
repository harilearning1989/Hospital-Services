package com.web.hosp.services;

import com.web.demo.constants.CommonConstants;
import com.web.hosp.mapper.DataMappers;
import com.web.hosp.models.Appointment;
import com.web.hosp.records.AppointRecord;
import com.web.hosp.repos.AppointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointServiceImpl implements AppointService {

    private AppointRepository appointRepository;
    private DataMappers dataMappers;

    @Autowired
    public AppointServiceImpl setPatientRepository(AppointRepository appointRepository) {
        this.appointRepository = appointRepository;
        return this;
    }

    @Autowired
    public AppointServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Override
    public AppointRecord takeAppointment(AppointRecord appointRecord) {
        Appointment appointment = dataMappers.recordToEntity(appointRecord);
        appointment = appointRepository.save(appointment);
        appointRecord = dataMappers.entityToRecord(appointment);
        return appointRecord;
    }

    @Override
    public List<AppointRecord> listAllAppointments() {
        List<Appointment> appointmentList = appointRepository.findAll();
        return Optional.ofNullable(appointmentList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> dataMappers.entityToRecord(m))
                .toList();
    }

    @Override
    public AppointRecord updateAppointment(int id, AppointRecord dto) {
        Optional<Appointment> appointmentOpt = appointRepository.findById(id);
        if (appointmentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        Appointment patient = appointmentOpt.get();
        dataMappers.updateAppointmentDetails(patient, dto);
        patient = appointRepository.save(patient);
        return dataMappers.entityToRecord(patient);
    }

    @Override
    public String deleteAppointmentById(int id) {
        Optional<Appointment> adminOpt = appointRepository.findById(id);
        if (adminOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        appointRepository.deleteById(id);
        Appointment appointment = adminOpt.get();
        //return appointment.getAdminName();
        return "Deleted Appointment";
    }

}
