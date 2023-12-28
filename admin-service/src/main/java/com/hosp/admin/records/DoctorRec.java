package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DoctorRec(
        @JsonProperty("id")
        int id,
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("lastName")
        String lastName,
        @JsonProperty("username")
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        @JsonProperty("specialist")
        String specialist,
        @JsonProperty("experience")
        String experience,
        @JsonProperty("contact")
        int contact,
        @JsonProperty("age")
        int age,
        @JsonProperty("email")
        String email,
        @JsonProperty("gender")
        String gender,//enum
        @JsonProperty("bloodGroup")
        String bloodGroup,//enum
        @JsonProperty("address")
        String address,
        @JsonProperty("city")
        String city,
        @JsonProperty("pincode")
        int pincode,
        @JsonProperty("createdDate")
        String createdDate,
        @JsonProperty("updatedDate")
        String updatedDate
) {
}
