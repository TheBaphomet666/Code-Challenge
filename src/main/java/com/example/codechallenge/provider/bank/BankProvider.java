package com.example.codechallenge.provider.bank;

import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.model.PaymentResponse;
import com.example.codechallenge.provider.model.RefundResponse;

public interface BankProvider {

    PaymentResponse doPayment(PurchaseRequest purchaseRequest);

    RefundResponse doRefund();
}
