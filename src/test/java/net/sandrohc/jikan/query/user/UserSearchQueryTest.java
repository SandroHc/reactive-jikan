/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserSearchQueryTest extends QueryTest {

	@Test
	void fetchUserSearch() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users", "users/getUsersSearch.json",
				Parameter.param("gender", "male"),
				Parameter.param("location", "place"),
				Parameter.param("maxAge", "100"),
				Parameter.param("minAge", "50"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("q", "name"));

		/* Act */
		UserSearchQuery query = jikan.query().user().search()
				.page(1)
				.limit(1)
				.query("name")
				.gender(UserGender.MALE)
				.location("place")
				.minAge(50)
				.maxAge(100);
		Collection<UserSimple> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users?gender=male&location=place&maxAge=100&minAge=50&page=1&limit=1&q=name");

		// Results
		softly = new SoftAssertions();
		softly.assertThat(results).isNotNull();
		softly.assertThat(results).hasSize(1);
		softly.assertThat(results)
				.extracting(u -> u.username, u -> u.url, u -> u.images != null && u.images.jpg != null ? u.images.jpg.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple("USER_1", "https://myanimelist.net/profile/USER_1", "https://cdn.myanimelist.net/images/questionmark_50.gif")
				);
		softly.assertAll();
	}
}
