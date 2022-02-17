/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class MangaStatisticsQueryTest extends RequestTest {

	@Test
	void fetchMangaStatistics() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/statistics", 1, "manga/getMangaStatistics.json");

		/* Act */
		MangaStatisticsQuery query = jikan.query().manga().statistics(96792);
		Statistics statistics = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/statistics");

		// Statistics
		softly = new SoftAssertions();
		softly.assertThat(statistics).isNotNull();
		softly.assertThat(statistics.toString()).isNotNull();
		softly.assertThat(statistics.seeing).isEqualTo(54058);
		softly.assertThat(statistics.completed).isEqualTo(42711);
		softly.assertThat(statistics.onHold).isEqualTo(3209);
		softly.assertThat(statistics.dropped).isEqualTo(1906);
		softly.assertThat(statistics.planToSee).isEqualTo(16489);
		softly.assertThat(statistics.total).isEqualTo(118373);
		softly.assertThat(statistics.scores)
				.extracting(s -> s.score, s -> s.votes, s -> s.percentage)
				.containsExactly(
						tuple(1, 19669, 1.5F),
						tuple(2, 21048, 1.6F),
						tuple(3, 35624, 2.7F),
						tuple(4, 64411, 4.8F),
						tuple(5, 98516, 7.4F),
						tuple(6, 150171, 11.2F),
						tuple(7, 250296, 18.7F),
						tuple(8, 264412, 19.8F),
						tuple(9, 212935, 15.9F),
						tuple(10, 219496, 16.4F)
				);
		int totalScores = statistics.scores.stream().mapToInt(s -> s.votes).sum();
		softly.assertThat(statistics.total >= totalScores).isTrue();
		softly.assertAll();
	}
}
