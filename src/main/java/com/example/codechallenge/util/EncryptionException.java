package com.example.codechallenge.util;

/**
 * Exception to handle any Encryption failure
 *
 * @author Oscar Pinto - oscar.pinto@payulatam.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class EncryptionException extends RuntimeException {

    /**
     * Constructor of EncryptionException that needs the message and throwable
     *
     * @param message to show with the exception
     * @param throwable indicates that the cause is nonexistent
     */
    public EncryptionException(final String message, final Throwable throwable) {

        super(message, throwable);
    }
}
