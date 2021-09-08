package com.example.codechallenge.controller.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.codechallenge.provider.model.shared.Amount;
import lombok.Getter;

@Getter
public class PurchaseRequest extends OperationRequest {

    @NotNull(message = "amount can not be empty")
    private Amount amount;

    @NotBlank(message = "encryptedCard can not be empty")
    private String encryptedCard;

    @Min(value = 1, message = "Installments must be at least 1")
    @Max(value = 32, message = "Installments must be a most 32")
    private int installmentsNumber;
}
