/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterAnimeQueryTest extends RequestTest {

	@Test
	void fetchCharacterAnime() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/characters/36765/anime", 1, "characters/getCharacterAnime.json");

		/* Act */
		CharacterAnimeQuery query = jikan.query().character().anime(11757);
		List<CharacterAnime> animeList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters/36765/anime");

		// Anime
		assertThat(animeList).isNotNull();
		assertThat(animeList).hasSize(1);

		CharacterAnime anime = animeList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(anime.toString()).isNotNull();
		softly.assertThat(anime.role).isEqualTo("ROLE");
		softly.assertThat(anime.anime.malId).isEqualTo(1);
		softly.assertThat(anime.anime.url).isEqualTo("URL");
		softly.assertThat(anime.anime.name).isEqualTo("NAME");
		softly.assertThat(anime.anime.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertAll();
	}
}
