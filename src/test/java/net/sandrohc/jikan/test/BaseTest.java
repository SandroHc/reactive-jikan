package net.sandrohc.jikan.test;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.Jikan.JikanBuilder;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class BaseTest {

	protected static Jikan jikan;
	protected static ClientAndServer mockServer;

	@BeforeAll
	public static void setup() {
		mockServer = MockUtils.createMockServer();
		jikan = new JikanBuilder().debug(true).baseUrl(MockUtils.MOCK_URL).userAgent("reactive-jikan/unit-tests").build();
	}

	@AfterAll
	public static void stopServer() {
		mockServer.stop();
	}

}
