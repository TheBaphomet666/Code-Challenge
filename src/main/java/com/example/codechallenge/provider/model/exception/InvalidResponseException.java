package com.example.codechallenge.provider.model.exception;

/**
 * Exception that indicates problems with the response that Transbank returned
 *
 * @author Juliana Diaz - juliana.diaz@payulatam.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvalidResponseException extends RuntimeException {

	/**
	 * Constructor of InvalidResponseException
	 *
	 * @param message to show with the exception
	 */
	public InvalidResponseException(final String message) {

		super(message);
	}

}
