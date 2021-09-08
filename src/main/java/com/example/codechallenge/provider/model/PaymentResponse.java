package com.example.codechallenge.provider.model;

import lombok.Data;

@Data
public class PaymentResponse {

    private String paymentId;

    private String state;

    private String responseMessage;

    private String bankEntity;
}
