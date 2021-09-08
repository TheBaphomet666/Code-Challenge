package com.example.codechallenge.client.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class SuperBankPaymentRequest {

    private final static String COMMERCE_CODE = "MY_CODE";

    private BigDecimal value;

    private String currency;

    private String encryptedCard;

    private int installments;
}
