package net.sandrohc.jikan.test;

import java.time.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.*;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static net.sandrohc.jikan.test.MockUtils.mockError;
import static org.assertj.core.api.Assertions.assertThat;
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

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(jikan.debug).isTrue();
		softly.assertThat(jikan.baseUrl).isEqualTo("https://example.com");
		softly.assertThat(jikan.userAgent).isEqualTo("user-agent");
		softly.assertThat(jikan.maxRetries).isEqualTo(10);
		softly.assertAll();
	}

	@Test
	void testBuilderDefaults() {
		Jikan jikan = new Jikan();

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(jikan.debug).isFalse();
		softly.assertThat(jikan.baseUrl).isEqualTo("https://api.jikan.moe/v3");
		softly.assertThat(jikan.userAgent).isEqualTo("reactive-jikan/development");
		softly.assertAll();
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
	void testNotFound() throws JikanQueryException {
		mockError(mockServer, HttpResponseStatus.NOT_FOUND);

		Anime anime = jikan.query().anime().get(1).execute().block();
		assertThat(anime).isNull();
	}

	@Test
	void testInvalidJson() {
		mock(mockServer, "/anime/1", "{ invalid }");
		assertThrows(Exception.class, () -> jikan.query().anime().get(1).execute().block());
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testEquality_malEntity() {
		Entity e1 = new Entity(1);
		Entity e2 = new Entity(2);
		Entity e3 = new Entity(1);

		assertThat(e1.toString()).isNotNull();

		assertThat(e1.toString()).isNotEqualTo(e2.toString());
		assertThat(e1.hashCode()).isNotEqualTo(e2.hashCode());

		assertThat(e1).isEqualTo(e1);
		assertThat(e1).isNotEqualTo(null);
		assertThat(e1).isNotEqualTo(new Anime());

		assertThat(e1).isNotEqualTo(e2);
		assertThat(e2).isNotEqualTo(e1);

		assertThat(e3).isEqualTo(e1);
		assertThat(e1).isEqualTo(e3);
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testEquality_dateRange() {
		DateRange e1 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));
		DateRange e2 = new DateRangeTest(OffsetDateTime.parse("2020-01-02T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));
		DateRange e3 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-01T00:00:00+00:00"));
		DateRange e4 = new DateRangeTest(OffsetDateTime.parse("2020-01-01T00:00:00+00:00"), OffsetDateTime.parse("2020-01-02T00:00:00+00:00"));

		assertThat(e1.toString()).isNotNull();

		assertThat(e1.toString()).isNotEqualTo(e2.toString());
		assertThat(e1.hashCode()).isNotEqualTo(e2.hashCode());

		assertThat(e1).isEqualTo(e1);
		assertThat(e1).isNotEqualTo(null);
		assertThat(e1).isNotEqualTo(new Anime());

		assertThat(e1).isNotEqualTo(e2);
		assertThat(e2).isNotEqualTo(e1);
		assertThat(e1).isNotEqualTo(e3);
		assertThat(e3).isNotEqualTo(e1);

		assertThat(e1).isEqualTo(e4);
		assertThat(e4).isEqualTo(e1);

		DateRange rangeNull = new DateRangeTest(null, null);
		assertThat(rangeNull.from).isNull();
		assertThat(rangeNull.to).isNull();

		assertThat(e1.from).isNotNull();
		assertThat(e1.to).isNotNull();
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
		je = new JikanException(new Throwable());
		je = new JikanException("Message", new Throwable(), true, true);

		jiae = new JikanInvalidArgumentException("Message");
		jiae = new JikanInvalidArgumentException("Message", new Throwable());
		jiae = new JikanInvalidArgumentException("Message", new Throwable(), true, true);

		jre = new JikanResponseException("Message");
		jre = new JikanResponseException("Message", new Throwable());
		jre = new JikanResponseException("Message", new Throwable(), true, true);

		jte = new JikanThrottleException("Message");
		jte = new JikanThrottleException("Message", new Throwable());
		jte = new JikanThrottleException("Message", new Throwable(), true, true);

		assertThat(je).isNotNull();
		assertThat(jiae).isNotNull();
		assertThat(jre).isNotNull();
		assertThat(jte).isNotNull();

		assertThat(JikanException.class.isAssignableFrom(JikanInvalidArgumentException.class)).isTrue();
		assertThat(JikanException.class.isAssignableFrom(JikanResponseException.class)).isTrue();
		assertThat(JikanException.class.isAssignableFrom(JikanThrottleException.class)).isTrue();
	}

	@Test
	public void testDumpStacktrace() {
		Jikan jikan;
		QueryTest query = new QueryTest(null);
		Exception ex;

		// debug mode = false
		jikan = new Jikan.JikanBuilder().debug(false).build();
		ex = jikan.dumpStacktrace(query, new byte[0], new RuntimeException("TEST 1"));
		assertThat(ex.getClass()).isEqualTo(JikanResponseException.class);
		assertThat(ex.getMessage().startsWith("Error parsing JSON for query:")).isTrue();
		assertFalse(ex.getMessage().contains("A report file was generated at"));
		assertThat(ex.getCause().getClass()).isEqualTo(RuntimeException.class);
		assertThat(ex.getCause().getMessage()).isEqualTo("TEST 1");

		// debug mode = true
		jikan = new Jikan.JikanBuilder().debug(true).build();
		ex = jikan.dumpStacktrace(query, new byte[0], new RuntimeException("TEST 2"));
		assertThat(ex.getClass()).isEqualTo(JikanResponseException.class);
		assertThat(ex.getMessage().startsWith("Error parsing JSON for query")).isTrue();
		assertThat(ex.getMessage().contains("A report file was generated at")).isTrue();
		assertThat(ex.getCause().getClass()).isEqualTo(RuntimeException.class);
		assertThat(ex.getCause().getMessage()).isEqualTo("TEST 2");
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
		public QueryUrlBuilder getUrl() {
			return QueryUrlBuilder.create();
		}

		@Override
		public TypeReference<Void> getResponseType() {
			return new TypeReference<Void>() { };
		}
	}
}
