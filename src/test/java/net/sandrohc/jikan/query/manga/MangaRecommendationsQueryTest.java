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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaRecommendationsQueryTest extends QueryTest {

	@Test
	void fetchMangaRecommendations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/recommendations", "manga/getMangaRecommendations.json");

		/* Act */
		MangaRecommendationsQuery query = jikan.query().manga().recommendations(23390);
		Collection<RecommendationSimple> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/recommendations");

		// Recommendations
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		RecommendationSimple recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.url).isEqualTo("https://myanimelist.net/recommendations/manga/23390-44489");
		softly.assertThat(recommendation.votes).isEqualTo(13);
		softly.assertAll();

		softly = new SoftAssertions();
		softly.assertThat(recommendation.entry.malId).isEqualTo(44489);
		softly.assertThat(recommendation.entry.url).isEqualTo("https://myanimelist.net/manga/44489/Houseki_no_Kuni");
		softly.assertThat(recommendation.entry.name).isEqualTo("Houseki no Kuni");
		softly.assertThat(recommendation.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/1/115443.jpg?s=9795cb8739785d402a60e5aade4ddf3b");
		softly.assertAll();
	}
}
