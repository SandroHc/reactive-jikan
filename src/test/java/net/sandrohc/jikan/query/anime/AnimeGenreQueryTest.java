/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.test.RequestTest;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class AnimeGenreQueryTest extends RequestTest {

	@Test
	void fetchGenre() {
		// https://api.jikan.moe/v3/genre/anime/1/1
		String response = "{\n" +
				"    \"mal_url\": {\n" +
				"        \"mal_id\": 1,\n" +
				"        \"type\": \"anime\",\n" +
				"        \"name\": \"Action Anime\",\n" +
				"        \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"    },\n" +
				"    \"item_count\": 3731,\n" +
				"    \"anime\": [\n" +
				"        {\n" +
				"            \"mal_id\": 16498,\n" +
				"            \"url\": \"https://myanimelist.net/anime/16498/Shingeki_no_Kyojin\",\n" +
				"            \"title\": \"Shingeki no Kyojin\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/10/47347.jpg\",\n" +
				"            \"synopsis\": \"Centuries ago, mankind was slaughtered to near extinction...\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"airing_start\": \"2013-04-06T16:58:00+00:00\",\n" +
				"            \"episodes\": 25,\n" +
				"            \"members\": 2005593,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Action\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 38,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Military\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/38/Military\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"source\": \"Manga\",\n" +
				"            \"producers\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 858,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Wit Studio\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/producer/858/Wit_Studio\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": 8.44,\n" +
				"            \"licensors\": [\n" +
				"                \"Funimation\"\n" +
				"            ],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/genre/anime/1/1", response);

		Collection<AnimeGenreSub> results = jikan.query().anime().genre(AnimeGenre.ACTION, 1)
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
		assertThat(new AnimeGenreList().toString()).isNotNull();

		/* Results */
		AnimeGenreSub result = results.iterator().next();
		assertThat(result.toString()).isNotNull();
		assertThat(result.malId).isEqualTo(16498);
		assertThat(result.title).isEqualTo("Shingeki no Kyojin");
		assertThat(result.url).isEqualTo("https://myanimelist.net/anime/16498/Shingeki_no_Kyojin");
		assertThat(result.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/10/47347.jpg");
		assertThat(result.synopsis).isEqualTo("Centuries ago, mankind was slaughtered to near extinction...");
		assertThat(result.type).isEqualTo(AnimeType.TV);
		assertThat(result.aired.from).isEqualTo(LocalDate.parse("2013-04-06"));
		assertThat(result.episodes).isEqualTo(25);
		assertThat(result.members).isEqualTo(2005593);
		assertThat(result.source).isEqualTo("Manga");
		assertThat(result.score).isEqualTo(8.44F);
		assertThat(result.r18).isFalse();
		assertThat(result.kids).isFalse();
		assertThat(result.licensors.contains("Funimation")).isTrue();


		Iterator<EntityWithType> genresIt = result.genres.iterator();

		EntityWithType genre1 = genresIt.next();
		assertThat(genre1.malId).isEqualTo(1);
		assertThat(genre1.type).isEqualTo(Type.ANIME);
		assertThat(genre1.name).isEqualTo("Action");
		assertThat(genre1.url).isEqualTo("https://myanimelist.net/anime/genre/1/Action");

		EntityWithType genre2 = genresIt.next();
		assertThat(genre2.malId).isEqualTo(38);
		assertThat(genre2.type).isEqualTo(Type.ANIME);
		assertThat(genre2.name).isEqualTo("Military");
		assertThat(genre2.url).isEqualTo("https://myanimelist.net/anime/genre/38/Military");

		assertThat(genresIt.hasNext()).isFalse();


		EntityWithType producer = result.producers.iterator().next();
		assertThat(producer.malId).isEqualTo(858);
		assertThat(producer.type).isEqualTo(Type.ANIME);
		assertThat(producer.name).isEqualTo("Wit Studio");
		assertThat(producer.url).isEqualTo("https://myanimelist.net/anime/producer/858/Wit_Studio");
	}

	@Test
	void fetchGenre_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().genre(AnimeGenre.ACTION, 0), "page starts at index 1");
	}
}
