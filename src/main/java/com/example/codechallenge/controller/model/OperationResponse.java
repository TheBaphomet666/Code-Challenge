package com.example.codechallenge.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class OperationResponse {

    private String operationId;

    private Status status;

    private Type type;

    private String responseMessage;

    private OperationError error;

    public HttpStatus getHttpResponseStatus() {

        HttpStatus httpStatus;

        if(error == null) {

            httpStatus = HttpStatus.OK;
        }
        else if (OperationErrorType.INVALID_PARAMETERS.equals(error.getErrorType())) {

            httpStatus = HttpStatus.BAD_REQUEST;
        } else {

            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return httpStatus;
    }

}
