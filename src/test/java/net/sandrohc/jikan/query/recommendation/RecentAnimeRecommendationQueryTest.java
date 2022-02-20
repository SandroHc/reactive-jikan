/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.recommendation;

import java.time.*;
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

public class RecentAnimeRecommendationQueryTest extends QueryTest {

	@Test
	void fetchAnimeRecommendations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/recommendations/anime", "recommendations/getRecentAnimeRecommendations.json");

		/* Act */
		RecentAnimeRecommendationQuery query = jikan.query().recommendation().anime().page(1).limit(2);
		Collection<Recommendation> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/recommendations/anime?page=1&limit=2");

		// Result
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		Recommendation recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.malId).isEqualTo("22135-31646");
		softly.assertThat(recommendation.entry)
				.extracting(e -> e.malId, e -> e.url, e -> e.name, e -> e.images != null && e.images.jpg != null ? e.images.jpg.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple(22135, "https://myanimelist.net/anime/22135/Ping_Pong_the_Animation", "Ping Pong the Animation", "https://cdn.myanimelist.net/images/anime/10/58041.jpg"),
						tuple(31646, "https://myanimelist.net/anime/31646/3-gatsu_no_Lion", "3-gatsu no Lion", "https://cdn.myanimelist.net/images/anime/6/82898.jpg")
				);
		softly.assertThat(recommendation.content).isEqualTo("Another hidden gem with great character dynamics and explores a rather underhyped sport");
		softly.assertThat(recommendation.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 20).atTime(14, 53, 29).atOffset(ZoneOffset.UTC));
		softly.assertThat(recommendation.user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(recommendation.user.username).isEqualTo("USER_1");
		softly.assertThat(recommendation.user.images).isNull();
		softly.assertAll();
	}
}
