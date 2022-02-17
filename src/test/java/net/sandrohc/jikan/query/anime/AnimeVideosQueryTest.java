/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeVideosQueryTest extends RequestTest {

	@Test
	void fetchVideos() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/videos", 1, "anime/getAnimeVideos.json");

		/* Act */
		AnimeVideosQuery query = jikan.query().anime().videos(11757);
		AnimeVideos videos = query.execute().block();

		/* Assert */
		SoftAssertions softly;
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/videos");

		// Videos
		softly = new SoftAssertions();
		softly.assertThat(videos).isNotNull();
		softly.assertThat(videos.toString()).isNotNull();
		softly.assertThat(videos.promo).isNotNull();
		softly.assertThat(videos.episodes).isNotNull();
		softly.assertAll();

		// Promo Videos
		AnimeVideosPromo promo = videos.promo.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(promo.toString()).isNotNull();
		softly.assertThat(promo.title).isEqualTo("PV English dub version");
		softly.assertThat(promo.trailer.youtubeId).isEqualTo("https://i.ytimg.com/vi/6ohYYtxfDCg/mqdefault.jpg");
		softly.assertThat(promo.trailer.url).isEqualTo("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(promo.trailer.embedUrl).isEqualTo("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(promo.trailer.images.imageUrl).isEqualTo("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertAll();

		// Episode Videos
		AnimeVideosEpisode episode = videos.episodes.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(episode.toString()).isNotNull();
		softly.assertThat(episode.title).isEqualTo("The World of Swords");
		softly.assertThat(episode.episode).isEqualTo("Episode 1");
		softly.assertThat(episode.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1");
		softly.assertThat(episode.images.jpg.imageUrl).isEqualTo("https://img1.ak.crunchyroll.com/i/spire1-tmb/018d02b49a25a58bfd8a64416bdb69b41341616322_large.jpg");
		softly.assertAll();
	}
}
