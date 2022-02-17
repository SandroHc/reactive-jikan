/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

/**
 * Exception thrown when using invalid arguments.
 */
public class JikanInvalidArgumentException extends JikanException {

	public JikanInvalidArgumentException(String message) {
		super(message);
	}

	public JikanInvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public JikanInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
