/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeMoreInfoQueryTest extends RequestTest {

	@Test
	void fetchAnimeMoreInfo() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/moreinfo", 1, "anime/getAnimeMoreInfo.json");

		/* Act */
		AnimeMoreInfoQuery query = jikan.query().anime().moreInfo(11757);
		MoreInfo moreInfo = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/moreinfo");

		// More Info
		softly = new SoftAssertions();
		softly.assertThat(moreInfo).isNotNull();
		softly.assertThat(moreInfo.toString()).isNotNull();
		softly.assertThat(moreInfo.moreInfo).isNull();
		softly.assertAll();
	}
}
