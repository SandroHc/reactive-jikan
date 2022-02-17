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

public class CharacterMangaQueryTest extends RequestTest {

	@Test
	void fetchManga() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/characters/36765/manga", 1, "characters/getCharacterManga.json");

		/* Act */
		CharacterMangaQuery query = jikan.query().character().manga(11757);
		List<CharacterManga> mangaList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters/36765/manga");

		// Manga
		assertThat(mangaList).isNotNull();
		Iterator<CharacterManga> mangaIt = mangaList.iterator();

		CharacterManga manga = mangaIt.next();
		softly = new SoftAssertions();
		softly.assertThat(manga.toString()).isNotNull();
		softly.assertThat(manga.role).isEqualTo("ROLE");
		softly.assertThat(manga.manga.malId).isEqualTo(1);
		softly.assertThat(manga.manga.url).isEqualTo("URL");
		softly.assertThat(manga.manga.name).isEqualTo("NAME");
		softly.assertThat(manga.manga.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertAll();

		assertThat(mangaIt).isExhausted();
	}
}
