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

public class PersonAnimeQueryTest extends QueryTest {

	@Test
	void fetchPersonAnime() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people/1/anime", "people/getPersonAnime.json");

		/* Act */
		PersonAnimeQuery query = jikan.query().person().anime(1);
		Collection<PersonRole> roles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people/1/anime");

		// Roles
		assertThat(roles).isNotNull();
		assertThat(roles).hasSize(1);

		PersonRole role = roles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(role.toString()).isNotNull();
		softly.assertThat(role.role).isEqualTo("add Theme Song Performance");
		softly.assertThat(role.entry.malId).isEqualTo(3080);
		softly.assertThat(role.entry.url).isEqualTo("https://myanimelist.net/anime/3080/Anime_Tenchou");
		softly.assertThat(role.entry.name).isEqualTo("Anime Tenchou");
		softly.assertThat(role.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/9/4635.jpg?s=ad3dd02ed42bccbc84e0bde5c9e4ac7d");
		softly.assertAll();
	}
}
