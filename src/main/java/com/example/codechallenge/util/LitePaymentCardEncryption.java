package com.example.codechallenge.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.example.codechallenge.provider.model.shared.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class LitePaymentCardEncryption implements CardEncryption { //TODO THIS WAS DONE ONLY FOR GO THROUGH NOT REAL EXPECTED IMPL

    static String algorithm = "DESede";

    private final Key symKey;

    private final Cipher c;

    private final Gson gson = new GsonBuilder().create();

    public LitePaymentCardEncryption() {

        try {
            symKey = KeyGenerator.getInstance(algorithm).generateKey();
            c = Cipher.getInstance(algorithm);
        }catch (Exception e){
            throw new EncryptionException("Creating Encryption Bean", e);
        }
    }

    @Override
    public Card decryptCard(String encryptedCard) {

        String cardAsString = decryptF(encryptedCard);
        return gson.fromJson(cardAsString, Card.class);
    }

    @Override
    public String encryptCard(Card card) {

        String cardAsString = gson.toJson(card);

        return Base64Utils.encodeToString(encryptF(cardAsString));
    }

    private byte[] encryptF(String input) {

        try{
            c.init(Cipher.ENCRYPT_MODE, symKey);
            byte[] inputBytes = input.getBytes();
            return c.doFinal(inputBytes);
        }catch (Exception e) {
            throw new EncryptionException("Error During Encryption", e);
        }
    }

    private String decryptF(String input) {

        var encryptionBytes = Base64Utils.decodeFromString(input);

        try {
            c.init(Cipher.DECRYPT_MODE, symKey);

            byte[] decrypt = c.doFinal(encryptionBytes);

            String decrypted = new String(decrypt);

            return decrypted;
        }catch (Exception e) {
            throw new EncryptionException("Error During Encryption", e);
        }
    }
}
