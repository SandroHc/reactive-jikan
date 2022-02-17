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
	void fetchCharacters() throws JikanUrlException, JikanQueryException {
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
		Iterator<CharacterBasic> charactersIt = characters.iterator();

		CharacterBasic character1 = charactersIt.next();
		softly = new SoftAssertions();
		softly.assertThat(character1.toString()).isNotNull();
		softly.assertThat(character1.role).isEqualTo("Main");
		softly.assertThat(character1.character.malId).isEqualTo(36765);
		softly.assertThat(character1.character.name).isEqualTo("Kirigaya, Kazuto");
		softly.assertThat(character1.role).isEqualTo("Main");
		softly.assertThat(character1.voiceActors)
				.extracting(va -> va.person.malId, va -> va.person.name, va -> va.language)
				.containsExactly(
						tuple(732, "Papenbrook, Bryce", "English"),
						tuple(11817, "Matsuoka, Yoshitsugu", "Japanese")
				);
		softly.assertAll();

		CharacterBasic character2 = charactersIt.next();
		softly = new SoftAssertions();
		softly.assertThat(character2.toString()).isNotNull();
		softly.assertThat(character2.role).isEqualTo("Main");
		softly.assertThat(character2.character.malId).isEqualTo(36828);
		softly.assertThat(character2.character.name).isEqualTo("Yuuki, Asuna");
		softly.assertThat(character2.role).isEqualTo("Main");
		softly.assertThat(character1.voiceActors)
				.extracting(va -> va.person.malId, va -> va.person.name, va -> va.language)
				.containsExactly(
						tuple(890, "Tomatsu, Haruka", "Japanese"),
						tuple(1650, "Leigh, Cherami", "English")
				);
		softly.assertAll();

		assertThat(charactersIt).isExhausted();
	}
}
