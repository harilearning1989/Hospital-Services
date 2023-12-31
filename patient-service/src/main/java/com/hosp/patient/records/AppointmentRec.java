package com.hosp.patient.records;

public record AppointmentRec(
        int appId,
        int patientId,
        String patientName,
        int doctorId,
        String doctorName,
        String specialization,
        String description,
        int consultantFees,
        String createdDate,
        String updatedDate,
        String appointmentDate
) {
}
