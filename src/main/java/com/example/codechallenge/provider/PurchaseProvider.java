package com.example.codechallenge.provider;

import static com.example.codechallenge.repository.entities.Order.builderFromPurchaseRequest;
import static com.example.codechallenge.repository.entities.Order.createDeclinedAntifraudOrder;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.antifraud.AntiFraudProvider;
import com.example.codechallenge.provider.bank.BankProvider;
import com.example.codechallenge.provider.model.shared.OperationType;
import com.example.codechallenge.repository.OrderRepository;
import com.example.codechallenge.repository.entities.Order;
import com.example.codechallenge.util.CardEncryption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseProvider {

    private final AntiFraudProvider antiFraudProvider;

    private final BankProvider bankProvider;

    private final OrderRepository orderRepository;

    private final CardEncryption cardEncryption;

    public OperationResponse doPurchase(PurchaseRequest purchaseRequest) {

        try {
            var orderBuilder = builderFromPurchaseRequest(purchaseRequest);

            if (antiFraudProvider.isFraudulentPurchase(purchaseRequest)) {

                Order order = createDeclinedAntifraudOrder(orderBuilder);
                orderRepository.save(order);
                return buildDeclinedOperationResponse(order);
            }

        } catch (Exception e){

            return buildErrorOperationResponse(o);
        }


        return null;
    }

    private OperationResponse buildDeclinedOperationResponse(Order order) {

        return OperationResponse.builder()
                .withOperationId(order.getId())
                .withResponseMessage(order.getResponseMessage())
                .withType(OperationType.PURCHASE)
                .withState(order.getState())
                .build();

    }

    private OperationResponse buildErrorOperationResponse(Order order, Exception e) {

    }

    private OperationResponse buildApprovedOperationResponse(Order order) {

    }
}
