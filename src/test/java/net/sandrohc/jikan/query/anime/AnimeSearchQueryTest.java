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

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeSearchQueryTest extends RequestTest {

	@Test
	void fetchAnimeSearch() throws JikanQueryException, JikanUrlException, JikanInvalidArgumentException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/reviews", "anime/getAnimeSearch.json",
				Parameter.param("score", "1.0"),
				Parameter.param("status", "complete"),
				Parameter.param("rating", "pg"),
				Parameter.param("genres[]", "1,2"),
				Parameter.param("order_by", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("q", "test"));

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
		assertThat(query.getUrl().build()).isEqualTo("/anime?score=1.0&status=complete&rating=pg&genres[]=1,2&order_by=mal_id&sort=asc&page=1&limit=1&q=test");

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
		softly.assertThat(result.score).isEqualTo(7.65D);
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.parse("2010-01-07"));
		softly.assertThat(result.aired.to).isEqualTo(LocalDate.parse("2010-04-01"));
		softly.assertThat(result.members).isEqualTo(484011);
		softly.assertThat(result.rating).isEqualTo(AgeRating.PG13);
		softly.assertAll();
	}

	@Test
	void fetchAnimeSearch_excludeGenres() throws JikanUrlException {
		AnimeSearchQuery query = jikan.query().anime().search()
				.excludeGenres(AnimeGenre.ACTION, AnimeGenre.ADVENTURE);

		assertThat(query.genres).isNull();
		assertThat(query.genresExclude).containsExactlyInAnyOrder(AnimeGenre.ACTION, AnimeGenre.ADVENTURE);

		assertThat(query.getUrl().build()).isEqualTo("/anime?genres_exclude[]=1,2");
	}
}
