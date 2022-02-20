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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaPicturesQueryTest extends QueryTest {

	@Test
	void fetchMangaPictures() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/pictures", "manga/getMangaPictures.json");

		/* Act */
		MangaPicturesQuery query = jikan.query().manga().pictures(23390);
		Collection<Images> pictures = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/pictures");

		// Pictures
		assertThat(pictures).isNotNull();
		assertThat(pictures).hasSize(1);

		Images picture = pictures.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(picture.toString()).isNotNull();
		softly.assertThat(picture.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846.jpg");
		softly.assertThat(picture.jpg.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846t.jpg");
		softly.assertThat(picture.jpg.mediumImageUrl).isNull();
		softly.assertThat(picture.jpg.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846l.jpg");
		softly.assertThat(picture.jpg.maximumImageUrl).isNull();
		softly.assertThat(picture.webp.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846.webp");
		softly.assertThat(picture.webp.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846t.webp");
		softly.assertThat(picture.webp.mediumImageUrl).isNull();
		softly.assertThat(picture.webp.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846l.webp");
		softly.assertThat(picture.webp.maximumImageUrl).isNull();
		softly.assertAll();
	}
}
