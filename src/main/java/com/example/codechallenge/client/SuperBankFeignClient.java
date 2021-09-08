package com.example.codechallenge.client;

import java.util.List;

import com.example.codechallenge.client.model.SuperBankPaymentRequest;
import com.example.codechallenge.client.model.SuperBankPaymentResponse;
import com.example.codechallenge.client.model.SuperBankRefundRequest;
import com.example.codechallenge.client.model.SuperBankRefundResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="superbank-api", url="${feign.client.config.superbank-api.url}", decode404 = false)
public interface SuperBankFeignClient {


    @PostMapping(value = "/pay")
    List<SuperBankPaymentResponse> pay(@RequestBody SuperBankPaymentRequest paymentRequest);

    @PostMapping(value = "/refund")
    List<SuperBankRefundResponse> refund(@RequestBody SuperBankRefundRequest refundRequest);
}

