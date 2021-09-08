package com.example.codechallenge.provider;

import static com.example.codechallenge.repository.entities.Order.builderFromPurchaseRequest;
import static com.example.codechallenge.repository.entities.Order.createDeclinedAntifraudOrder;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.antifraud.AntiFraudProvider;
import com.example.codechallenge.provider.bank.BankProvider;
import com.example.codechallenge.provider.model.exception.ClientConnectorException;
import com.example.codechallenge.provider.model.exception.InvalidRequestException;
import com.example.codechallenge.provider.model.exception.InvalidResponseException;
import com.example.codechallenge.provider.model.shared.OperationError;
import com.example.codechallenge.provider.model.shared.OperationErrorType;
import com.example.codechallenge.provider.model.shared.OperationState;
import com.example.codechallenge.provider.model.shared.OperationStatus;
import com.example.codechallenge.provider.model.shared.OperationType;
import com.example.codechallenge.repository.OrderRepository;
import com.example.codechallenge.repository.entities.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PurchaseProvider {

    private final AntiFraudProvider antiFraudProvider;

    private final BankProvider bankProvider;

    private final OrderRepository orderRepository;

    public OperationResponse doPurchase(PurchaseRequest purchaseRequest) {

        var orderBuilder = builderFromPurchaseRequest(purchaseRequest);
        Order order;
        try {

            if (antiFraudProvider.isFraudulentPurchase(purchaseRequest)) {

                order = createDeclinedAntifraudOrder(orderBuilder);
                orderRepository.save(order);
                return buildOperationResponse(order);
            }
            var bankResponse = bankProvider.doPayment(purchaseRequest);

            order = orderBuilder.withPaymentPlatformId(bankResponse.getPaymentId())
                    .withStatus(OperationStatus.COMPLETED.name())
                    .withResponseMessage(bankResponse.getResponseMessage())
                    .withBank(bankResponse.getBankEntity())
                    .withState(OperationState.fromString(bankResponse.getState()).name()).build();
            orderRepository.save(order);
            return buildOperationResponse(order);

        } catch (Exception e){

            order = orderBuilder.withStatus(OperationStatus.ERROR.name())
                    .withResponseMessage(e.getMessage())
                    .withState(OperationState.ERROR.name()).build();
            log.error("Error occurred on purchase [{}],",order.getId(), e);
            orderRepository.save(order);
            return buildErrorOperationResponse(order, e);
        }
    }

    private OperationResponse buildOperationResponse(Order order) {

        return OperationResponse.builder()
                .withOperationId(order.getId())
                .withResponseMessage(order.getResponseMessage())
                .withType(OperationType.PURCHASE)
                .withState(order.getState())
                .build();

    }

    private OperationResponse buildErrorOperationResponse(Order order, Exception e) {

        return OperationResponse.builder()
                .withOperationId(order.getId())
                .withResponseMessage(e.getMessage())
                .withType(OperationType.PURCHASE)
                .withState(order.getState())
                .withError(getOperationError(e))
                .build();
    }

    private OperationError getOperationError(Exception exception) {

        OperationError operationError ;
        if (exception instanceof InvalidRequestException ) {

            operationError = OperationError.builder()
                    .withErrorType(OperationErrorType.INVALID_PARAMETERS)
                    .withErrorMessage("Invalid Parameters").build();

        } else if(exception instanceof InvalidResponseException) {

            operationError = OperationError.builder().withErrorType(OperationErrorType.PAYMENT_PROVIDER_ERROR)
                    .withErrorMessage("Error response by payment provider").build();

        } else if(exception instanceof ClientConnectorException) {

            operationError = OperationError.builder().withErrorType(OperationErrorType.PAYMENT_PROVIDER_ERROR)
                    .withErrorMessage("Error with payment provider").build();
        } else {

            operationError = OperationError.builder().withErrorType(OperationErrorType.UNEXPECTED)
                    .withErrorMessage("Unexpected error").build();
        }

        return operationError;
    }
}
