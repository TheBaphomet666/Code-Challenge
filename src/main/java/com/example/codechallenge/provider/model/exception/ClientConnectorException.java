package com.example.codechallenge.provider.model.exception;

public class ClientConnectorException extends RuntimeException {

	public ClientConnectorException(final String message, final Throwable throwable) {

		super(message, throwable);

	}
}
