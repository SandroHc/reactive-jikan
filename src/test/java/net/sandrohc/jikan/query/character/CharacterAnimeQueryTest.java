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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterAnimeQueryTest extends QueryTest {

	@Test
	void fetchCharacterAnime() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/characters/11757/anime", "characters/getCharacterAnime.json");

		/* Act */
		CharacterAnimeQuery query = jikan.query().character().anime(11757);
		List<CharacterAnime> animeList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters/11757/anime");

		// Anime
		assertThat(animeList).isNotNull();
		assertThat(animeList).hasSize(1);

		CharacterAnime anime = animeList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(anime.toString()).isNotNull();
		softly.assertThat(anime.role).isEqualTo(CharacterRole.MAIN);
		softly.assertThat(anime.anime.malId).isEqualTo(11757);
		softly.assertThat(anime.anime.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online");
		softly.assertThat(anime.anime.name).isEqualTo("Sword Art Online");
		softly.assertThat(anime.anime.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717.jpg?s=e418310b575f6afe9ac03b383527169d");
		softly.assertAll();
	}
}
