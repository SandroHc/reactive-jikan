/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserClubsQueryTest extends QueryTest {

	@Test
	void fetchUserClubs() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/clubs", "users/getUserClubs.json");

		/* Act */
		UserClubsQuery query = jikan.query().user().clubs("USER_NAME");
		Collection<ClubSimple> clubs = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/clubs");

		// Clubs
		softly = new SoftAssertions();
		softly.assertThat(clubs).isNotNull();
		softly.assertThat(clubs).hasSize(2);
		softly.assertThat(clubs)
				.extracting(c -> c.malId, c -> c.url, c -> c.name)
				.containsExactlyInAnyOrder(
						tuple(669, "https://myanimelist.net/clubs.php?cid=669", "AnimePT"),
						tuple(18051, "https://myanimelist.net/clubs.php?cid=18051", "One Piece")
				);
		softly.assertAll();
	}
}
