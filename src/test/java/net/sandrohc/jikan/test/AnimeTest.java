package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static net.sandrohc.jikan.test.MockUtils.mockError;
import static org.junit.jupiter.api.Assertions.*;

public class AnimeTest extends BaseTest {

	@Test
	void fetchById() {
		String response = "{" +
						  "  \"mal_id\": 1," +
						  "  \"url\": \"https://example.com/url\"," +
						  "  \"image_url\": \"https://example.com/image_url\"," +
						  "  \"trailer_url\": \"https://example.com/trailer_url\"," +
						  "  \"title\": \"TITLE\"," +
						  "  \"title_english\": \"TITLE ENGLISH\"," +
						  "  \"title_japanese\": \"TITLE 日本語\"," +
						  "  \"title_synonyms\": [ \"TITLE_SYNONYM\" ]," +
						  "  \"type\": \"TV\"," +
						  "  \"source\": \"SOURCE\"," +
						  "  \"episodes\": 10," +
						  "  \"status\": \"Finished Airing\"," +
						  "  \"airing\": false," +
						  "  \"aired\": {" +
						  "    \"from\": \"2010-01-01T00:00:00+00:00\"," +
						  "    \"to\": \"2010-12-01T00:00:00+00:00\"" +
						  "  }," +
						  "  \"duration\": \"DURATION\"," +
						  "  \"rating\": \"PG-13 - Teens 13 or older\"," +
						  "  \"score\": 10.00," +
						  "  \"scored_by\": 20," +
						  "  \"rank\": 30," +
						  "  \"popularity\": 40," +
						  "  \"members\": 50," +
						  "  \"favorites\": 60," +
						  "  \"synopsis\": \"SYNOPSIS\"," +
						  "  \"background\": \"BACKGROUND\"," +
						  "  \"premiered\": \"Summer 2012\"," +
						  "  \"broadcast\": \"Sundays at 00:00 (JST)\"," +
						  "  \"related\": {" +
						  "    \"Other\": [" +
						  "      { \"mal_id\": 10000, \"type\": \"anime\", \"name\": \"RELATED_OTHER\", \"url\": \"https://example.com/related_other\" }" +
						  "    ]," +
						  "    \"Sequel\": [" +
						  "      { \"mal_id\": 11000, \"type\": \"anime\", \"name\": \"RELATED_SEQUEL\", \"url\": \"https://example.com/related_sequel\" }" +
						  "    ]" +
						  "  }," +
						  "  \"producers\": [" +
						  "    { \"mal_id\": 1000, \"type\": \"anime\", \"name\": \"PRODUCER\", \"url\": \"https://example.com/producer\" }" +
						  "  ]," +
						  "  \"licensors\": [" +
						  "    { \"mal_id\": 2000, \"type\": \"anime\", \"name\": \"LICENSOR\", \"url\": \"https://example.com/licensor\" }" +
						  "  ]," +
						  "  \"studios\": [" +
						  "    { \"mal_id\": 3000, \"type\": \"anime\", \"name\": \"STUDIO\", \"url\": \"https://example.com/studio\" }" +
						  "  ]," +
						  "  \"genres\": [" +
						  "    { \"mal_id\": 1, \"type\": \"anime\", \"name\": \"Action\", \"url\": \"https://myanimelist.net/anime/genre/1/Action\" }," +
						  "    { \"mal_id\": 2, \"type\": \"anime\", \"name\": \"Adventure\", \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\" }" +
						  "  ]," +
						  "  \"opening_themes\": [ \"OPENING THEME\" ]," +
						  "  \"ending_themes\":  [ \"ENDING THEME\" ]" +
						  "}";

		mock(mockServer, "/anime/1", response);

		Anime anime = jikan.query().anime().get(1).execute().block();

		assertNotNull(anime);
		assertEquals(1, anime.malId);
		assertEquals("https://example.com/url", anime.url);
		assertEquals("https://example.com/image_url", anime.imageUrl);
		assertEquals("https://example.com/trailer_url", anime.trailerUrl);
		assertEquals("TITLE", anime.title);
		assertEquals("TITLE ENGLISH", anime.titleEnglish);
		assertEquals("TITLE 日本語", anime.titleJapanese);
		assertEquals("TITLE_SYNONYM", anime.titleSynonyms.iterator().next());
		assertEquals(AnimeType.TV, anime.type);
		assertEquals("SOURCE", anime.source);
		assertEquals(10, anime.episodes);
		assertEquals(AnimeStatus.COMPLETED, anime.status);
		assertEquals(OffsetDateTime.parse("2010-01-01T00:00:00+00:00"), anime.aired.from);
		assertEquals(OffsetDateTime.parse("2010-12-01T00:00:00+00:00"), anime.aired.to);
		assertEquals("DURATION", anime.duration);
		assertEquals(AgeRating.PG13, anime.rating);
		assertEquals(10.0F, anime.score);
		assertEquals(20, anime.scoredBy);
		assertEquals(30, anime.rank);
		assertEquals(40, anime.popularity);
		assertEquals(50, anime.members);
		assertEquals(60, anime.favorites);
		assertEquals("SYNOPSIS", anime.synopsis);
		assertEquals("BACKGROUND", anime.background);
		assertEquals("Summer 2012", anime.premiered);
		assertEquals("Sundays at 00:00 (JST)", anime.broadcast);
		assertEquals("OPENING THEME", anime.openingThemes.iterator().next());
		assertEquals("ENDING THEME", anime.endingThemes.iterator().next());

		assertEquals("RELATED_OTHER", anime.related.others.iterator().next().name);
		assertEquals("RELATED_SEQUEL", anime.related.sequels.iterator().next().name);
		assertTrue(anime.related.prequels.isEmpty());
		assertTrue(anime.related.alternativeVersions.isEmpty());
		assertTrue(anime.related.alternativeSettings.isEmpty());
		assertTrue(anime.related.characters.isEmpty());
		assertTrue(anime.related.spinOffs.isEmpty());
		assertTrue(anime.related.adaptations.isEmpty());
		assertTrue(anime.related.summaries.isEmpty());
		assertTrue(anime.related.sideStories.isEmpty());
		assertTrue(anime.related.parentStories.isEmpty());
		assertTrue(anime.related.fullStories.isEmpty());

		Iterator<Genre<AnimeGenre>> genresIt = anime.genres.iterator();
		assertEquals(AnimeGenre.ACTION, genresIt.next().name);
		assertEquals(AnimeGenre.ADVENTURE, genresIt.next().name);

		MalSubEntity producer = anime.producers.iterator().next();
		assertEquals(1000, producer.malId);
		assertEquals(Type.ANIME, producer.type);
		assertEquals("PRODUCER", producer.name);
		assertEquals("https://example.com/producer", producer.url);

		MalSubEntity licensor = anime.licensors.iterator().next();
		assertEquals(2000, licensor.malId);
		assertEquals(Type.ANIME, licensor.type);
		assertEquals("LICENSOR", licensor.name);
		assertEquals("https://example.com/licensor", licensor.url);

		MalSubEntity studio = anime.studios.iterator().next();
		assertEquals(3000, studio.malId);
		assertEquals(Type.ANIME, studio.type);
		assertEquals("STUDIO", studio.name);
		assertEquals("https://example.com/studio", studio.url);
	}

	@Test
	void fetchAnimeById_badRequest() {
		mockError(mockServer, HttpResponseStatus.BAD_REQUEST);
		assertThrows(Exception.class, () -> jikan.query().anime().get(1).execute().block());
	}

}
