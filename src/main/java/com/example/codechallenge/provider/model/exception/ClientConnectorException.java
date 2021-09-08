package com.example.codechallenge.provider.model.exception;

public class ClientConnectorException extends RuntimeException {

	private final ErrorReason reason;

	public ClientConnectorException(final String message) {

		super(message);
		this.reason = ErrorReason.UNEXPECTED;
	}

	public ClientConnectorException(final String message, final ErrorReason reason) {

		super(message);
		this.reason = reason;
	}

	public ClientConnectorException(final String message, final Throwable throwable, final ErrorReason reason) {

		super(message, throwable);
		this.reason = reason;
	}

	public ErrorReason getReason() {

		return this.reason;
	}

	public enum ErrorReason {

		CIRCUIT_BREAKER_OPEN,

		BULKHEAD_FULL,

		READ_TIMEOUT,

		CONNECTION_TIMEOUT,

		UNEXPECTED,

		ILLEGAL_ARGUMENTS;

	}
}
