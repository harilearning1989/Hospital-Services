package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Patient(
        @JsonProperty("id")
        int id,
        @Size(min = 2, message = "user name should have at least 2 characters")
        @JsonProperty("firstName")
        String firstName,
        @NotBlank(message = "lastName is not be null and blank")
        @JsonProperty("lastName")
        String lastName,
        @NotBlank(message = "username is not be null and blank")
        @JsonProperty("username")
        String username,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank(message = "password is not be null and blank")
        String password,
        //@NotBlank(message = "Invalid Phone number: Empty number")
        //@NotNull(message = "Invalid Phone number: Number is NULL")
        //@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
        @JsonProperty("contact")
        long contact,
        @JsonProperty("age")
        int age,
        @Email(message = "Invalid email")
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
