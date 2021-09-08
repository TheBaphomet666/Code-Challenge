package com.example.codechallenge.controller;

import javax.validation.Valid;

import com.example.codechallenge.controller.model.OperationResponse;
import com.example.codechallenge.controller.model.RefundRequestDTO;
import com.example.codechallenge.provider.RefundProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RESTful Web Service Endpoint for refunds.
 *
 * @author <a href="oscar.pinto@payulatam.com">Oscar Pinto</a>
 * @version 1.0.0
 * @since 	1.0.0
 */
@RestController
@RequestMapping("payments")
public class RefundController {

    /**
     * The purchase Provider
     */
    private final RefundProvider refundProvider;

    @Autowired
    public RefundController(final RefundProvider refundProvider) {

        this.refundProvider = refundProvider;
    }

    @PostMapping(path = "/refund")
    public ResponseEntity<OperationResponse> refundOrder(@Valid @RequestBody final RefundRequestDTO refundRequestDTO) {

        var response = refundProvider.doRefund(refundRequestDTO);

        return new ResponseEntity<>(response, response.getHttpResponseStatus());
    }
}
