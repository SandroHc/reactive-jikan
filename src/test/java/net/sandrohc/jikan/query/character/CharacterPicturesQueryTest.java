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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CharacterPicturesQueryTest extends QueryTest {

	@Test
	void fetchCharacterPictures() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/characters/11757/pictures", "characters/getCharacterPictures.json");

		/* Act */
		CharacterPicturesQuery query = jikan.query().character().pictures(11757);
		List<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters/11757/pictures");

		// Pictures
		softly = new SoftAssertions();
		softly.assertThat(pictures).isNotNull();
		softly.assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p != null && p.webp != null ? p.webp.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple("https://cdn.myanimelist.net/images/characters/13/159377.jpg", null)
				);
		softly.assertAll();
	}
}
