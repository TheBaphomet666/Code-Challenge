package com.example.codechallenge.client.model;

import java.io.Serializable;

import com.example.codechallenge.provider.model.PaymentResponse;
import lombok.Getter;

@Getter
public class SuperBankPaymentResponse implements Serializable {

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
