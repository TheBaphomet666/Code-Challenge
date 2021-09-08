package com.example.codechallenge.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.codechallenge.controller.model.OperationRequest;
import com.example.codechallenge.controller.model.PurchaseRequest;
import com.example.codechallenge.provider.model.shared.OperationState;
import com.example.codechallenge.provider.model.shared.OperationStatus;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(schema = "payments", name = "order")
@Data
@Builder(setterPrefix = "with")
public class Order implements Serializable {

    /**
     * The serializable class version.
     */
    private static final long serialVersionUID = 6978817551505286685L;

    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    private String id;

    @Column(name = "client_id", nullable = false, length = 255)
    @Length(max = 255)
    private String clientId;

    @Column(name = "payment_platform_id", nullable = false, length = 255)
    @Length(max = 255)
    private String paymentPlatformId;

    @Column(name = "state", length = 25)
    @Length(max = 25)
    private String state;

    @Column(name = "status", length = 25)
    @Length(max = 25)
    private String status;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "bank", length = 25)
    @Length(max = 25)
    private String bank;

    @Column(name = "response_message", nullable = false, length = 255)
    @Length(max = 255)
    private String responseMessage;

    @Column(name = "currency", length = 25)
    @Length(max = 25)
    private String currency;

    /**
     * Default constructor.
     */
    public Order() {

        super();
    }

    public static Order.OrderBuilder builderFromOperation(OperationRequest operationRequest) {

        return Order.builder()
                .withId(UUID.randomUUID().toString())
                .withClientId(operationRequest.getClientId())
                .withCreationDate(LocalDate.now())
                .withUpdateDate(LocalDate.now());
    }

    public static Order.OrderBuilder builderFromPurchaseRequest(PurchaseRequest purchaseRequest) {
        return Order.builderFromOperation(purchaseRequest)
                .withValue(purchaseRequest.getAmount().getValue())
                .withCurrency(purchaseRequest.getAmount().getCurrency());
    }

    public static Order createDeclinedAntifraudOrder(Order.OrderBuilder orderBuilder) {
        orderBuilder.withStatus(OperationStatus.ANTIFRAUD_DECLINED.name())
                .withResponseMessage(OperationStatus.ANTIFRAUD_DECLINED.getDescription())
                .withState(OperationState.DECLINED.name()).build();
        return orderBuilder.build();
    }

}
