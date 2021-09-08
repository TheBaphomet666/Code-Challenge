package com.example.codechallenge.provider.bank;

import com.example.codechallenge.client.SuperBankFeignClient;
import com.example.codechallenge.client.model.SuperBankPaymentRequest;
import com.example.codechallenge.client.model.SuperBankPaymentResponse;
import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.model.PaymentResponse;
import com.example.codechallenge.provider.model.RefundResponse;
import com.example.codechallenge.provider.model.shared.Card;
import com.example.codechallenge.util.BankCardEncryption;
import com.example.codechallenge.util.CardEncryption;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

@Component
public class SuperBankProvider implements BankProvider {

    private SuperBankFeignClient superBankFeignClient;

    private BankCardEncryption bankCardEncryption;

    private CardEncryption cardEncryption;

    private static final String SUPER_BANK_PAYMENT_CIRCUIT_BREAKER = "superBankPayment";


    @Override
    public PaymentResponse doPayment(PurchaseRequest purchaseRequest) {

        Card card = cardEncryption.decryptCard(purchaseRequest.getEncryptedCard());

        String bankEncryptedCard = bankCardEncryption.encryptCard(card);
        var request = SuperBankPaymentRequest.builder()
                .encryptedCard(bankEncryptedCard)
                .installments(purchaseRequest.getInstallmentsNumber())
                .value(purchaseRequest.getAmount().getValue())
                .currency(purchaseRequest.getAmount().getCurrency())
                .build();

        return doPayment(request).toPaymentResponse();
    }

    @Override

    public RefundResponse doRefund() {
        return null;
    }

    @Retry(name = "retryPayment")
    @CircuitBreaker(name = SUPER_BANK_PAYMENT_CIRCUIT_BREAKER)
    private SuperBankPaymentResponse doPayment(SuperBankPaymentRequest paymentRequest){

        return superBankFeignClient.pay(paymentRequest);
    }
}
