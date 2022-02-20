/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomAnimeQueryTest extends QueryTest {

	@Test
	void fetchRandomAnime() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/random/anime", "random/getRandomAnime.json");

		/* Act */
		RandomAnimeQuery query = jikan.query().random().anime();
		Anime result = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/random/anime");

		// Result
		assertThat(result).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(44026);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/44026/Zannen_na_Ikimono_Jiten_2019");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1957/110202.jpg");
		softly.assertThat(result.title).isEqualTo("Zannen na Ikimono Jiten (2019)");
		softly.assertThat(result.titleEnglish).isNull();
		softly.assertThat(result.titleJapanese).isEqualTo("ざんねんないきもの事典 (2019)");
		softly.assertAll();
	}
}
