/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

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

public class AnimePicturesQueryTest extends QueryTest {

	@Test
	void fetchAnimePictures() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/pictures", "anime/getAnimePictures.json");

		/* Act */
		AnimePicturesQuery query = jikan.query().anime().pictures(11757);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/pictures");

		// Pictures
		softly = new SoftAssertions();
		softly.assertThat(pictures).isNotNull();
		softly.assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p.webp.imageUrl)
				.containsExactlyInAnyOrder(
						tuple("https://cdn.myanimelist.net/images/anime/8/36343.jpg", "https://cdn.myanimelist.net/images/anime/8/36343.webp")
				);
		softly.assertAll();
	}
}
