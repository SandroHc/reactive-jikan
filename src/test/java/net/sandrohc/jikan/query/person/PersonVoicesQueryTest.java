/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonVoicesQueryTest extends QueryTest {

	@Test
	void fetchPersonVoices() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people/1/voices", "people/getPersonVoices.json");

		/* Act */
		PersonVoicesQuery query = jikan.query().person().voices(1);
		Collection<PersonVoiceActingRole> roles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people/1/voices");

		// Roles
		assertThat(roles).isNotNull();
		assertThat(roles).hasSize(1);

		PersonVoiceActingRole role = roles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(role.toString()).isNotNull();
		softly.assertThat(role.role).isEqualTo("Main");
		softly.assertThat(role.entry.malId).isEqualTo(48561);
		softly.assertThat(role.entry.url).isEqualTo("https://myanimelist.net/anime/48561/Jujutsu_Kaisen_0_Movie");
		softly.assertThat(role.entry.name).isEqualTo("Jujutsu Kaisen 0 Movie");
		softly.assertThat(role.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1121/119044.jpg?s=b4821cdac7d0126dc79e948b62df4d2e");
		softly.assertThat(role.character.malId).isEqualTo(164476);
		softly.assertThat(role.character.url).isEqualTo("https://myanimelist.net/character/164476/Panda");
		softly.assertThat(role.character.name).isEqualTo("Panda");
		softly.assertThat(role.character.images.jpg.imageUrl).isEqualTo( "https://cdn.myanimelist.net/r/84x124/images/characters/16/423950.jpg?s=0028e4781ba40417b1e7779268b2bc59");
		softly.assertAll();
	}
}
