package com.web.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "status",
        "message",
        "size",
        "data"
})
public record SignupResponse(
        @JsonProperty("status")
        Integer status,
        @JsonProperty("message")
        String message,
        @JsonProperty("size")
        Integer size,
        @JsonProperty("data")
        List<UserResponse> data
) {
}
