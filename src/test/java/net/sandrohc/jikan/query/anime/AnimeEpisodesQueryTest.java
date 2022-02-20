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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeEpisodesQueryTest extends QueryTest {

	@Test
	void fetchAnimeEpisodes() throws JikanInvalidArgumentException, JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/episodes", "anime/getAnimeEpisodes.json");

		/* Act */
		AnimeEpisodesQuery query = jikan.query().anime().episodes(11757).page(1);
		List<AnimeEpisode> episodes = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/episodes?page=1");

		// Episodes
		assertThat(episodes).isNotNull();
		assertThat(episodes).hasSize(1);

		AnimeEpisode ep = episodes.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(ep.toString()).isNotNull();
		softly.assertThat(ep.malId).isEqualTo(1);
		softly.assertThat(ep.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1");
		softly.assertThat(ep.title).isEqualTo("The World of Swords");
		softly.assertThat(ep.titleJapanese).isEqualTo("剣の世界");
		softly.assertThat(ep.titleRomanji).isEqualTo("Ken no Sekai");
		softly.assertThat(ep.duration).isNull();
		softly.assertThat(ep.aired).isEqualTo(LocalDateTime.of(2012, Month.JULY, 8, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(ep.filler).isFalse();
		softly.assertThat(ep.recap).isFalse();
		softly.assertThat(ep.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=459625");
		softly.assertThat(ep.synopsis).isNull();
		softly.assertAll();
	}
}
