package com.example.codechallenge.controller;

import javax.validation.Valid;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.provider.PurchaseProvider;
import com.example.codechallenge.controller.model.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RESTful Web Service Endpoint for payments.
 *
 * @author <a href="oscar.pinto@payulatam.com">Oscar Pinto</a>
 * @version 1.0.0
 * @since 	1.0.0
 */
@RestController
@RequestMapping("payments")
public class PaymentController {

    /**
     * The purchase Provider
     */
    private final PurchaseProvider purchaseProvider;

    @Autowired
    public PaymentController(final PurchaseProvider purchaseProvider) {

        this.purchaseProvider = purchaseProvider;
    }

    @PostMapping(path = "/purchase")
    public ResponseEntity<OperationResponse> purchaseOrder(@Valid @RequestBody final PurchaseRequest purchaseRequest) {

        var response = purchaseProvider.doPurchase(purchaseRequest);

        return new ResponseEntity<>(response, response.getHttpResponseStatus());
    }
}
