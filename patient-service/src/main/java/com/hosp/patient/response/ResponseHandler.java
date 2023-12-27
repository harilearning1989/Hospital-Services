package com.hosp.patient.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<GlobalResponse> generateResponse(String message, HttpStatus status, Object data) {
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message(message)
                .status(status.value())
                .build();

        if (data != null) {
            globalResponse.setData(Arrays.asList(data));
            globalResponse.setSize(globalResponse.getData().size());
        }
        return new ResponseEntity<>(globalResponse, status);
    }

    public static ResponseEntity<GlobalResponse> generateResponseList(String message, HttpStatus status, List<?> data) {
        GlobalResponse globalResponse = GlobalResponse.builder()
                .message(message)
                .status(status.value())
                .size(data.size())
                .data(data)
                .build();

        return new ResponseEntity<>(globalResponse, status);
    }

    public static ResponseEntity<GlobalResponse> generateErrorResponse(
            Map<String, List<String>> errorsMap, HttpHeaders httpHeaders, HttpStatus httpStatus) {
        GlobalResponse globalResponse = GlobalResponse.builder()
                .status(httpStatus.value())
                .build();

        if (errorsMap != null) {
            globalResponse.setErrors(errorsMap);
        }
        return new ResponseEntity<>(globalResponse, httpStatus);
    }

    public static ResponseEntity<GlobalResponse> generateErrorMapResponse(
            Map<String, String> errorsMap,
            HttpHeaders httpHeaders, HttpStatus httpStatus) {
        GlobalResponse globalResponse = GlobalResponse.builder()
                .status(httpStatus.value())
                .build();

        if (errorsMap != null) {
            globalResponse.setErrorsMap(errorsMap);
        }
        return new ResponseEntity<>(globalResponse, httpStatus);
    }
}