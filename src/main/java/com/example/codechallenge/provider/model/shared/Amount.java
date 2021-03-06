package com.example.codechallenge.provider.model.shared;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class Amount implements Serializable {

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal value;

    @NotBlank(message = "Currency can not be empty")
    private String currency;
}
