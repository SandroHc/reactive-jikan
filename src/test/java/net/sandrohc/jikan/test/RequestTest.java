package net.sandrohc.jikan.test;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.Jikan.JikanBuilder;
import net.sandrohc.jikan.model.anime.Anime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

	protected static Jikan jikan;
	protected static ClientAndServer mockServer;

	@BeforeAll
	public static void setup() {
		mockServer = MockUtils.createMockServer();
		jikan = new JikanBuilder().baseUrl(MockUtils.MOCK_HOST).build();
	}

	@AfterAll
	public static void stopServer() {
		mockServer.stop();
	}

}
