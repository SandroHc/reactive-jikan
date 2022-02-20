/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.Jikan.JikanBuilder;
import net.sandrohc.jikan.test.MockUtils;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class QueryTest {

	protected static Jikan jikan;
	protected static ClientAndServer mockServer;

	@BeforeAll
	public static void setup() {
		jikan = new JikanBuilder()
				.debug(true)
				.baseUrl(MockUtils.MOCK_URL)
				.maxRetries(1)
				.userAgent("reactive-jikan/unit-tests")
				.cache(null) // Disable cache for tests
				.build();

		if (mockServer == null)
			mockServer = MockUtils.createMockServer();
	}

	@BeforeEach
	public void stopServer() {
		mockServer.reset();
	}

}
