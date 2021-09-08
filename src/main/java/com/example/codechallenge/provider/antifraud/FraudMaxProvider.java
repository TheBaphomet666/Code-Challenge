package com.example.codechallenge.provider.antifraud;

import java.math.BigDecimal;

import com.example.codechallenge.client.FraudMaxFeignClient;
import com.example.codechallenge.client.model.AntifraudValidationRequest;
import com.example.codechallenge.client.model.FraudMaxResponse;
import com.example.codechallenge.controller.model.PurchaseRequest;
import org.springframework.stereotype.Component;

@Component
public class FraudMaxProvider implements AntiFraudProvider {

    private FraudMaxFeignClient fraudMaxFeignClient;

    private final BigDecimal MAXIMUM_RISK = new BigDecimal("37.2");


    @Override
    public boolean isFraudulentPurchase(PurchaseRequest purchaseRequest) {

        var antiFraudRequest = AntifraudValidationRequest.builder().build();
        var response = fraudMaxFeignClient.validateFraud(antiFraudRequest);

        return processResponse(response);
    }

    private boolean processResponse(FraudMaxResponse response) {

        return response.getRisk().compareTo(MAXIMUM_RISK) < 0 && !response.isCovered();
    }
}
