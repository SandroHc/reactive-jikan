package net.sandrohc.jikan.test;

import java.time.*;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanException;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.exception.JikanThrottleException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.query.Query;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;

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
				.maxRetries(10)
				.build();

		assertTrue(jikan.debug);
		assertEquals("https://example.com", jikan.baseUrl);
		assertEquals("user-agent", jikan.userAgent);
		assertEquals(10, jikan.maxRetries);
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
	void testTooManyRequests() {
		mockError(mockServer, HttpResponseStatus.TOO_MANY_REQUESTS);
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
	void testEquality_malEntity() {
		Entity e1 = new Entity(1);
		Entity e2 = new Entity(2);
		Entity e3 = new Entity(1);

		assertNotNull(e1.toString());

		assertNotEquals(e1.toString(), e2.toString());
		assertNotEquals(e1.hashCode(), e2.hashCode());

		assertEquals(e1, e1);
		assertNotEquals(e1, null);
		assertNotEquals(e1, new Anime());

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

	@Test
	void testEquality_dateRange() {
		DateRange e1 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));
		DateRange e2 = new DateRangeTest(OffsetDateTime.parse("2020-01-02T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));
		DateRange e3 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-01T00:00:00+00:00"));
		DateRange e4 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));

		assertNotNull(e1.toString());

		assertNotEquals(e1.toString(), e2.toString());
		assertNotEquals(e1.hashCode(), e2.hashCode());

		assertEquals(e1, e1);
		assertNotEquals(e1, null);
		assertNotEquals(e1, new Anime());

		assertNotEquals(e1, e2);
		assertNotEquals(e2, e1);
		assertNotEquals(e1, e3);
		assertNotEquals(e3, e1);

		assertEquals(e1, e4);
		assertEquals(e4, e1);

		DateRange rangeNull = new DateRangeTest(null, null);
		assertNull(rangeNull.from);
		assertNull(rangeNull.to);

		assertNotNull(e1.from);
		assertNotNull(e1.to);
	}

	@SuppressWarnings("UnusedAssignment")
	@Test
	void testExceptions() {
		JikanException je;
		JikanInvalidArgumentException jiae;
		JikanResponseException jre;
		JikanThrottleException jte;

		je = new JikanException();
		je = new JikanException("Message");
		je = new JikanException("Message", new Throwable());
		je = new JikanException("Message", new Throwable(), true, true);
		je = new JikanException(new Throwable());

		jiae = new JikanInvalidArgumentException();
		jiae = new JikanInvalidArgumentException("Message");
		jiae = new JikanInvalidArgumentException("Message", new Throwable());
		jiae = new JikanInvalidArgumentException("Message", new Throwable(), true, true);
		jiae = new JikanInvalidArgumentException(new Throwable());

		jre = new JikanResponseException();
		jre = new JikanResponseException("Message");
		jre = new JikanResponseException("Message", new Throwable());
		jre = new JikanResponseException("Message", new Throwable(), true, true);
		jre = new JikanResponseException(new Throwable());

		jte = new JikanThrottleException();
		jte = new JikanThrottleException("Message");
		jte = new JikanThrottleException("Message", new Throwable());
		jte = new JikanThrottleException("Message", new Throwable(), true, true);
		jte = new JikanThrottleException(new Throwable());

		assertNotNull(je);
		assertNotNull(jiae);
		assertNotNull(jre);
		assertNotNull(jte);

		assertTrue(JikanException.class.isAssignableFrom(JikanInvalidArgumentException.class));
		assertTrue(JikanException.class.isAssignableFrom(JikanResponseException.class));
		assertTrue(JikanException.class.isAssignableFrom(JikanThrottleException.class));
	}

	@Test
	public void testDumpStacktrace() {
		Jikan jikan;
		QueryTest query = new QueryTest(null);
		Exception ex;

		// debug mode = false
		jikan = new Jikan.JikanBuilder().debug(false).build();
		ex = jikan.dumpStacktrace(query, new byte[0], new RuntimeException("TEST 1"));
		assertEquals(JikanResponseException.class, ex.getClass());
		assertTrue(ex.getMessage().startsWith("Error parsing JSON for query:"));
		assertFalse(ex.getMessage().contains("A report file was generated at"));
		assertEquals(RuntimeException.class, ex.getCause().getClass());
		assertEquals("TEST 1", ex.getCause().getMessage());

		// debug mode = true
		jikan = new Jikan.JikanBuilder().debug(true).build();
		ex = jikan.dumpStacktrace(query, new byte[0], new RuntimeException("TEST 2"));
		assertEquals(JikanResponseException.class, ex.getClass());
		assertTrue(ex.getMessage().startsWith("Error parsing JSON for query"));
		assertTrue(ex.getMessage().contains("A report file was generated at"));
		assertEquals(RuntimeException.class, ex.getCause().getClass());
		assertEquals("TEST 2", ex.getCause().getMessage());
	}


	private static class Entity extends MalEntity {

		public Entity(int malId) {
			this.malId = malId;
		}

	}

	private static class DateRangeTest extends DateRange {

		public DateRangeTest(OffsetDateTime from, OffsetDateTime to) {
			setFrom(from);
			setTo(to);
		}

	}

	private static class QueryTest extends Query<Void, Mono<Void>> {

		public QueryTest(Jikan jikan) {
			super(jikan);
		}

		@Override
		public String getUri() {
			return "";
		}

		@Override
		public Class<Void> getRequestClass() {
			return Void.class;
		}

	}

}
