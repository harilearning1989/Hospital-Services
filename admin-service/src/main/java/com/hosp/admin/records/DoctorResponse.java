package com.hosp.admin.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "size",
        "message",
        "data"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public record DoctorResponse(
        @JsonProperty("status")
        Integer status,
        @JsonProperty("size")
        Integer size,
        @JsonProperty("message")
        String message,
        @JsonProperty("data")
        List<DoctorRec> data
) {
}
