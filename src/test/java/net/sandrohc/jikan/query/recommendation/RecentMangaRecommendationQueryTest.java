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

public class RecentMangaRecommendationQueryTest extends QueryTest {

	@Test
	void fetchMangaRecommendations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/recommendations/manga", "recommendations/getRecentMangaRecommendations.json");

		/* Act */
		RecentMangaRecommendationQuery query = jikan.query().recommendation().manga().page(1).limit(2);
		Collection<Recommendation> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/recommendations/manga?page=1&limit=2");

		// Result
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		Recommendation recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.malId).isEqualTo("2-656");
		softly.assertThat(recommendation.entry)
				.extracting(e -> e.malId, e -> e.url, e -> e.name, e -> e.images != null && e.images.jpg != null ? e.images.jpg.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple(2, "https://myanimelist.net/manga/2/Berserk", "Berserk", "https://cdn.myanimelist.net/images/manga/1/157897.jpg"),
						tuple(656, "https://myanimelist.net/manga/656/Vagabond", "Vagabond", "https://cdn.myanimelist.net/images/manga/2/181787.jpg")
				);
		softly.assertThat(recommendation.content).isEqualTo("Both has a good story and artstyle");
		softly.assertThat(recommendation.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 20).atTime(5, 31, 36).atOffset(ZoneOffset.UTC));
		softly.assertThat(recommendation.user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(recommendation.user.username).isEqualTo("USER_1");
		softly.assertThat(recommendation.user.images).isNull();
		softly.assertAll();
	}
}
