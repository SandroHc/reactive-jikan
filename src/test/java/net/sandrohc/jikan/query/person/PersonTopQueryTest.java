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
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTopQueryTest extends QueryTest {

	@Test
	void fetchPersonTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/top/people", "top/getTopPeople.json");

		/* Act */
		PersonTopQuery query = jikan.query().person().top();
		Collection<Person> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/top/people");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Person person = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(person.toString()).isNotNull();
		softly.assertThat(person.malId).isEqualTo(118);
		softly.assertThat(person.url).isEqualTo("https://myanimelist.net/people/118/Hiroshi_Kamiya");
		softly.assertThat(person.name).isEqualTo("Hiroshi Kamiya");
		softly.assertThat(person.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/1/58597.jpg");
		softly.assertThat(person.websiteUrl).isEqualTo("WEBSITE");
		softly.assertThat(person.givenName).isEqualTo("浩史");
		softly.assertThat(person.familyName).isEqualTo("神谷");
		softly.assertThat(person.alternativeNames).containsExactlyInAnyOrder("ヒロC", "HiroC", "Kamiyan");
		softly.assertThat(person.birthday).isEqualTo(LocalDate.of(1975, Month.JANUARY, 28).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(person.favorites).isEqualTo(100361);
		softly.assertThat(person.about).startsWith("Birth place: Matsudo, Chiba Prefecture, Japan");
		softly.assertThat(person.about).hasSize(1703);
		softly.assertAll();
	}
}
