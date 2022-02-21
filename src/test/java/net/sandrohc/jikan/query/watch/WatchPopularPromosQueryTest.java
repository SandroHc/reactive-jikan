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

public class WatchPopularPromosQueryTest extends QueryTest {

	@Test
	void fetchWatchPopularPromos() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/watch/promos/popular", "watch/getWatchPopularPromos.json");

		/* Act */
		WatchPopularPromosQuery query = jikan.query().watch().popularPromotionals().page(1).limit(2);
		Collection<PromoWithEntry> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/watch/promos/popular?page=1&limit=2");

		// Result
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		PromoWithEntry promo = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(promo.toString()).isNotNull();
		softly.assertThat(promo.title).isEqualTo("PV 1");
		softly.assertThat(promo.trailer.youtubeId).isEqualTo("KKzmOh4SuBc");
		softly.assertThat(promo.trailer.url).isEqualTo("https://www.youtube.com/watch?v=KKzmOh4SuBc");
		softly.assertThat(promo.trailer.embedUrl).isEqualTo("https://www.youtube.com/embed/KKzmOh4SuBc?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(promo.trailer.images.imageUrl).isEqualTo("https://img.youtube.com/vi/KKzmOh4SuBc/default.jpg");
		softly.assertThat(promo.entry.malId).isEqualTo(16498);
		softly.assertThat(promo.entry.url).isEqualTo("https://myanimelist.net/anime/16498/Shingeki_no_Kyojin");
		softly.assertThat(promo.entry.name).isEqualTo("Shingeki no Kyojin");
		softly.assertThat(promo.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/10/47347.jpg");
		softly.assertAll();
	}
}
