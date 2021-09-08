package com.example.codechallenge.provider.bank;

import com.example.codechallenge.client.SuperBankFeignClient;
import com.example.codechallenge.provider.model.PaymentResponse;
import com.example.codechallenge.provider.model.RefundResponse;
import org.springframework.stereotype.Component;

@Component
public class SuperBankProvider implements BankProvider {

    private SuperBankFeignClient fraudMaxFeignClient;


    @Override
    public PaymentResponse doPayment() {
        return null;
    }

    @Override
    public RefundResponse doRefund() {
        return null;
    }
}
