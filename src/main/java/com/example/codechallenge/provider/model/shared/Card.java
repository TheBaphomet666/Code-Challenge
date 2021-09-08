package com.example.codechallenge.provider.model.shared;

import lombok.Builder;

@Builder
public class Card {

    private String number;

    private String expirationDate;

    private String cvv;

    private String cardHolderName;

}
