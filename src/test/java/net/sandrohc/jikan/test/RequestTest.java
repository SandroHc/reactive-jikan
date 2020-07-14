package net.sandrohc.jikan.test;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.Jikan.JikanBuilder;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class RequestTest {

	protected static Jikan jikan;
	protected static ClientAndServer mockServer;

	@BeforeAll
	public static void setup() {
		mockServer = MockUtils.createMockServer();
		jikan = new JikanBuilder().baseUrl(MockUtils.MOCK_URL).build();
	}

	@AfterAll
	public static void stopServer() {
		mockServer.stop();
	}

}
