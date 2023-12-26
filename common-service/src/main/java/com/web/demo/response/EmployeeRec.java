package com.web.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "employee_name",
        "employee_salary",
        "employee_age",
        "profile_image"
})
public record EmployeeRec(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("employee_name")
        String employeeName,
        @JsonProperty("employee_salary")
        Integer employeeSalary,
        @JsonProperty("employee_age")
        Integer employeeAge,
        @JsonProperty("profile_image")
        String profileImage
) {
}
