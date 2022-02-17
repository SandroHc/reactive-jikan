/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeEpisodesQueryTest extends RequestTest {

	@Test
	void fetchAnimeEpisodes() throws JikanInvalidArgumentException, JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/episodes", 1, "anime/getAnimeEpisodes.json");

		/* Act */
		AnimeEpisodesQuery query = jikan.query().anime().episodes(11757);
		List<AnimeEpisode> episodes = query.page(1).execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/episodes");

		// Episodes
		assertThat(episodes).isNotNull();
		assertThat(episodes).hasSize(1);

		AnimeEpisode ep = episodes.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(ep.toString()).isNotNull();
		softly.assertThat(ep.malId).isEqualTo(1);
		softly.assertThat(ep.url).isEqualTo("URL");
		softly.assertThat(ep.title).isEqualTo("The World of Swords");
		softly.assertThat(ep.titleJapanese).isEqualTo("The World of Swords");
		softly.assertThat(ep.titleRomanji).isEqualTo("The World of Swords");
		softly.assertThat(ep.duration).isEqualTo(1);
		softly.assertThat(ep.aired).isEqualTo(LocalDateTime.of(2012, Month.JULY, 8, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(ep.filler).isFalse();
		softly.assertThat(ep.recap).isFalse();
		softly.assertThat(ep.forumUrl).isEqualTo("URL");
		softly.assertThat(ep.synopsis).isEqualTo("SYNOPSIS");
		softly.assertAll();
	}
}
