/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserRecommendationsQueryTest extends QueryTest {

	@Test
	void fetchUserRecommendations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/recommendations", "users/getUserRecommendations.json");

		/* Act */
		UserRecommendationsQuery query = jikan.query().user().recommendations("USER_NAME");
		Collection<RecommendationMultiple> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/recommendations");

		// Recommendations
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		RecommendationMultiple recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.entry)
				.extracting(r -> r.malId, r -> r.url, r -> r.name, r -> r.images != null && r.images.jpg != null ? r.images.jpg.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple(16524, "https://myanimelist.net/anime/16524/Suisei_no_Gargantia", "Suisei no Gargantia", "https://cdn.myanimelist.net/images/anime/11/48817.jpg?s=b8c16cd2222ab6da13efcc86567d7f46"),
						tuple(33352, "https://myanimelist.net/anime/33352/Violet_Evergarden", "Violet Evergarden", "https://cdn.myanimelist.net/images/anime/1795/95088.jpg?s=76cf05d56876e34065e3408286cf1e22")
				);
		softly.assertThat(recommendation.url).isNull();
		softly.assertThat(recommendation.votes).isEqualTo(0);
		softly.assertAll();
	}
}
