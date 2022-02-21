/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeVideosQueryTest extends QueryTest {

	@Test
	void fetchAnimeVideos() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/videos", "anime/getAnimeVideos.json");

		/* Act */
		AnimeVideosQuery query = jikan.query().anime().videos(11757);
		AnimeVideos videos = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/videos");

		// Videos
		softly = new SoftAssertions();
		softly.assertThat(videos).isNotNull();
		softly.assertThat(videos.toString()).isNotNull();
		softly.assertThat(videos.promo).isNotNull();
		softly.assertThat(videos.episodes).isNotNull();
		softly.assertAll();

		// Promo Videos
		Promo promo = videos.promo.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(promo.toString()).isNotNull();
		softly.assertThat(promo.title).isEqualTo("PV English dub version");
		softly.assertThat(promo.trailer.youtubeId).isEqualTo("6ohYYtxfDCg");
		softly.assertThat(promo.trailer.url).isEqualTo("https://www.youtube.com/watch?v=6ohYYtxfDCg");
		softly.assertThat(promo.trailer.embedUrl).isEqualTo("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(promo.trailer.images.imageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/default.jpg");
		softly.assertAll();

		// Episode Videos
		AnimeVideosEpisode episode = videos.episodes.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(episode.toString()).isNotNull();
		softly.assertThat(episode.title).isEqualTo("The World Seed");
		softly.assertThat(episode.episode).isEqualTo("Episode 25");
		softly.assertThat(episode.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online/episode/25");
		softly.assertThat(episode.images.jpg.imageUrl).isNull();
		softly.assertAll();
	}
}
