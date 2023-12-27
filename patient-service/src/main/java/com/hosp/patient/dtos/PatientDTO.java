package com.hosp.patient.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private int id;
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;
    @NotBlank(message = "lastName is not be null and blank")
    private String lastName;
    @NotBlank(message = "username is not be null and blank")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "password is not be null and blank")
    private String password;
    //@NotBlank(message = "Invalid Phone number: Empty number")
    //@NotNull(message = "Invalid Phone number: Number is NULL")
    //@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private int contact;
    private int age;
    @Email(message = "Invalid email")
    private String email;
    private String gender;//enum
    private String bloodGroup;//enum
    private String address;
    private String city;
    private int pincode;
    private String createdDate;
    private String updatedDate;

}
