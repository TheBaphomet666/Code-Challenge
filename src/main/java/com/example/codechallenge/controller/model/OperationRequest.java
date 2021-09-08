package com.example.codechallenge.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.codechallenge.provider.model.shared.Payer;
import lombok.Getter;

@Getter
public abstract class OperationRequest {

    @NotBlank(message = "clientId can not be empty")
    private String clientId;

    @NotNull(message = "payer can not be empty")
    private Payer payer;
}
