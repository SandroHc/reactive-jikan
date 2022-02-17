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
	void fetchEpisodes() throws JikanInvalidArgumentException, JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/episodes", 1, "anime/getAnimeEpisodes.json");

		/* Act */
		AnimeEpisodesQuery query = jikan.query().anime().episodes(11757);
		List<AnimeEpisode> episodes = query.page(1).execute().collectList().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/episodes");

		assertThat(episodes).isNotNull();

		Iterator<AnimeEpisode> episodesIt = episodes.iterator();

		AnimeEpisode ep1 = episodesIt.next();
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(ep1.toString()).isNotNull();
		softly.assertThat(ep1.malId).isEqualTo(1);
		softly.assertThat(ep1.url).isEqualTo("URL");
		softly.assertThat(ep1.title).isEqualTo("The World of Swords");
		softly.assertThat(ep1.titleJapanese).isEqualTo("The World of Swords");
		softly.assertThat(ep1.titleRomanji).isEqualTo("The World of Swords");
		softly.assertThat(ep1.duration).isEqualTo(1);
		softly.assertThat(ep1.aired).isEqualTo(LocalDateTime.of(2012, Month.JULY, 8, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(ep1.filler).isFalse();
		softly.assertThat(ep1.recap).isFalse();
		softly.assertThat(ep1.forumUrl).isEqualTo("URL");
		softly.assertThat(ep1.synopsis).isEqualTo("SYNOPSIS");
		softly.assertAll();

		AnimeEpisode ep2 = episodesIt.next();
		softly = new SoftAssertions();
		softly.assertThat(ep2.toString()).isNotNull();
		softly.assertThat(ep2.malId).isEqualTo(2);
		softly.assertThat(ep2.url).isEqualTo("URL");
		softly.assertThat(ep2.title).isEqualTo("Beater");
		softly.assertThat(ep2.titleJapanese).isEqualTo("Beater");
		softly.assertThat(ep2.titleRomanji).isEqualTo("Beater");
		softly.assertThat(ep2.duration).isEqualTo(1);
		softly.assertThat(ep2.aired).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(ep2.filler).isFalse();
		softly.assertThat(ep2.recap).isFalse();
		softly.assertThat(ep2.forumUrl).isEqualTo("URL");
		softly.assertThat(ep2.synopsis).isEqualTo("SYNOPSIS");
		softly.assertAll();
	}
}
