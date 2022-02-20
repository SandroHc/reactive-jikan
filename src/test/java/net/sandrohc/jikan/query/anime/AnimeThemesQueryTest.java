/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimeThemesQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
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
		softly.assertThat(themes.openings)
				.extracting(s -> s.number, s -> s.name, s -> s.artist, s -> s.episodes)
				.containsExactly(
						tuple(1, "crossing field", "LiSA", Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)),
						tuple(2, "INNOCENCE", "Aoi Eir", Arrays.asList(15, 16, 17, 18, 19, 20, 21, 22, 23, 24))
				);
		softly.assertThat(themes.endings)
				.extracting(s -> s.number, s -> s.name, s -> s.artist, s -> s.episodes)
				.containsExactly(
						tuple(1, "Yume Sekai (ユメセカイ)", "Haruka Tomatsu", Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)),
						tuple(2, "Overfly", "Luna Haruna", Arrays.asList(15, 16, 17, 18, 19, 20, 21, 22, 23, 24)),
						tuple(3, "crossing field", "LiSA", Collections.singletonList(25))
				);
		softly.assertAll();
	}
}
