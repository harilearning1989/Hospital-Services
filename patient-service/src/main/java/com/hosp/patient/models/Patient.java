package com.hosp.patient.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PATIENT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @Column(name = "PATIENT_ID")
    //@SequenceGenerator(name = "PATIENT_DETAILS_ID_SEQ", sequenceName = "PATIENT_DETAILS_ID_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_DETAILS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    @Column(name = "PATIENT_NAME")
    private String patientName;
    @Column(name = "AGE")
    private int age;
    @Column(name = "GENDER")
    private String gender;//enum
    //@Column(name = "BLOOD_GROUP")
    //private String bloodGroup;//enum
    @Column(name = "DOB")
    private Date dob;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @Column(name = "USER_ID")
    private long userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "ADDR_PATIENT_ID", referencedColumnName = "PATIENT_ID")
    private Set<AddressDetails> address = new HashSet<>();
}
