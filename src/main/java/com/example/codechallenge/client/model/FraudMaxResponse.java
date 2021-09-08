package com.example.codechallenge.client.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class FraudMaxResponse implements Serializable {

    private BigDecimal risk;

    private String analyzer;

    private boolean isCovered;

}
