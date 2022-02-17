/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

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

public class MangaPicturesQueryTest extends RequestTest {

	@Test
	void fetchMangaPictures() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/pictures", 1, "manga/getMangaPictures.json");

		/* Act */
		MangaPicturesQuery query = jikan.query().manga().pictures(96792);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/pictures");

		// Pictures
		assertThat(pictures).isNotNull();
		assertThat(pictures).hasSize(1);

		Images picture = pictures.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(picture.toString()).isNotNull();
		softly.assertThat(picture.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.jpg");
		softly.assertThat(picture.jpg.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.jpg");
		softly.assertThat(picture.jpg.mediumImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.jpg");
		softly.assertThat(picture.jpg.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.jpg");
		softly.assertThat(picture.jpg.maximumImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.jpg");
		softly.assertThat(picture.webp.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.webp");
		softly.assertThat(picture.webp.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.webp");
		softly.assertThat(picture.webp.mediumImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.webp");
		softly.assertThat(picture.webp.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.webp");
		softly.assertThat(picture.webp.maximumImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/172790l.webp");
		softly.assertAll();
	}
}
