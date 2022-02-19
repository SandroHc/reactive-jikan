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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class PersonPicturesQueryTest extends RequestTest {

	@Test
	void fetchPersonPictures() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/people/1/pictures", 1, "people/getPersonPictures.json");

		/* Act */
		PersonPicturesQuery query = jikan.query().person().pictures(1);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/people/1/pictures");

		// Pictures
		softly = new SoftAssertions();
		softly.assertThat(pictures).isNotNull();
		softly.assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p.webp.imageUrl)
				.containsExactly(
						tuple("https://cdn.myanimelist.net/images/anime/8/36343l.jpg", "https://cdn.myanimelist.net/images/anime/8/36343.jpg"),
						tuple("https://cdn.myanimelist.net/images/anime/8/36343l.jpg", "https://cdn.myanimelist.net/images/anime/8/36343.jpg")
				);
		softly.assertAll();
	}
}
