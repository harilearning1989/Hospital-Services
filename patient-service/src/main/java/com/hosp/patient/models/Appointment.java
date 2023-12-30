package com.hosp.patient.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "APPOINTMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @Column(name = "id")
    //@SequenceGenerator(name = "PATIENT_DETAILS_ID_SEQ", sequenceName = "PATIENT_DETAILS_ID_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_DETAILS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "PATIENT_ID")
    private int patientId;
    @Column(name = "DOCTOR_ID")
    private int doctorId;
    @Column(name = "CONSULTATION")
    private int consultation;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED _DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @Column(name = "APPOINTMENT_DATE")
    private Date appointmentDate;
}
