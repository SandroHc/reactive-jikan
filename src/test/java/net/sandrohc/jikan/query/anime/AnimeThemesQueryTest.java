/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeThemesQueryTest extends QueryTest {

	@Test
	void fetchAnimeThemes() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/themes", "anime/getAnimeThemes.json");

		/* Act */
		AnimeThemesQuery query = jikan.query().anime().themes(11757);
		AnimeThemes themes = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/themes");

		// Themes
		assertThat(themes).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(themes.toString()).isNotNull();
		softly.assertThat(themes.openings).containsExactly(
				"1: \"crossing field\" by LiSA (eps 2-14)",
				"2: \"INNOCENCE\" by Aoi Eir (eps 15-24)"
		);
		softly.assertThat(themes.endings).containsExactly(
				"1: \"Yume Sekai (ユメセカイ)\" by Haruka Tomatsu (eps 2-14)",
				"2: \"Overfly\" by Luna Haruna (eps 15-24)",
				"3: \"crossing field\" by LiSA (eps 25)"
		);
		softly.assertAll();
	}
}
