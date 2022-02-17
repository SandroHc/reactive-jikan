/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

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

public class AnimeStatisticsQueryTest extends RequestTest {

	@Test
	void fetchAnimeStatistics() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/anime/11757/statistics", 1, "anime/getAnimeStatistics.json");

		/* Act */
		AnimeStatisticsQuery query = jikan.query().anime().statistics(11757);
		Statistics statistics = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/statistics");

		// Statistics
		softly = new SoftAssertions();
		softly.assertThat(statistics).isNotNull();
		softly.assertThat(statistics.toString()).isNotNull();
		softly.assertThat(statistics.seeing).isEqualTo(68292);
		softly.assertThat(statistics.completed).isEqualTo(1624882);
		softly.assertThat(statistics.onHold).isEqualTo(21722);
		softly.assertThat(statistics.dropped).isEqualTo(75644);
		softly.assertThat(statistics.planToSee).isEqualTo(87594);
		softly.assertThat(statistics.total).isEqualTo(1878134);
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
