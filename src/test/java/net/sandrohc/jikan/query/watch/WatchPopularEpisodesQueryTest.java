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

public class WatchPopularEpisodesQueryTest extends QueryTest {

	@Test
	void fetchWatchPopularEpisodes() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/watch/episodes/popular", "watch/getWatchPopularEpisodes.json");

		/* Act */
		WatchPopularEpisodesQuery query = jikan.query().watch().popularEpisodes().page(1).limit(2);
		Collection<WatchEpisodes> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/watch/episodes/popular?page=1&limit=2");

		// Result
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		WatchEpisodes episodes = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(episodes.toString()).isNotNull();
		softly.assertThat(episodes.entry.malId).isEqualTo(1);
		softly.assertThat(episodes.entry.url).isEqualTo("https://myanimelist.net/anime/1/Cowboy_Bebop");
		softly.assertThat(episodes.entry.name).isEqualTo("Cowboy Bebop");
		softly.assertThat(episodes.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/icon-banned-youtube-rect.png");
		softly.assertThat(episodes.episodes)
				.extracting(e -> e.malId, e -> e.url, e -> e.name, e -> e.premium)
				.containsExactlyInAnyOrder(
						tuple(26, "https://myanimelist.net/anime/1/Cowboy_Bebop/episode/26", "Episode 26", false),
						tuple(25, "https://myanimelist.net/anime/1/Cowboy_Bebop/episode/25", "Episode 25", false)
				);
		softly.assertThat(episodes.regionLocked).isTrue();
		softly.assertAll();
	}
}
