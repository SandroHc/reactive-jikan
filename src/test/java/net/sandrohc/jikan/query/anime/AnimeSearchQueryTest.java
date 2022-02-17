/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.test.RequestTest;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeSearchQueryTest extends RequestTest {

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException, JikanUrlException, JikanQueryException {
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
				.rated(AgeRating.PG)
				.score(1.0F)
				.startDate(LocalDate.parse("2020-01-01"))
				.endDate(LocalDate.parse("2020-12-31"));
		Collection<Anime> searchResults = query.execute().collectList().block();

		/* Assert */
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo(MOCK_URL + "/anime/?q=test&page=1&limit=1&genre[]=1,2&status=completed&orderBy=mal_id&sort=asc&rated=pg&score=1.0&startDate=2020-01-01&endDate=2020-12-31");

		// Search Results
		assertThat(searchResults).isNotNull();
		Iterator<AnimeSearchSub> resultsIt = results.iterator();

		AnimeSearchSub r1 = resultsIt.next();
		assertThat(r1.toString()).isNotNull();
		assertThat(r1.malId).isEqualTo(6347);
		assertThat(r1.url).isEqualTo("https://myanimelist.net/anime/6347/Baka_to_Test_to_Shoukanjuu");
		assertThat(r1.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/3/50389.jpg?s=bb898c33476da7c587e8ec82afecee57");
		assertThat(r1.title).isEqualTo("Baka to Test to Shoukanjuu");
		assertThat(r1.airing).isFalse();
		assertThat(r1.synopsis).isEqualTo("Fumizuki Academy isn't a typical Japanese high school...");
		assertThat(r1.type).isEqualTo(AnimeType.TV);
		assertThat(r1.episodes).isEqualTo(13);
		assertThat(r1.score).isEqualTo(7.65F);
		assertThat(r1.aired.from).isEqualTo(LocalDate.parse("2010-01-07"));
		assertThat(r1.aired.to).isEqualTo(LocalDate.parse("2010-04-01"));
		assertThat(r1.members).isEqualTo(484011);
		assertThat(r1.rating).isEqualTo(AgeRating.PG13);

		AnimeSearchSub r2 = resultsIt.next();
		assertThat(r2.toString()).isNotNull();
		assertThat(r2.malId).isEqualTo(9471);
		assertThat(r2.url).isEqualTo("https://myanimelist.net/anime/9471/Baka_to_Test_to_Shoukanjuu__Matsuri");
		assertThat(r2.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/3/67303.jpg?s=048fca5bdfb21cefa072a303fe8047ad");
		assertThat(r2.title).isEqualTo("Baka to Test to Shoukanjuu: Matsuri");
		assertThat(r2.airing).isFalse();
		assertThat(r2.synopsis).isEqualTo("OVA of Baka to Test to Shoukanjuu...");
		assertThat(r2.type).isEqualTo(AnimeType.OVA);
		assertThat(r2.episodes).isEqualTo(2);
		assertThat(r2.score).isEqualTo(7.66F);
		assertThat(r2.aired.from).isEqualTo(LocalDate.parse("2011-02-23"));
		assertThat(r2.aired.to).isEqualTo(LocalDate.parse("2011-03-30"));
		assertThat(r2.members).isEqualTo(101901);
		assertThat(r2.rating).isEqualTo(AgeRating.PG13);

		assertThat(resultsIt).isExhausted();
	}

	@Test
	void fetchSearch_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().query("ab"), "query must be a minimum of 3 characters");
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().limit(-1),                        "limit must be between 0 and " + SearchQuery.LIMIT_MAX);
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().limit(SearchQuery.LIMIT_MAX + 1), "limit must be between 0 and " + SearchQuery.LIMIT_MAX);
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().score(-0.1F), "score must be between 0.0 and 10.0");
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().score(10.1F), "score must be between 0.0 and 10.0");
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
