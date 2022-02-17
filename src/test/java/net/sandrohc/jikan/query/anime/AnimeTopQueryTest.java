/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.test.RequestTest;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimeTopQueryTest extends RequestTest {

	@Test
	void fetchTop() {
		// https://api.jikan.moe/v3/top/anime/1/airing
		String response = "{\n" +
				"    \"top\": [\n" +
				"        {\n" +
				"            \"mal_id\": 39587,\n" +
				"            \"rank\": 3,\n" +
				"            \"title\": \"Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season\",\n" +
				"            \"url\": \"https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/1444/108005.jpg?s=b998e66dcfad4bbd4510b9ece4c9eb99\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"episodes\": 13,\n" +
				"            \"start_date\": \"Jul 2020\",\n" +
				"            \"end_date\": null,\n" +
				"            \"members\": 290261,\n" +
				"            \"score\": 8.5\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		String responseEmpty = "{\n" +
				"    \"top\": [ ]\n" +
				"}";

		// without subtype
		mock(mockServer, "/top/anime/1", responseEmpty);
		Collection<AnimeTopSub> resultsNoSubtype = jikan.query().anime().top(1).execute().collectList().block();
		assertThat(resultsNoSubtype).isNotNull();
		assertThat(resultsNoSubtype.isEmpty()).isTrue();


		// with subtype
		mock(mockServer, "/top/anime/1/airing", response);
		Collection<AnimeTopSub> results = jikan.query().anime().top(1).subtype(AnimeSubType.AIRING)
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
		assertThat(new AnimeTop().toString()).isNotNull();

		/* Results */
		AnimeTopSub result = results.iterator().next();
		assertThat(result.toString()).isNotNull();
		assertThat(result.malId).isEqualTo(39587);
		assertThat(result.rank).isEqualTo(3);
		assertThat(result.title).isEqualTo("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season");
		assertThat(result.url).isEqualTo("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season");
		assertThat(result.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1444/108005.jpg?s=b998e66dcfad4bbd4510b9ece4c9eb99");
		assertThat(result.type).isEqualTo(AnimeType.TV);
		assertThat(result.episodes).isEqualTo(13);
		assertThat(result.startDate).isEqualTo("Jul 2020");
		assertNull(result.endDate);
		assertThat(result.members).isEqualTo(290261);
		assertThat(result.score).isEqualTo(8.5F);
	}

	@Test
	void fetchTop_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().top(0), "page starts at index 1");
	}
}
