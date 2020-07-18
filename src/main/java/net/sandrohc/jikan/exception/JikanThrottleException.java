/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.exception;

public class JikanThrottleException extends JikanException {

	public JikanThrottleException() {
	}

	public JikanThrottleException(String message) {
		super(message);
	}

	public JikanThrottleException(String message, Throwable cause) {
		super(message, cause);
	}

	public JikanThrottleException(Throwable cause) {
		super(cause);
	}

	public JikanThrottleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
