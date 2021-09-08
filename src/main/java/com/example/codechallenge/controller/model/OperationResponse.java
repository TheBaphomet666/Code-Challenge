package com.example.codechallenge.controller.model;

import com.example.codechallenge.provider.model.shared.OperationError;
import com.example.codechallenge.provider.model.shared.OperationErrorType;
import com.example.codechallenge.provider.model.shared.OperationType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder(setterPrefix = "with")
@Getter
public class OperationResponse {

    private String operationId;

    private String state;

    private OperationType type;

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
