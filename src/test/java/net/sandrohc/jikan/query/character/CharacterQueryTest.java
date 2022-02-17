/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchCharacterDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/characters/36765", 1, "characters/getCharacterById.json");

		/* Act */
		CharacterQuery query = jikan.query().character().get(11757);
		Character character = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters/36765");

		// Character
		softly = new SoftAssertions();
		softly.assertThat(character).isNotNull();
		softly.assertThat(character.toString()).isNotNull();
		softly.assertThat(character.malId).isEqualTo(36765);
		softly.assertThat(character.url).isEqualTo("URL");
		softly.assertThat(character.name).isEqualTo("Kazuto Kirigaya");
		softly.assertThat(character.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertThat(character.nicknames).containsExactly("NICK");
		softly.assertThat(character.favourites).isEqualTo(1);
		softly.assertThat(character.about).isEqualTo("ABOUT");
		softly.assertAll();
	}
}
