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

public class CharacterMangaQueryTest extends QueryTest {

	@Test
	void fetchCharacterManga() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/characters/11757/manga", "characters/getCharacterManga.json");

		/* Act */
		CharacterMangaQuery query = jikan.query().character().manga(11757);
		List<CharacterManga> mangaList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters/11757/manga");

		// Manga
		assertThat(mangaList).isNotNull();
		assertThat(mangaList).hasSize(1);

		CharacterManga manga = mangaList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(manga.toString()).isNotNull();
		softly.assertThat(manga.role).isEqualTo(CharacterRole.MAIN);
		softly.assertThat(manga.manga.malId).isEqualTo(21479);
		softly.assertThat(manga.manga.url).isEqualTo("https://myanimelist.net/manga/21479/Sword_Art_Online");
		softly.assertThat(manga.manga.name).isEqualTo("Sword Art Online");
		softly.assertThat(manga.manga.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/34697.jpg?s=370b6226f2da5f0061b8aba1bd43ece4");
		softly.assertAll();
	}
}
