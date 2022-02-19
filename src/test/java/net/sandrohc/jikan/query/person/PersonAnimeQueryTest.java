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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonAnimeQueryTest extends RequestTest {

	@Test
	void fetchPersonAnime() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/people/1/anime", 1, "people/getPersonAnime.json");

		/* Act */
		PersonAnimeQuery query = jikan.query().person().anime(23390);
		Collection<PersonRole> roles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/people/1/anime");

		// Roles
		assertThat(roles).isNotNull();
		assertThat(roles).hasSize(1);

		PersonRole role = roles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(role.toString()).isNotNull();
		softly.assertThat(role.role).isEqualTo("Theme Song Performance");
		softly.assertThat(role.entry.malId).isEqualTo(30205);
		softly.assertThat(role.entry.url).isEqualTo("URL");
		softly.assertThat(role.entry.name).isEqualTo("Aoharu x Kikanjuu");
		softly.assertThat(role.entry.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertAll();
	}
}
