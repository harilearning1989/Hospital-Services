package com.hosp.patient.records;

public record AddressRecord(
        int addrId,
        String street,
        String city,
        String state,
        int zip
) {
}
