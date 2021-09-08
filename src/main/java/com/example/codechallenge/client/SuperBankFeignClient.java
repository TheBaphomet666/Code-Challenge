package com.example.codechallenge.client;

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
    SuperBankPaymentResponse pay(@RequestBody SuperBankPaymentRequest paymentRequest);

    @PostMapping(value = "/refund")
    SuperBankRefundResponse refund(@RequestBody SuperBankRefundRequest refundRequest);
}

