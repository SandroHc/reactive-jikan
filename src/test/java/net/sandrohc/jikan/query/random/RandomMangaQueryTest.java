/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomMangaQueryTest extends QueryTest {

	@Test
	void fetchRandomManga() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/random/manga", "random/getRandomManga.json");

		/* Act */
		RandomMangaQuery query = jikan.query().random().manga();
		Manga result = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/random/manga");

		// Result
		assertThat(result).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(131611);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/131611/Oda-san_to_no_Omoide_Manga");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/238222.jpg");
		softly.assertThat(result.title).isEqualTo("Oda-san to no Omoide Manga!");
		softly.assertThat(result.titleEnglish).isNull();
		softly.assertThat(result.titleJapanese).isEqualTo("尾田さんとの思い出漫画!");
		softly.assertAll();
	}
}
