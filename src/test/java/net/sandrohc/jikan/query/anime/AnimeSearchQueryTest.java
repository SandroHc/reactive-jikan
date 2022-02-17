/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeSearchQueryTest extends RequestTest {

	@Test
	void fetchAnimeSearch() throws JikanQueryException, JikanUrlException, JikanInvalidArgumentException {
		/* Arrange */
		mock(mockServer, "/anime/11757/reviews", 1, "anime/getAnimeSearch.json",
				Parameter.param("q", "test"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("genre[]", "1,2"),
				Parameter.param("status", "completed"),
				Parameter.param("orderBy", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("rated", "pg"),
				Parameter.param("score", "1.0"),
				Parameter.param("startDate", "2020-01-01"),
				Parameter.param("endDate", "2020-12-31"));

		/* Act */
		AnimeSearchQuery query = jikan.query().anime().search()
				.query("test")
				.page(1)
				.limit(1)
				.genres(AnimeGenre.ACTION, AnimeGenre.ADVENTURE)
				.status(AnimeStatus.COMPLETED)
				.orderBy(AnimeOrderBy.MAL_ID, SortOrder.ASCENDING)
				.rating(AgeRating.PG)
				.score(1.0D);
		Collection<Anime> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/?q=test&page=1&limit=1&genre[]=1,2&status=completed&orderBy=mal_id&sort=asc&rated=pg&score=1.0&startDate=2020-01-01&endDate=2020-12-31");

		// Search Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Anime result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(6347);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/6347/Baka_to_Test_to_Shoukanjuu");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/3/50389.jpg?s=bb898c33476da7c587e8ec82afecee57");
		softly.assertThat(result.title).isEqualTo("Baka to Test to Shoukanjuu");
		softly.assertThat(result.airing).isFalse();
		softly.assertThat(result.synopsis).isEqualTo("Fumizuki Academy isn't a typical Japanese high school...");
		softly.assertThat(result.type).isEqualTo(AnimeType.TV);
		softly.assertThat(result.episodes).isEqualTo(13);
		softly.assertThat(result.score).isEqualTo(7.65F);
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.parse("2010-01-07"));
		softly.assertThat(result.aired.to).isEqualTo(LocalDate.parse("2010-04-01"));
		softly.assertThat(result.members).isEqualTo(484011);
		softly.assertThat(result.rating).isEqualTo(AgeRating.PG13);
		softly.assertAll();
	}

	@Test
	void fetchSearch_excludeGenres() {
		// https://api.jikan.moe/v3/search/anime?genre[]=1&genre_exclude=0
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/anime", response,
				Parameter.param("genre", "1"),
				Parameter.param("genre_exclude", "1"));

		Collection<AnimeSearchSub> results = jikan.query().anime().search()
				.genres(AnimeGenre.ACTION)
				.excludeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
	}

	@Test
	void fetchSearch_includeGenres() {
		// https://api.jikan.moe/v3/search/anime?genre_exclude=1
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/anime", response,
				Parameter.param("genre_exclude", "1"));

		Collection<AnimeSearchSub> results = jikan.query().anime().search()
				.genres(AnimeGenre.ACTION)
				.includeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
	}
}
