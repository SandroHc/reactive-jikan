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

public class AnimeThemesQueryTest extends RequestTest {

	@Test
	void fetchThemes() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/anime/11757/themes", 1, "anime/getAnimeThemes.json");

		/* Act */
		AnimeThemesQuery query = jikan.query().anime().themes(11757);
		AnimeThemes themes = query.execute().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/themes");

		// Themes
		assertThat(themes).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(themes.toString()).isNotNull();
		softly.assertThat(themes.openings).containsExactly("OP");
		softly.assertThat(themes.endings).containsExactly("ED");
		softly.assertAll();
	}
}
