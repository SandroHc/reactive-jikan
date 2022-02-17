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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class MangaCharactersQueryTest extends RequestTest {

	@Test
	void fetchMangaCharacters() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/characters", 1, "manga/getMangaCharacters.json");

		/* Act */
		MangaCharactersQuery query = jikan.query().manga().characters(23390);
		Collection<CharacterBasic> characters = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/characters");

		// Characters
		assertThat(characters).isNotNull();
		assertThat(characters).hasSize(1);

		CharacterBasic character = characters.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character.toString()).isNotNull();
		softly.assertThat(character.role).isEqualTo("Main");
		softly.assertThat(character.character.malId).isEqualTo(146157);
		softly.assertThat(character.character.url).isEqualTo("URL");
		softly.assertThat(character.character.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertThat(character.character.name).isEqualTo("Kamado, Nezuko");
		softly.assertThat(character.voiceActors)
				.extracting(va -> va.language, va -> va.person.malId, va -> va.person.url, va -> va.person.name, va -> va.person.images.jpg.imageUrl)
				.containsExactly(
						tuple("LANG", 1, "URL", "NAME", "IMAGE")
				);
		softly.assertAll();
	}
}
