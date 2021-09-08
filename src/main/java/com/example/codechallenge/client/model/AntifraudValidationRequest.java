package com.example.codechallenge.client.model;

import com.example.codechallenge.provider.model.shared.Amount;
import com.example.codechallenge.provider.model.shared.Card;
import com.example.codechallenge.provider.model.shared.Payer;
import lombok.Builder;

@Builder
public class AntifraudValidationRequest {

    private Payer payer;

    private Card card;

    private Amount amount;
}
