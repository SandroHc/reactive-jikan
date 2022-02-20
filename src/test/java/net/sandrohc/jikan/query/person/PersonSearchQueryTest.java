/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonSearchQueryTest extends QueryTest {

	@Test
	void fetchPersonSearch() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people", "people/getPeopleSearch.json",
				Parameter.param("order_by", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("letter", "abc"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("q", "name")
		);

		/* Act */
		PersonSearchQuery query = jikan.query().person().search()
				.query("name")
				.page(1)
				.limit(1)
				.orderBy(PersonOrderBy.MAL_ID, SortOrder.ASCENDING)
				.suffix("abc");
		Collection<Person> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people?order_by=mal_id&sort=asc&letter=abc&page=1&limit=1&q=name");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Person person = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(person.toString()).isNotNull();
		softly.assertThat(person.malId).isEqualTo(1);
		softly.assertThat(person.url).isEqualTo("https://myanimelist.net/people/1/Tomokazu_Seki");
		softly.assertThat(person.name).isEqualTo("Tomokazu Seki");
		softly.assertThat(person.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/1/55486.jpg");
		softly.assertThat(person.websiteUrl).isEqualTo("WEBSITE");
		softly.assertThat(person.givenName).isEqualTo("智一");
		softly.assertThat(person.familyName).isEqualTo("関");
		softly.assertThat(person.alternativeNames).containsExactlyInAnyOrder("Seki Mondoya", "門戸 開", "Monto Hiraku");
		softly.assertThat(person.birthday).isEqualTo(LocalDate.of(1972, Month.SEPTEMBER, 8).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(person.favorites).isEqualTo(5772);
		softly.assertThat(person.about).isEqualTo("Hometown: Tokyo, JapanBlood type: ABTwitter: @seki0908");
		softly.assertAll();
	}
}
