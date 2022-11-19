/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaSearchQueryTest extends QueryTest {

	@Test
	void fetchMangaSearch() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga", "manga/getMangaSearch.json",
				Parameter.param("type", "manga"),
				Parameter.param("score", "1.0"),
				Parameter.param("min_score", "2.0"),
				Parameter.param("max_score", "3.0"),
				Parameter.param("status", "complete"),
				Parameter.param("sfw", "true"),
				Parameter.param("genres[]", "1,2"),
				Parameter.param("genres_exclude[]", "9,12"),
				Parameter.param("order_by", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("letter", "abc"),
				Parameter.param("magazine[]", "1,2"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "2"),
				Parameter.param("q", "name")
		);

		/* Act */
		MangaSearchQuery query = jikan.query().manga().search()
				.query("name")
				.page(1)
				.limit(2)
				.type(MangaType.MANGA)
				.score(1.0D)
				.minimumScore(2.0D)
				.maximumScore(3.0D)
				.status(MangaStatus.COMPLETED)
				.safeForWork(true)
				.genres(Genre.ACTION, Genre.ADVENTURE)
				.excludeGenres(Genre.ECCHI, Genre.HENTAI)
				.orderBy(MangaOrderBy.MAL_ID, SortOrder.ASCENDING)
				.suffix("abc")
				.magazines(1, 2);
		Collection<Manga> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga?type=manga&score=1.0&min_score=2.0&max_score=3.0&status=complete&sfw=true&genres[]=1,2&genres_exclude[]=9,12&order_by=mal_id&sort=asc&letter=abc&magazine[]=1,2&page=1&limit=2&q=name");

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
				.excludeGenres(Genre.ACTION, Genre.ADVENTURE);

		assertThat(query.genres).isNull();
		assertThat(query.genresExclude).containsExactlyInAnyOrder(Genre.ACTION, Genre.ADVENTURE);

		assertThat(query.getUrl().build()).isEqualTo("/manga?genres_exclude[]=1,2");
	}
}
