package com.example.codechallenge.util;

import static org.junit.jupiter.api.Assertions.*;

import com.example.codechallenge.provider.model.shared.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LitePaymentCardEncryptionTest {

    private LitePaymentCardEncryption litePaymentCardEncryption = new LitePaymentCardEncryption();

    @Test
    void test(){

        var card = Card.builder().cardHolderName("tuti").cvv("123").number("1234").expirationDate("2020/12").build();
        var encrypted = litePaymentCardEncryption.encryptCard(card);
        var card2 = litePaymentCardEncryption.decryptCard(encrypted);
        assertEquals(card,card2);
    }
}