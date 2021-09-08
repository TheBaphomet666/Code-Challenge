package com.example.codechallenge.provider.antifraud;

import com.example.codechallenge.controller.model.PurchaseRequest;

public interface AntiFraudProvider {

    boolean isFraudulentPurchase(PurchaseRequest purchaseRequest);
}
