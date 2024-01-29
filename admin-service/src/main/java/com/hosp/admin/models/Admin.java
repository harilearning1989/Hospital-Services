package com.hosp.admin.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "PATIENT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {

    @Id
    @Column(name = "ADMIN_ID")
    //@SequenceGenerator(name = "PATIENT_DETAILS_ID_SEQ", sequenceName = "PATIENT_DETAILS_ID_SEQ", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_DETAILS_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    @Column(name = "ADMIN_NAME")
    private String adminName;
    @Column(name = "EXPERIENCE")
    private String experience;
    @Column(name = "AGE")
    private int age;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @Column(name = "USER_ID")
    private int userId;
}
