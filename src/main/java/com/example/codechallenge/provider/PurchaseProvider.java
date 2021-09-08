package com.example.codechallenge.provider;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.PurchaseRequestDTO;
import com.example.codechallenge.provider.antifraud.AntiFraudProvider;
import com.example.codechallenge.provider.bank.BankProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseProvider {

    private AntiFraudProvider antiFraudProvider;

    private BankProvider bankProvider;

    public OperationResponse doPurchase(PurchaseRequestDTO purchaseRequestDTO) {

        return null;
    }
}
