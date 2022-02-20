/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTopQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchCharacterTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/top/characters", "top/getTopCharacters.json");

		/* Act */
		CharacterTopQuery query = jikan.query().character().top();
		List<Character> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/top/characters");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Character character1 = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(character1.toString()).isNotNull();
		softly.assertThat(character1.malId).isEqualTo(417);
		softly.assertThat(character1.url).isEqualTo( "https://myanimelist.net/character/417/Lelouch_Lamperouge");
		softly.assertThat(character1.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/8/406163.jpg");
		softly.assertThat(character1.name).isEqualTo("Lelouch Lamperouge");
		softly.assertThat(character1.nicknames).containsExactlyInAnyOrder("Lelouch vi Britannia", "Zero", "Lulu", "Black Prince", "The Demon Emperor");
		softly.assertThat(character1.favorites).isEqualTo(150195);
		softly.assertThat(character1.about).startsWith("Age: 17 (first season), 18 (second season)Date of Birth:  December 5, 1999");
		softly.assertThat(character1.about).hasSize(3000);
		softly.assertAll();
	}
}
