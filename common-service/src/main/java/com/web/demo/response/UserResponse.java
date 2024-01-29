package com.web.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "userId",
        "username",
        "email",
        "phone",
        "roles"
})
public record UserResponse(
        @JsonProperty("userId")
        Integer userId,
        @JsonProperty("username")
        String username,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        Long phone,
        @JsonProperty("roles")
        List<RoleResponse> roles
) {
}