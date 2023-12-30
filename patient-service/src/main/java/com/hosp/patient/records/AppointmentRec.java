package com.hosp.patient.records;

import java.util.Date;

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
