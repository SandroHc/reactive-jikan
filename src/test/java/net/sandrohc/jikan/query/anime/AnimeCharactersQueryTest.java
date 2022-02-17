/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

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
import static org.assertj.core.api.Assertions.tuple;

public class AnimeCharactersQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeCharacters() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/characters", 1, "anime/getAnimeCharacters.json");

		/* Act */
		AnimeCharactersQuery query = jikan.query().anime().characters(11757);
		List<CharacterBasic> characters = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/characters");

		// Characters
		assertThat(characters).isNotNull();
		assertThat(characters).hasSize(1);

		CharacterBasic character = characters.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character.toString()).isNotNull();
		softly.assertThat(character.role).isEqualTo("Main");
		softly.assertThat(character.character.malId).isEqualTo(36765);
		softly.assertThat(character.character.name).isEqualTo("Kirigaya, Kazuto");
		softly.assertThat(character.role).isEqualTo("Main");
		softly.assertThat(character.voiceActors)
				.extracting(va -> va.person.malId, va -> va.person.name, va -> va.language)
				.containsExactly(
						tuple(732, "Papenbrook, Bryce", "English"),
						tuple(11817, "Matsuoka, Yoshitsugu", "Japanese")
				);
		softly.assertAll();
	}
}
