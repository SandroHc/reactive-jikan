/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.watch;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class WatchRecentPromosQueryTest extends QueryTest {

	@Test
	void fetchWatchRecentPromos() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/watch/promos", "watch/getWatchRecentPromos.json");

		/* Act */
		WatchRecentPromosQuery query = jikan.query().watch().recentPromotionals().page(1).limit(2);
		Collection<PromoWithEntry> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/watch/promos?page=1&limit=2");

		// Result
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		PromoWithEntry promo = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(promo.toString()).isNotNull();
		softly.assertThat(promo.title).isEqualTo("Character PV (Meguru Bachira ver.)");
		softly.assertThat(promo.trailer.youtubeId).isEqualTo("1-yxNeFzp6E");
		softly.assertThat(promo.trailer.url).isEqualTo("https://www.youtube.com/watch?v=1-yxNeFzp6E");
		softly.assertThat(promo.trailer.embedUrl).isEqualTo("https://www.youtube.com/embed/1-yxNeFzp6E?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(promo.trailer.images.imageUrl).isEqualTo("https://img.youtube.com/vi/1-yxNeFzp6E/default.jpg");
		softly.assertThat(promo.entry.malId).isEqualTo(49596);
		softly.assertThat(promo.entry.url).isEqualTo("https://myanimelist.net/anime/49596/Blue_Lock");
		softly.assertThat(promo.entry.name).isEqualTo("Blue Lock");
		softly.assertThat(promo.entry.images.jpg.imageUrl).isEqualTo( "https://cdn.myanimelist.net/images/anime/1418/117081.jpg");
		softly.assertAll();
	}
}
