package com.web.hosp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "APPOINTMENT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @Column(name = "APPOINT_ID")
    //@SequenceGenerator(name = "PATIENT_DETAILS_ID_SEQ", sequenceName = "PATIENT_DETAILS_ID_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_DETAILS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointId;
    @Column(name = "PATIENT_ID")
    private long patientId;
    @Column(name = "DOCTOR_ID")
    private long doctorId;
    @Column(name = "CONSULTATION")
    private long consultation;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "APPOINTMENT_DATE")
    private Date appointDate;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

}
