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

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaSearchQueryTest extends RequestTest {

	@Test
	void fetchMangaSearch() throws JikanInvalidArgumentException, JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga", 1, "manga/getMangaSearch.json",
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
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Manga result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(2915);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/2915/Testarotho");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/3/200365.jpg?s=2925c3aa1fca9d2e89f5480cfbf95984");
		softly.assertThat(result.title).isEqualTo("Testarotho");
		softly.assertThat(result.publishing).isFalse();
		softly.assertThat(result.synopsis).isEqualTo("Capria has spent her life sheltered...");
		softly.assertThat(result.type).isEqualTo(MangaType.MANGA);
		softly.assertThat(result.chapters).isEqualTo(26);
		softly.assertThat(result.volumes).isEqualTo(4);
		softly.assertThat(result.score).isEqualTo(6.60F);
		softly.assertThat(result.published.from).isEqualTo(LocalDate.parse("2000-06-09"));
		softly.assertThat(result.published.to).isEqualTo(LocalDate.parse("2002-10-09"));
		softly.assertThat(result.members).isEqualTo(612);
		softly.assertAll();
	}

	@Test
	void fetchSearch_excludeGenres() {
		// https://api.jikan.moe/v3/search/manga?genre[]=1&genre_exclude=1

		mock(mockServer, "/search/manga", response,
				Parameter.param("genre[]", "1"),
				Parameter.param("genre_exclude", "1"));

		Collection<MangaSearchSub> results = jikan.query().manga().search()
				.genres(MangaGenre.ACTION)
				.excludeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
	}

	@Test
	void fetchSearch_includeGenres() {
		// https://api.jikan.moe/v3/search/manga?genre_exclude=1

		mock(mockServer, "/search/manga", response,
				Parameter.param("genre_exclude", "1"));

		Collection<MangaSearchSub> results = jikan.query().manga().search()
				.genres(MangaGenre.ACTION)
				.includeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
	}
}
