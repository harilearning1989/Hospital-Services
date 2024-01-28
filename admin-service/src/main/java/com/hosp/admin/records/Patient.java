package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Patient(
        @JsonProperty("patientId")
        int patientId,
        @JsonProperty("patientName")
        String patientName,
        @JsonProperty("phone")
        long phone,
        @JsonProperty("age")
        int age,
        @JsonProperty("gender")
        String gender,//enum
        @JsonProperty("bloodGroup")
        String bloodGroup,//enum
        @JsonProperty("address")
        String address,
        @JsonProperty("createdDate")
        String createdDate,
        @JsonProperty("updatedDate")
        String updatedDate
) {
}
