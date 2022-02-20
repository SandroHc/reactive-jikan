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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterVoiceActorsQueryTest extends QueryTest {

	@Test
	void fetchCharacterVoiceActors() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/characters/11757/voices", "characters/getCharacterVoiceActors.json");

		/* Act */
		CharacterVoiceActorsQuery query = jikan.query().character().voiceActors(11757);
		List<CharacterVoiceActor> voiceActors = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/characters/11757/voices");

		// Manga
		assertThat(voiceActors).isNotNull();
		assertThat(voiceActors).hasSize(1);

		CharacterVoiceActor voiceActor = voiceActors.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(voiceActor.toString()).isNotNull();
		softly.assertThat(voiceActor.language).isEqualTo("English");
		softly.assertThat(voiceActor.person.malId).isEqualTo(732);
		softly.assertThat(voiceActor.person.url).isEqualTo("https://myanimelist.net/people/732/Bryce_Papenbrook");
		softly.assertThat(voiceActor.person.name).isEqualTo("Papenbrook, Bryce");
		softly.assertThat(voiceActor.person.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/3/29853.jpg");
		softly.assertAll();
	}
}
