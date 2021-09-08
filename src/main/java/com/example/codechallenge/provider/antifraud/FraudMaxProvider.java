package com.example.codechallenge.provider.antifraud;

import java.math.BigDecimal;

import com.example.codechallenge.client.FraudMaxFeignClient;
import com.example.codechallenge.client.model.AntifraudValidationRequest;
import com.example.codechallenge.client.model.FraudMaxResponse;
import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.model.exception.ClientConnectorException;
import com.example.codechallenge.util.CardEncryption;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class FraudMaxProvider implements AntiFraudProvider {

    private final static BigDecimal MAXIMUM_RISK = new BigDecimal("37.2");

    private final FraudMaxFeignClient fraudMaxFeignClient;

    private final CardEncryption cardEncryption;

    @Override
    public boolean isFraudulentPurchase(PurchaseRequest purchaseRequest) {

        var antiFraudRequest = AntifraudValidationRequest.builder()
                .payer(purchaseRequest.getPayer())
                .amount(purchaseRequest.getAmount())
                .card(cardEncryption.decryptCard(purchaseRequest.getEncryptedCard())).build();
        try {
            var response = fraudMaxFeignClient.validateFraud(antiFraudRequest);
            return processResponse(response);
        }catch (Exception e){
            
            log.error("Error occurred with fraudMax", e);
            throw new ClientConnectorException("Error with FraudMax",e);
        }
    }

    private boolean processResponse(FraudMaxResponse response) {

        return response.getRisk().compareTo(MAXIMUM_RISK) < 0 && !response.isCovered();
    }
}
