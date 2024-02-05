package com.hosp.doctor.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DoctorRec(
        int doctorId,
        String doctorName,
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        String specialist,
        String experience,
        long phone,
        int age,
        String email,
        String createdDate,
        String updatedDate,
        long userId
) {
}
