package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRec(
        int adminId,
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
        String phone,
        int age,
        @Email(message = "Invalid email")
        String email,
        String gender,//enum
        //String bloodGroup,//enum
        AddressRecord addressRecord,
        String createdDate,
        String updatedDate,
        long userId
) {
}
