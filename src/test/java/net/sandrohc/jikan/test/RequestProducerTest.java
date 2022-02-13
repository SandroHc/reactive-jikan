package net.sandrohc.jikan.test;

import java.time.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.producer.*;
import net.sandrohc.jikan.model.legacy.season.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestProducerTest extends RequestTest {

	@Test
	void fetchProducer() throws JikanInvalidArgumentException {
		// http://api.jikan.moe/v3/producer/43/1
		String response = "{\n" +
				"    \"meta\": {\n" +
				"        \"mal_id\": 43,\n" +
				"        \"type\": \"anime\",\n" +
				"        \"name\": \"ufotable\",\n" +
				"        \"url\": \"https://myanimelist.net/anime/producer/43/ufotable\"\n" +
				"    },\n" +
				"    \"anime\": [\n" +
				"        {\n" +
				"            \"mal_id\": 10087,\n" +
				"            \"url\": \"https://myanimelist.net/anime/10087/Fate_Zero\",\n" +
				"            \"title\": \"Fate/Zero\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/2/73249.jpg\",\n" +
				"            \"synopsis\": \"With the promise of granting any wish, the omnipotent Holy Grail triggered three wars in the past...\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"airing_start\": \"2011-10-01T15:00:00+00:00\",\n" +
				"            \"episodes\": 13,\n" +
				"            \"members\": 938115,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Action\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"source\": \"Light novel\",\n" +
				"            \"producers\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 43,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"ufotable\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/producer/43/ufotable\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": 8.38,\n" +
				"            \"licensors\": [\n" +
				"                \"Aniplex of America\"\n" +
				"            ],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/producer/43/1", response);

		Producer producer = jikan.query().producer(43, 1).execute().block();

		assertNotNull(producer);
		assertNotNull(producer.toString());

		/* Results */
		MalSubEntity meta = producer.meta;
		assertEquals(43, meta.malId);
		assertEquals(Type.ANIME, meta.type);
		assertEquals("ufotable", meta.name);
		assertEquals("https://myanimelist.net/anime/producer/43/ufotable", meta.url);

		SeasonAnime anime = producer.anime.iterator().next();
		assertNotNull(anime.toString());
		assertEquals(10087, anime.malId);
		assertEquals("Fate/Zero", anime.title);
		assertEquals("https://myanimelist.net/anime/10087/Fate_Zero", anime.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/2/73249.jpg", anime.imageUrl);
		assertEquals("With the promise of granting any wish, the omnipotent Holy Grail triggered three wars in the past...", anime.synopsis);
		assertEquals(AnimeType.TV, anime.type);
		assertEquals(OffsetDateTime.parse("2011-10-01T15:00:00+00:00"), anime.airingStart);
		assertEquals(13, anime.episodes);
		assertEquals(938115, anime.members);
		assertEquals("Light novel", anime.source);
		assertEquals(8.38F, anime.score);
		assertFalse(anime.r18);
		assertFalse(anime.kids);
		assertTrue(anime.licensors.contains("Aniplex of America"));

		GenreEntity<AnimeGenre> genre = anime.genres.iterator().next();
		assertEquals(1, genre.malId);
		assertEquals(Type.ANIME, genre.type);
		assertEquals(AnimeGenre.ACTION, genre.name);
		assertEquals("https://myanimelist.net/anime/genre/1/Action", genre.url);

		MalSubEntity animeProducer = anime.producers.iterator().next();
		assertEquals(43, animeProducer.malId);
		assertEquals(Type.ANIME, animeProducer.type);
		assertEquals("ufotable", animeProducer.name);
		assertEquals("https://myanimelist.net/anime/producer/43/ufotable", animeProducer.url);
	}

	@Test
	void fetchProducer_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().producer(10, 0), "page starts at index 1");
	}

}
