package com.example.codechallenge.provider.antifraud;

import com.example.codechallenge.client.FraudMaxFeignClient;
import com.example.codechallenge.provider.model.FraudValidationResponse;

public class FraudMaxProvider implements AntiFraudProvider {

    private FraudMaxFeignClient fraudMaxFeignClient;


    @Override
    public FraudValidationResponse validateFraud() {
        return null;
    }
}
