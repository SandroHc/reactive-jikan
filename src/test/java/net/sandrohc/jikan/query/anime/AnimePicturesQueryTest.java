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
import net.sandrohc.jikan.test.RequestTest;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimePicturesQueryTest extends RequestTest {

	@Test
	void fetchPictures() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/pictures", 1, "anime/getAnimePictures.json");

		/* Act */
		AnimePicturesQuery query = jikan.query().anime().pictures(11757);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/pictures");

		// Pictures
		assertThat(pictures).isNotNull();
		assertThat(pictures)
				.extracting(p -> p.jpg.imageUrl, p -> p.webp.imageUrl)
				.containsExactly(
						tuple("https://cdn.myanimelist.net/images/anime/8/36343l.jpg", "https://cdn.myanimelist.net/images/anime/8/36343.jpg"),
						tuple("https://cdn.myanimelist.net/images/anime/8/36343l.jpg", "https://cdn.myanimelist.net/images/anime/8/36343.jpg")
				);
	}
}
