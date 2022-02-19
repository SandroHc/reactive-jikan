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
		mockFromFile(mockServer, "/anime", "anime/getAnimeSearch.json",
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
		softly.assertThat(result.malId).isEqualTo(24751);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/24751/Akuma_no_Riddle__Shousha_wa_Dare_Nukiuchi_Test");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/63653.jpg");
		softly.assertThat(result.title).isEqualTo("Akuma no Riddle: Shousha wa Dare? Nukiuchi Test");
		softly.assertThat(result.airing).isFalse();
		softly.assertThat(result.synopsis).startsWith("Unaired episode 13 of Akuma no Riddle released with the seventh Blu-ray/DVD volume. The special will be previewed");
		softly.assertThat(result.synopsis).hasSize(214);
		softly.assertThat(result.type).isEqualTo(AnimeType.SPECIAL);
		softly.assertThat(result.episodes).isEqualTo(1);
		softly.assertThat(result.score).isEqualTo(6.52D);
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.of(2014, Month.NOVEMBER, 22).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.aired.to).isNull();
		softly.assertThat(result.members).isEqualTo(36727);
		softly.assertThat(result.rating).isEqualTo(AgeRating.R17);
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
