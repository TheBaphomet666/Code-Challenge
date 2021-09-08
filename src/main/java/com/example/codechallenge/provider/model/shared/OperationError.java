package com.example.codechallenge.provider.model.shared;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class OperationError {

    private String ErrorMessage;

    private OperationErrorType errorType;
}
