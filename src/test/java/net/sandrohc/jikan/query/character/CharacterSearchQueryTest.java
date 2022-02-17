/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterSearchQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchCharacterSearch() throws JikanQueryException, JikanUrlException, JikanInvalidArgumentException {
		/* Arrange */
		mock(mockServer, "/characters", 1, "characters/getCharactersSearch.json");

		/* Act */
		CharacterSearchQuery query = jikan.query().character().search()
				.query("asuna")
				.page(1)
				.limit(1);
		List<Character> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Character character1 = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character1.toString()).isNotNull();
		softly.assertThat(character1.malId).isEqualTo(92639);
		softly.assertThat(character1.url).isEqualTo("https://myanimelist.net/character/92639/Asuna");
		softly.assertThat(character1.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/12/315884.jpg?s=137d8ac45a770ba4abf6d165aea2dc3e");
		softly.assertThat(character1.name).isEqualTo("Asuna");
		softly.assertThat(character1.nicknames).containsExactly("NICK");
		softly.assertThat(character1.favourites).isEqualTo(1);
		softly.assertThat(character1.about).isEqualTo("ABOUT");
		softly.assertAll();
	}
}
