package com.example.codechallenge.controller.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class PurchaseRequestDTO {

    @NotBlank(message = "clientId can not be empty")
    private String clientId;

    @NotNull(message = "payerDTO can not be empty")
    private PayerDTO payerDTO;

    @NotNull(message = "amountDTO can not be empty")
    private AmountDTO amountDTO;

    @NotBlank(message = "encryptedCard can not be empty")
    private String encryptedCard;

    @Min(value = 1, message = "Installments must be at least 1")
    @Max(value = 32, message = "Installments must be a most 32")
    private int installmentsNumber;
}
