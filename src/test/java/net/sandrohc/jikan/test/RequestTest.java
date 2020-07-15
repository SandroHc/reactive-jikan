package net.sandrohc.jikan.test;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.model.anime.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static net.sandrohc.jikan.test.MockUtils.mockError;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest extends BaseTest {

	@Test
	void testBadRequest() {
		mockError(mockServer, HttpResponseStatus.BAD_REQUEST);
		assertThrows(Exception.class, () -> jikan.query().anime().get(1).execute().block());
	}

	@Test
	void testNotFound() {
		mockError(mockServer, HttpResponseStatus.NOT_FOUND);

		Anime anime = jikan.query().anime().get(1).execute().block();
		assertNull(anime);
	}

	@Test
	void testInvalidJson() {
		mock(mockServer, "/anime/1", "{ invalid }");
		assertThrows(Exception.class, () -> jikan.query().anime().get(1).execute().block());
	}

}
