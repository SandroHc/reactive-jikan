/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

/**
 * Exception thrown when there is an error executing a query.
 */
public class JikanQueryException extends JikanException {

	public JikanQueryException(String message) {
		super(message);
	}

	public JikanQueryException(String message, Throwable cause) {
		super(message, cause);
	}
}
