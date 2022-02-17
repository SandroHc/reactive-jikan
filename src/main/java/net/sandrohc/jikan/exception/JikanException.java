/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

/**
 * A generic exception.
 */
public class JikanException extends Exception {

	public JikanException() {
	}

	public JikanException(String message) {
		super(message);
	}

	public JikanException(String message, Throwable cause) {
		super(message, cause);
	}

	public JikanException(Throwable cause) {
		super(cause);
	}

	public JikanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
