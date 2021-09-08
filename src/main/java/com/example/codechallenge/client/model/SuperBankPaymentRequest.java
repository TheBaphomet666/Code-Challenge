package com.example.codechallenge.client.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SuperBankPaymentRequest implements Serializable {

    private final static String COMMERCE_CODE = "MY_CODE";

    private BigDecimal value;

    private String currency;

    private String encryptedCard;

    private int installments;
}
