package com.hosp.doctor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "DOCTOR_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {

    @Id
    @Column(name = "id")
    //@SequenceGenerator(name = "DOCTOR_DETAILS_ID_SEQ", sequenceName = "DOCTOR_DETAILS_ID_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCTOR_DETAILS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "FIRST_NAME")
    private String doctorName;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE")
    private long phone;
    @Column(name = "SPECIALIST")
    private String specialist;
    @Column(name = "EXPERIENCE")
    private String experience;
    @Column(name = "AGE")
    private int age;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GENDER")
    private String gender;//enum
    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;//enum
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CITY")
    private String city;
    @Column(name = "PINCODE")
    private int pincode;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

}
