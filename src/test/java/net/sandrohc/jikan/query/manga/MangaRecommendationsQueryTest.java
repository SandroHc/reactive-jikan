/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaRecommendationsQueryTest extends RequestTest {

	@Test
	void fetchMangaRecommendations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/recommendations", 1, "manga/getMangaRecommendations.json");

		/* Act */
		MangaRecommendationsQuery query = jikan.query().manga().recommendations(96792);
		Collection<Recommendation> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/recommendations");

		// Recommendations
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		Recommendation recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.url).isEqualTo("https://myanimelist.net/manga/25/Fullmetal_Alchemist");
		softly.assertThat(recommendation.votes).isEqualTo(1);
		softly.assertAll();

		softly = new SoftAssertions();
		softly.assertThat(recommendation.entry.malId).isEqualTo(25);
		softly.assertThat(recommendation.entry.url).isEqualTo("https://cdn.myanimelist.net/images/manga/1/27600.jpg?s=9d0238ae01b9fbab777e64b023b7d654");
		softly.assertThat(recommendation.entry.name).isEqualTo("Fullmetal Alchemist");
		softly.assertThat(recommendation.entry.images.jpg.imageUrl).isEqualTo("https://myanimelist.net/recommendations/manga/25-96792");
		softly.assertAll();
	}
}
