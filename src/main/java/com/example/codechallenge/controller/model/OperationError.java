package com.example.codechallenge.controller.model;

import lombok.Getter;

@Getter
public class OperationError {

    private String ErrorMessage;

    private OperationErrorType errorType;
}
