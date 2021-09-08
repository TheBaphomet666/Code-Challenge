package com.example.codechallenge.provider.antifraud;

import com.example.codechallenge.provider.model.FraudValidationResponse;

public interface AntiFraudProvider {

    FraudValidationResponse validateFraud();
}
