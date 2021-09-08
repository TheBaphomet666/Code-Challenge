package com.example.codechallenge.provider;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.PurchaseRequestDTO;
import com.example.codechallenge.controller.model.RefundRequestDTO;
import com.example.codechallenge.provider.antifraud.AntiFraudProvider;
import com.example.codechallenge.provider.bank.BankProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RefundProvider {

    private AntiFraudProvider antiFraudProvider;

    private BankProvider bankProvider;

    public OperationResponse doRefund(RefundRequestDTO refundRequestDTO) {

        return null;
    }
}
