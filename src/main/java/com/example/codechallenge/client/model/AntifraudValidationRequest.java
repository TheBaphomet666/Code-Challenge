package com.example.codechallenge.client.model;

import java.io.Serializable;

import com.example.codechallenge.provider.model.shared.Amount;
import com.example.codechallenge.provider.model.shared.Card;
import com.example.codechallenge.provider.model.shared.Payer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AntifraudValidationRequest implements Serializable {

    private Payer payer;

    private Card card;

    private Amount amount;
}
