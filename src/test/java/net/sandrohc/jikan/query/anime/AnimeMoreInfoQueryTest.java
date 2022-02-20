/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeMoreInfoQueryTest extends QueryTest {

	@Test
	void fetchAnimeMoreInfo() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/moreinfo", "anime/getAnimeMoreInfo.json");

		/* Act */
		AnimeMoreInfoQuery query = jikan.query().anime().moreInfo(11757);
		MoreInfo moreInfo = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/moreinfo");

		// More Info
		softly = new SoftAssertions();
		softly.assertThat(moreInfo).isNotNull();
		softly.assertThat(moreInfo.toString()).isNotNull();
		softly.assertThat(moreInfo.moreInfo).isNull();
		softly.assertAll();
	}
}
