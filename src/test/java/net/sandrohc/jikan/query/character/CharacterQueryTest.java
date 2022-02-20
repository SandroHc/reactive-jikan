/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchCharacterDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/characters/11757", "characters/getCharacterById.json");

		/* Act */
		CharacterQuery query = jikan.query().character().get(11757);
		Character character = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters/11757");

		// Character
		softly = new SoftAssertions();
		softly.assertThat(character).isNotNull();
		softly.assertThat(character.toString()).isNotNull();
		softly.assertThat(character.malId).isEqualTo(36765);
		softly.assertThat(character.url).isEqualTo("https://myanimelist.net/character/36765/Kazuto_Kirigaya");
		softly.assertThat(character.name).isEqualTo("Kazuto Kirigaya");
		softly.assertThat(character.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/7/204821.jpg");
		softly.assertThat(character.nicknames).containsExactlyInAnyOrder("Kirito", "The Black Swordsman", "Beater", "Kazu");
		softly.assertThat(character.favorites).isEqualTo(38444);
		softly.assertThat(character.about).startsWith("Birthday: October 7, 2008Age: 14 (Beginning of Aincrad arc), 16");
		softly.assertThat(character.about).hasSize(1469);
		softly.assertAll();
	}
}
