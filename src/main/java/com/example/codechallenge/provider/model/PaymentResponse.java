package com.example.codechallenge.provider.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private String paymentId;

    private String state;

    private String responseMessage;

    private String bankEntity;
}
