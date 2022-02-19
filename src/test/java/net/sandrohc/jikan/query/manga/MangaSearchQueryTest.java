/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaSearchQueryTest extends RequestTest {

	@Test
	void fetchMangaSearch() throws JikanInvalidArgumentException, JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga", "manga/getMangaSearch.json",
				Parameter.param("q", "test"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("genre[]", "1,2"),
				Parameter.param("status", "completed"),
				Parameter.param("orderBy", "id"),
				Parameter.param("sort", "asc"),
				Parameter.param("rated", "pg"),
				Parameter.param("score", "1.0"),
				Parameter.param("startDate", "2020-01-01"),
				Parameter.param("endDate", "2020-12-31"));

		/* Act */
		MangaSearchQuery query = jikan.query().manga().search()
				.query("test")
				.page(1)
				.limit(1)
				.genres(MangaGenre.ACTION, MangaGenre.ADVENTURE)
				.status(MangaStatus.COMPLETED)
				.orderBy(MangaOrderBy.MAL_ID, SortOrder.ASCENDING)
				.score(1.0D);
		Collection<Manga> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga?score=1.0&status=complete&genres[]=1,2&order_by=mal_id&sort=asc&page=1&limit=1&q=test");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Manga result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(1);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/1/Monster");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/3/54525.jpg");
		softly.assertThat(result.title).isEqualTo("Monster");
		softly.assertThat(result.publishing).isFalse();
		softly.assertThat(result.synopsis).startsWith("Kenzou Tenma, a renowned Japanese neurosurgeon working in post-war Germany, faces a difficult choice");
		softly.assertThat(result.synopsis).hasSize(981);
		softly.assertThat(result.type).isEqualTo(MangaType.MANGA);
		softly.assertThat(result.chapters).isEqualTo(162);
		softly.assertThat(result.volumes).isEqualTo(18);
		softly.assertThat(result.score).isEqualTo(9.12D);
		softly.assertThat(result.published.from).isEqualTo(LocalDate.of(1994, Month.DECEMBER, 5).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.published.to).isEqualTo(LocalDate.of(2001, Month.DECEMBER, 20).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.members).isEqualTo(171735);
		softly.assertAll();
	}

	@Test
	void fetchMangaSearch_excludeGenres() throws JikanUrlException {
		MangaSearchQuery query = jikan.query().manga().search()
				.excludeGenres(MangaGenre.ACTION, MangaGenre.ADVENTURE);

		assertThat(query.genres).isNull();
		assertThat(query.genresExclude).containsExactlyInAnyOrder(MangaGenre.ACTION, MangaGenre.ADVENTURE);

		assertThat(query.getUrl().build()).isEqualTo("/manga?genres_exclude[]=1,2");
	}
}
