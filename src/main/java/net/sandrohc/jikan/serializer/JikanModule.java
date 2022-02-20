/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.serializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JikanModule extends SimpleModule {

	public JikanModule() {
		addDeserializer(String.class, new StringDeserializer());
	}
}
