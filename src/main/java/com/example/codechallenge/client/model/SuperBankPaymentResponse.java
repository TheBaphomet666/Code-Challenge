package com.example.codechallenge.client.model;

import com.example.codechallenge.provider.model.PaymentResponse;

public class SuperBankPaymentResponse {

    private String paymentId;

    private String state;

    private String responseMessage;


    public PaymentResponse toPaymentResponse(){

        return PaymentResponse.builder()
                .bankEntity("SUPERBANK")
                .paymentId(paymentId)
                .responseMessage(responseMessage)
                .state(state)
                .build();
    }
}
