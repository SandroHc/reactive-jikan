/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

/**
 * Exception thrown when there is an error building the URL.
 */
public class JikanUrlException extends JikanException {

	public JikanUrlException(String path, String query) {
		super("Error building URL with path '" + path + "' and query '" + query + "'");
	}

	public JikanUrlException(String path, String query, Throwable cause) {
		super("Error building URL with path '" + path + "' and query '" + query + "'", cause);
	}
}
