package com.example.codechallenge.provider;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.RefundRequest;
import com.example.codechallenge.provider.antifraud.AntiFraudProvider;
import com.example.codechallenge.provider.bank.BankProvider;
import com.example.codechallenge.repository.OrderRepository;
import com.example.codechallenge.util.CardEncryption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RefundProvider {

    private AntiFraudProvider antiFraudProvider;

    private BankProvider bankProvider;

    private OrderRepository orderRepository;

    private CardEncryption cardEncryption;

    public OperationResponse doRefund(RefundRequest refundRequest) {

        return null;
    }
}
