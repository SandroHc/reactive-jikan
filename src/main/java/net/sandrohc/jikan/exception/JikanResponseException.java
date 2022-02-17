/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

/**
 * Exception thrown when there is an error building the response.
 */
public class JikanResponseException extends JikanException {

	public JikanResponseException(String message) {
		super(message);
	}

	public JikanResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public JikanResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
