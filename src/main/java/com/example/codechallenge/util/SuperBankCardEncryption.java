package com.example.codechallenge.util;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.example.codechallenge.provider.model.shared.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class SuperBankCardEncryption implements BankCardEncryption { //TODO THIS WAS DONE ONLY FOR GO THROUGH NOT REAL EXPECTED IMPL

    private static final String algorithm = "AES";

    private static final IvParameterSpec IV = new IvParameterSpec(DatatypeConverter.parseHexBinary("00000000000000000000000000000000"));

    private final SecretKey symKey;

    private final Cipher c;

    private final Gson gson = new GsonBuilder().create();

    public SuperBankCardEncryption() {

        try {
            symKey = new SecretKeySpec("12312312312312312312312312312312".getBytes(), "AES");
            c = Cipher.getInstance("AES/CBC/NoPadding");
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
            c.init(Cipher.ENCRYPT_MODE, symKey, IV);
            byte[] inputBytes = input.getBytes();
            return c.doFinal(inputBytes);
        }catch (Exception e) {
            throw new EncryptionException("Error During Encryption", e);
        }
    }

    private String decryptF(String input) {

        var encryptionBytes = Base64Utils.decodeFromString(input);

        try {
            c.init(Cipher.DECRYPT_MODE, symKey, IV);

            byte[] decrypt = c.doFinal(encryptionBytes);

            String decrypted = new String(decrypt);

            return decrypted;
        }catch (Exception e) {
            throw new EncryptionException("Error During Encryption", e);
        }
    }
}
