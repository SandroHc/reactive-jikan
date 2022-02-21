/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.watch;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.watch.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class WatchRecentEpisodesQueryTest extends QueryTest {

	@Test
	void fetchWatchRecentEpisodes() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/watch/episodes", "watch/getWatchRecentEpisodes.json");

		/* Act */
		WatchRecentEpisodesQuery query = jikan.query().watch().recentEpisodes().page(1).limit(2);
		Collection<WatchEpisodes> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/watch/episodes?page=1&limit=2");

		// Result
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		WatchEpisodes episodes = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(episodes.toString()).isNotNull();
		softly.assertThat(episodes.entry.malId).isEqualTo(41946);
		softly.assertThat(episodes.entry.url).isEqualTo("https://myanimelist.net/anime/41946/Shuumatsu_no_Harem");
		softly.assertThat(episodes.entry.name).isEqualTo("Shuumatsu no Harem");
		softly.assertThat(episodes.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1491/117296.jpg");
		softly.assertThat(episodes.episodes)
				.extracting(e -> e.malId, e -> e.url, e -> e.name, e -> e.premium)
				.containsExactlyInAnyOrder(
						tuple(7, "https://myanimelist.net/anime/41946/Shuumatsu_no_Harem/episode/7", "Episode 7", true),
						tuple(6, "https://myanimelist.net/anime/41946/Shuumatsu_no_Harem/episode/6", "Episode 6", true)
				);
		softly.assertThat(episodes.regionLocked).isFalse();
		softly.assertAll();
	}
}
