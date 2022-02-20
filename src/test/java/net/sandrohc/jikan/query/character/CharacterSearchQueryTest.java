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
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterSearchQueryTest extends QueryTest {

	@Test
	void fetchCharacterSearch() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/characters", "characters/getCharactersSearch.json",
				Parameter.param("order_by", "mal_id"),
				Parameter.param("sort", "asc"),
				Parameter.param("letter", "abc"),
				Parameter.param("q", "name"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1")
		);

		/* Act */
		CharacterSearchQuery query = jikan.query().character().search()
				.query("name")
				.page(1)
				.limit(1)
				.orderBy(CharacterOrderBy.MAL_ID, SortOrder.ASCENDING)
				.suffix("abc");
		List<Character> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters?order_by=mal_id&sort=asc&letter=abc&page=1&limit=1&q=name");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Character character1 = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character1.toString()).isNotNull();
		softly.assertThat(character1.malId).isEqualTo(1);
		softly.assertThat(character1.url).isEqualTo("https://myanimelist.net/character/1/Spike_Spiegel");
		softly.assertThat(character1.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/4/50197.jpg");
		softly.assertThat(character1.name).isEqualTo("Spike Spiegel");
		softly.assertThat(character1.nicknames).containsExactlyInAnyOrder("NICK");
		softly.assertThat(character1.favorites).isEqualTo(41206);
		softly.assertThat(character1.about).startsWith("Birthdate: June 26, 2044Height: 185 cm (6' 1\")Weight: 70 kg (155 lbs)Blood type: OPlanet of");
		softly.assertThat(character1.about).hasSize(1857);
		softly.assertAll();
	}
}
