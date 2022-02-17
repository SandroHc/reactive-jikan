/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

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

public class CharacterPicturesQueryTest extends RequestTest {

	@Test
	void fetchPictures() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/characters/36765/pictures", 1, "characters/getCharacterPictures.json");

		/* Act */
		CharacterPicturesQuery query = jikan.query().character().pictures(11757);
		List<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters/36765/pictures");

		// Pictures
		assertThat(pictures).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p.webp.imageUrl)
				.containsExactly(
						tuple("JPG", "WEBP")
				);
		softly.assertAll();
	}
}
