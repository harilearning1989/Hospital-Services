package com.hosp.admin.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDetails {

    @Id
    @Column(name = "ADDR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addrId;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIP")
    private int zip;
}
