/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeEpisodeQueryTest extends RequestTest {

	@Test
	void fetchEpisodeDetails() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/episodes/1", 1, "anime/getAnimeEpisodeById.json");

		/* Act */
		AnimeEpisodeQuery query = jikan.query().anime().episode(11757, 1);
		AnimeEpisode episode = query.execute().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/episodes/1");

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(episode).isNotNull();
		softly.assertThat(episode.malId).isEqualTo(1);
		softly.assertThat(episode.url).isEqualTo("DUMMY");
		softly.assertThat(episode.title).isEqualTo("DUMMY");
		softly.assertThat(episode.titleJapanese).isEqualTo("DUMMY");
		softly.assertThat(episode.titleRomanji).isEqualTo("DUMMY");
		softly.assertThat(episode.duration).isEqualTo(1);
		softly.assertThat(episode.aired).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(episode.filler).isFalse();
		softly.assertThat(episode.recap).isFalse();
		softly.assertThat(episode.forumUrl).isEqualTo("DUMMY");
		softly.assertThat(episode.synopsis).isEqualTo("DUMMY");
		softly.assertAll();
	}
}
