package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRec(
        int patientId,
        @Size(min = 2, message = "admin name should have at least 2 characters")
        String adminName,
        @NotBlank(message = "username is not be null and blank")
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank(message = "password is not be null and blank")
        String password,
        //@NotBlank(message = "Invalid Phone number: Empty number")
        //@NotNull(message = "Invalid Phone number: Number is NULL")
        //@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
        long phone,
        int age,
        @Email(message = "Invalid email")
        String email,
        String gender,//enum
        //String bloodGroup,//enum
        String address,
        @NotBlank(message = "experience is not be null and blank")
        String experience,
        String createdDate,
        String updatedDate,
        int userId
) {
}
