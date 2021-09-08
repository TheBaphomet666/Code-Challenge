package com.example.codechallenge.provider.model.shared;

import com.example.codechallenge.provider.model.shared.OperationErrorType;
import lombok.Getter;

@Getter
public class OperationError {

    private String ErrorMessage;

    private OperationErrorType errorType;
}
