/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class ClubSearchQueryTest extends QueryTest {

	@Test
	void fetchClubSearch() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/clubs", "clubs/getClubsSearch.json",
				Parameter.param("type", "public"),
				Parameter.param("category", "anime"),
				Parameter.param("order_by", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("letter", "abc"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "2"),
				Parameter.param("q", "name"));

		/* Act */
		ClubSearchQuery query = jikan.query().club().search()
				.query("name")
				.page(1)
				.limit(2)
				.type(ClubType.PUBLIC)
				.category(ClubCategory.ANIME)
				.orderBy(ClubOrderBy.MAL_ID, SortOrder.ASCENDING)
				.suffix("abc");
		Collection<Club> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/clubs?type=public&category=anime&order_by=mal_id&sort=asc&letter=abc&page=1&limit=2&q=name");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Club club = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(club.toString()).isNotNull();
		softly.assertThat(club.malId).isEqualTo(1);
		softly.assertThat(club.url).isEqualTo("https://myanimelist.net/clubs.php?cid=1");
		softly.assertThat(club.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/clubs/16/222057.jpg");
		softly.assertThat(club.name).isEqualTo("Cowboy Bebop");
		softly.assertThat(club.members).isEqualTo(1379);
		softly.assertThat(club.category).isEqualTo(ClubCategory.ANIME);
		softly.assertThat(club.created).isEqualTo(LocalDateTime.of(2007, Month.MARCH, 29, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(club.access).isEqualTo(ClubType.PUBLIC);
		softly.assertAll();
	}
}
