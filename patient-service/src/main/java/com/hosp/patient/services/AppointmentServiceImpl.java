package com.hosp.patient.services;

import com.hosp.patient.mapper.DataMappers;
import com.hosp.patient.models.Appointment;
import com.hosp.patient.records.AppointmentRec;
import com.hosp.patient.repos.AppointmentRepository;
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
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private DataMappers dataMappers;

    @Autowired
    public AppointmentServiceImpl setDataMappers(DataMappers dataMappers) {
        this.dataMappers = dataMappers;
        return this;
    }

    @Autowired
    public AppointmentServiceImpl setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        return this;
    }

    @Override
    public AppointmentRec takeAppointment(AppointmentRec dto) {
        Appointment appointment = dataMappers.recordToEntity(dto);
        appointment = appointmentRepository.save(appointment);

        return dataMappers.entityToRecord(appointment);
    }

    @Override
    public List<AppointmentRec> listAllAppoinments() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        return Optional.ofNullable(appointmentList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> dataMappers.entityToRecord(m))
                .toList();
    }

    @Override
    public AppointmentRec updateAppointment(int id, AppointmentRec dto) {
        Optional<Appointment> patientOpt = appointmentRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        Appointment appointment = patientOpt.get();
        dataMappers.updateAppointment(appointment, dto);
        appointment = appointmentRepository.save(appointment);
        return dataMappers.entityToRecord(appointment);
    }

    @Override
    public String deleteById(int id) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CommonConstants.NOT_FOUND_WITH_ID, CommonConstants.PATIENT, id));
        }
        appointmentRepository.deleteById(id);
        return "Deleted";
    }

}
