package com.example.codechallenge.client.model;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class FraudMaxResponse {

    private BigDecimal risk;

    private String analyzer;

    private boolean isCovered;

}
