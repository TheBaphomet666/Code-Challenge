package com.example.codechallenge.client;

import com.example.codechallenge.client.model.AntifraudValidationRequest;
import com.example.codechallenge.client.model.FraudMaxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name="fraudmax-api", url="${feign.client.config.fraudmax-api.url}", decode404 = false)
public interface FraudMaxFeignClient {


    @PostMapping(value = "/validate")
    FraudMaxResponse validateFraud(@RequestBody AntifraudValidationRequest antifraudValidationRequest);
}

