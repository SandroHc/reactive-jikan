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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class CharacterVoiceActorsQueryTest extends RequestTest {

	@Test
	void fetchVoiceActors() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/characters/36765/voices", 1, "characters/getCharacterVoiceActors.json");

		/* Act */
		CharacterVoiceActorsQuery query = jikan.query().character().voiceActors(11757);
		List<CharacterVoiceActor> voiceActors = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/characters/36765/voices");

		// Manga
		assertThat(voiceActors).isNotNull();
		Iterator<CharacterVoiceActor> voiceActorsIt = voiceActors.iterator();

		CharacterVoiceActor voiceActor = voiceActorsIt.next();
		softly = new SoftAssertions();
		softly.assertThat(voiceActor.toString()).isNotNull();
		softly.assertThat(voiceActor.language).isEqualTo("LANGUAGE");
		softly.assertThat(voiceActor.person.malId).isEqualTo(1);
		softly.assertThat(voiceActor.person.url).isEqualTo("URL");
		softly.assertThat(voiceActor.person.name).isEqualTo("NAME");
		softly.assertThat(voiceActor.person.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertAll();

		assertThat(voiceActorsIt).isExhausted();
	}
}
