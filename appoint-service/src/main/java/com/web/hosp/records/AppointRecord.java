package com.web.hosp.records;

public record AppointRecord(
        int appointId,
        long patientId,
        long doctorId,
        long consultation,
        String description,
        String appointDate,
        String createdDate,
        String updatedDate
) {
}
