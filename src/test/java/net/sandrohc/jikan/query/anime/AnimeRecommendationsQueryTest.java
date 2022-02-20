/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeRecommendationsQueryTest extends QueryTest {

	@Test
	void fetchAnimeRecommendations() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/recommendations", "anime/getAnimeRecommendations.json");

		/* Act */
		AnimeRecommendationsQuery query = jikan.query().anime().recommendations(11757);
		Collection<RecommendationSimple> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/recommendations");

		// Recommendations
		assertThat(recommendations).isNotNull();
		assertThat(recommendations).hasSize(1);

		RecommendationSimple recommendation = recommendations.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.entry.malId).isEqualTo(17265);
		softly.assertThat(recommendation.entry.url).isEqualTo("https://myanimelist.net/anime/17265/Log_Horizon");
		softly.assertThat(recommendation.entry.name).isEqualTo("Log Horizon");
		softly.assertThat(recommendation.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/5/84004.jpg?s=73d4b44ed253ca5c865ef6e026cec99f");
		softly.assertThat(recommendation.url).isEqualTo("https://myanimelist.net/recommendations/anime/11757-17265");
		softly.assertThat(recommendation.votes).isEqualTo(174);
		softly.assertAll();
	}
}
