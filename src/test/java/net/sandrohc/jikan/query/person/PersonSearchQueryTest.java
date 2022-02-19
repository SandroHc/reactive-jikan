/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonSearchQueryTest extends RequestTest {

	@Test
	void fetchPersonSearch() throws JikanQueryException, JikanUrlException, JikanInvalidArgumentException {
		/* Arrange */
		mock(mockServer, "/people", 1, "people/getPeopleSearch.json",
				Parameter.param("q", "asuna"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"));

		/* Act */
		PersonSearchQuery query = jikan.query().person().search()
				.query("asuna")
				.page(1)
				.limit(1);
		Collection<Person> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/people");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Person person = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(person.toString()).isNotNull();
		softly.assertThat(person.malId).isEqualTo(11817);
		softly.assertThat(person.url).isEqualTo("URL");
		softly.assertThat(person.name).isEqualTo("Yoshitsugu Matsuoka");
		softly.assertThat(person.images.jpg.imageUrl).isEqualTo("IMG");
		softly.assertThat(person.websiteUrl).isEqualTo("WEBSITE");
		softly.assertThat(person.givenName).isEqualTo("GIVEN");
		softly.assertThat(person.familyName).isEqualTo("FAMILY");
		softly.assertThat(person.alternativeNames).containsExactly("NICK");
		softly.assertThat(person.birthday).isEqualTo(LocalDate.of(2020, Month.JANUARY, 1).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(person.favorites).isEqualTo(1);
		softly.assertThat(person.about).isEqualTo("ABOUT");
		softly.assertAll();
	}
}
