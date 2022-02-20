/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaCharactersQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchMangaCharacters() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/characters", "manga/getMangaCharacters.json");

		/* Act */
		MangaCharactersQuery query = jikan.query().manga().characters(23390);
		Collection<CharacterBasic> characters = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/characters");

		// Characters
		assertThat(characters).isNotNull();
		assertThat(characters).hasSize(1);

		CharacterBasic character = characters.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character.toString()).isNotNull();
		softly.assertThat(character.role).isEqualTo(CharacterRole.MAIN);
		softly.assertThat(character.character.malId).isEqualTo(40881);
		softly.assertThat(character.character.url).isEqualTo("https://myanimelist.net/character/40881/Mikasa_Ackerman");
		softly.assertThat(character.character.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/9/215563.jpg?s=5b0650bb09a7e053afc6bad84ab48947");
		softly.assertThat(character.character.name).isEqualTo("Ackerman, Mikasa");
		softly.assertThat(character.voiceActors).hasSize(0);
		softly.assertAll();
	}
}
