/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class PersonPicturesQueryTest extends QueryTest {

	@Test
	void fetchPersonPictures() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people/1/pictures", "people/getPersonPictures.json");

		/* Act */
		PersonPicturesQuery query = jikan.query().person().pictures(1);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people/1/pictures");

		// Pictures
		softly = new SoftAssertions();
		softly.assertThat(pictures).isNotNull();
		softly.assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p != null && p.webp != null ? p.webp.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple("https://cdn.myanimelist.net/images/voiceactors/3/17141.jpg", null)
				);
		softly.assertAll();
	}
}
