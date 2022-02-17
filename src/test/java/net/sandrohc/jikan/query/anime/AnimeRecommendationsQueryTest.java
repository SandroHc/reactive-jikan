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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeRecommendationsQueryTest extends RequestTest {

	@Test
	void fetchRecommendations() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/recommendations", 1, "anime/getAnimeRecommendations.json");

		/* Act */
		AnimeRecommendationsQuery query = jikan.query().anime().recommendations(11757);
		Collection<Recommendation> recommendations = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/recommendations");

		// Recommendations
		assertThat(recommendations).isNotNull();
		Iterator<Recommendation> recommendationsIt = recommendations.iterator();

		Recommendation recommendation = recommendationsIt.next();
		softly = new SoftAssertions();
		softly.assertThat(recommendation.toString()).isNotNull();
		softly.assertThat(recommendation.entry.malId).isEqualTo(17265);
		softly.assertThat(recommendation.entry.url).isEqualTo("https://myanimelist.net/anime/17265/Log_Horizon");
		softly.assertThat(recommendation.entry.name).isEqualTo("Log Horizon");
		softly.assertThat(recommendation.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/5/84004.jpg?s=73d4b44ed253ca5c865ef6e026cec99f");
		softly.assertThat(recommendation.url).isEqualTo("https://myanimelist.net/recommendations/anime/11757-17265");
		softly.assertThat(recommendation.votes).isEqualTo(160);
		softly.assertAll();

		assertThat(recommendationsIt).isExhausted();
	}
}
