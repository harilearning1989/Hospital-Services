package com.web.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "data",
        "message"
})
public record EmpSingleResponseRec(
        @JsonProperty("status")
        String status,
        @JsonProperty("data")
        EmployeeRec data,
        @JsonProperty("message")
        String message
) {
}
