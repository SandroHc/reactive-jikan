package net.sandrohc.jikan.test;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.base.MalEntity;
import net.sandrohc.jikan.model.base.MalSubEntity;
import net.sandrohc.jikan.model.enums.Type;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static net.sandrohc.jikan.test.MockUtils.mockError;
import static org.junit.jupiter.api.Assertions.*;

public class CommonTest extends RequestTest {

	@Test
	void testBuilder() {
		Jikan jikan = new Jikan.JikanBuilder()
				.debug(true)
				.baseUrl("https://example.com")
				.userAgent("user-agent")
				.build();

		assertTrue(jikan.debug);
		assertEquals("https://example.com", jikan.baseUrl);
		assertEquals("user-agent", jikan.userAgent);
	}

	@Test
	void testBuilderDefaults() {
		Jikan jikan = new Jikan();

		assertFalse(jikan.debug);
		assertEquals("https://api.jikan.moe/v3", jikan.baseUrl);
		assertEquals("reactive-jikan/development", jikan.userAgent);
	}

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

	@Test
	void testEquality() {
		Entity e1 = new Entity(1);
		Entity e2 = new Entity(2);
		Entity e3 = new Entity(1);

		assertNotEquals(e1.toString(), e2.toString());
		assertNotEquals(e1.hashCode(), e2.hashCode());

		assertEquals(e1, e1);
		assertNotEquals(e1, new Object());

		assertNotEquals(e1, e2);
		assertNotEquals(e2, e1);

		assertEquals(e1, e3);
		assertEquals(e3, e1);

		MalSubEntity subEntity = new MalSubEntity();
		subEntity.type = Type.ANIME;
		subEntity.malId = 1;
		subEntity.name = "NAME";
		assertNotNull(subEntity.toString());
	}

	private static class Entity extends MalEntity {

		public Entity(int malId) {
			this.malId = malId;
		}

	}

}
