package com.hosp.doctor.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DoctorRec(
        int id,
        String firstName,
        String lastName,
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        String specialist,
        String experience,
        int contact,
        int age,
        String email,
        String gender,//enum
        String bloodGroup,//enum
        String address,
        String city,
        int pincode,
        String createdDate,
        String updatedDate
) {
}
