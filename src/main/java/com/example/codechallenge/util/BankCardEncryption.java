package com.example.codechallenge.util;

import com.example.codechallenge.provider.model.shared.Card;

public interface BankCardEncryption {

    Card decryptCard(String encryptedCard);

    String encryptCard(final Card card);
}
