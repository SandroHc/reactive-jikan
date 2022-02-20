/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomPersonQueryTest extends QueryTest {

	@Test
	void fetchRandomPerson() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/random/people", "random/getRandomPeople.json");

		/* Act */
		RandomPersonQuery query = jikan.query().random().person();
		Person result = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/random/people");

		// Result
		assertThat(result).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(1670);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/people/1670/Michael_Bauer");
		softly.assertThat(result.name).isEqualTo("Michael Bauer");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/2/42042.jpg");
		softly.assertAll();
	}
}
