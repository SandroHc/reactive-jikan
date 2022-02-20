/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonQueryTest extends QueryTest {

	@Test
	void fetchPersonDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people/1", "people/getPersonById.json");

		/* Act */
		PersonQuery query = jikan.query().person().get(1);
		Person person = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people/1");

		// Person
		assertThat(person).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(person.toString()).isNotNull();
		softly.assertThat(person.malId).isEqualTo(1);
		softly.assertThat(person.url).isEqualTo( "https://myanimelist.net/people/1/Tomokazu_Seki");
		softly.assertThat(person.name).isEqualTo("Tomokazu Seki");
		softly.assertThat(person.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/1/55486.jpg");
		softly.assertThat(person.websiteUrl).isEqualTo("WEBSITE");
		softly.assertThat(person.givenName).isEqualTo("智一");
		softly.assertThat(person.familyName).isEqualTo("関");
		softly.assertThat(person.alternativeNames).containsExactlyInAnyOrder("Seki Mondoya", "門戸 開", "Monto Hiraku");
		softly.assertThat(person.birthday).isEqualTo(LocalDate.of(1972, Month.SEPTEMBER, 8).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(person.favorites).isEqualTo(5772);
		softly.assertThat(person.about).startsWith("Hometown: Tokyo, Japan");
		softly.assertThat(person.about).hasSize(54);
		softly.assertAll();
	}
}
