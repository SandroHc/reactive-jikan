/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomCharacterQueryTest extends QueryTest {

	@Test
	void fetchRandomCharacter() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/random/characters", "random/getRandomCharacters.json");

		/* Act */
		RandomCharacterQuery query = jikan.query().random().character();
		Character result = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/random/characters");

		// Result
		assertThat(result).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(16972);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/character/16972/Korega");
		softly.assertThat(result.name).isEqualTo("Korega");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/12/66547.jpg");
		softly.assertAll();
	}
}
