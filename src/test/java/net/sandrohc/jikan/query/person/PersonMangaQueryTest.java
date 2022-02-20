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

public class PersonMangaQueryTest extends QueryTest {

	@Test
	void fetchPersonManga() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/people/1/manga", "people/getPersonManga.json");

		/* Act */
		PersonMangaQuery query = jikan.query().person().manga(1);
		Collection<PersonRole> roles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/people/1/manga");

		// Roles
		assertThat(roles).isNotNull();
		assertThat(roles).hasSize(1);

		PersonRole role = roles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(role.toString()).isNotNull();
		softly.assertThat(role.role).isEqualTo("Story & Art");
		softly.assertThat(role.entry.malId).isEqualTo(23390);
		softly.assertThat(role.entry.url).isEqualTo("https://myanimelist.net/manga/23390/Shingeki_no_Kyojin");
		softly.assertThat(role.entry.name).isEqualTo("Shingeki no Kyojin");
		softly.assertThat(role.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846.jpg?s=b6287253ff8bf4145d07a32a74bdff9e");
		softly.assertAll();
	}
}
