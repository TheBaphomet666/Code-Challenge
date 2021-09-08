package com.example.codechallenge.controller.model;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;

@Getter
public class AmountDTO {

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    @NotBlank(message = "Currency can not be empty")
    private String currency;
}
