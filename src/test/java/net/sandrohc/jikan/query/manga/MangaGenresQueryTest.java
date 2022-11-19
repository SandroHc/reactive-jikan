/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.QueryTest;
import net.sandrohc.jikan.query.genre.GenreMangaQuery;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaGenresQueryTest extends QueryTest {

	@Test
	void fetchMangaGenres() throws JikanQueryException, JikanUrlException, JsonProcessingException {
		/* Arrange */
		mockFromFile(mockServer, "/genres/manga", "genres/getMangaGenres.json",
				Parameter.param("filter", "genres"));

		/* Act */
		GenreMangaQuery query = jikan.query().manga().genres().type(GenreType.GENRES);
		Collection<GenreWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/genres/manga?filter=genres");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(78);

		GenreWithCount result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(1);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/genre/1/Action");
		softly.assertThat(result.name).isEqualTo("Action");
		softly.assertThat(result.count).isEqualTo(8437);
		softly.assertAll();

		// Check for new genres
		assertThat(results)
				.extracting(MalEntity::getMalId, Entity::getName)
				.containsExactlyInAnyOrder(
						tuple(1, "Action"),
						tuple(2, "Adventure"),
						tuple(5, "Avant Garde"),
						tuple(46, "Award Winning"),
						tuple(28, "Boys Love"),
						tuple(4, "Comedy"),
						tuple(8, "Drama"),
						tuple(10, "Fantasy"),
						tuple(26, "Girls Love"),
						tuple(47, "Gourmet"),
						tuple(14, "Horror"),
						tuple(7, "Mystery"),
						tuple(22, "Romance"),
						tuple(24, "Sci-Fi"),
						tuple(36, "Slice of Life"),
						tuple(30, "Sports"),
						tuple(37, "Supernatural"),
						tuple(45, "Suspense"),
						tuple(9, "Ecchi"),
						tuple(49, "Erotica"),
						tuple(12, "Hentai"),
						tuple(50, "Adult Cast"),
						tuple(51, "Anthropomorphic"),
						tuple(52, "CGDCT"),
						tuple(53, "Childcare"),
						tuple(54, "Combat Sports"),
						tuple(44, "Crossdressing"),
						tuple(55, "Delinquents"),
						tuple(39, "Detective"),
						tuple(56, "Educational"),
						tuple(57, "Gag Humor"),
						tuple(58, "Gore"),
						tuple(35, "Harem"),
						tuple(59, "High Stakes Game"),
						tuple(13, "Historical"),
						tuple(60, "Idols (Female)"),
						tuple(61, "Idols (Male)"),
						tuple(62, "Isekai"),
						tuple(63, "Iyashikei"),
						tuple(64, "Love Polygon"),
						tuple(65, "Magical Sex Shift"),
						tuple(66, "Mahou Shoujo"),
						tuple(17, "Martial Arts"),
						tuple(18, "Mecha"),
						tuple(67, "Medical"),
						tuple(68, "Memoir"),
						tuple(38, "Military"),
						tuple(19, "Music"),
						tuple(6, "Mythology"),
						tuple(69, "Organized Crime"),
						tuple(70, "Otaku Culture"),
						tuple(20, "Parody"),
						tuple(71, "Performing Arts"),
						tuple(72, "Pets"),
						tuple(40, "Psychological"),
						tuple(3, "Racing"),
						tuple(73, "Reincarnation"),
						tuple(74, "Reverse Harem"),
						tuple(75, "Romantic Subtext"),
						tuple(21, "Samurai"),
						tuple(23, "School"),
						tuple(76, "Showbiz"),
						tuple(29, "Space"),
						tuple(11, "Strategy Game"),
						tuple(31, "Super Power"),
						tuple(77, "Survival"),
						tuple(78, "Team Sports"),
						tuple(79, "Time Travel"),
						tuple(32, "Vampire"),
						tuple(80, "Video Game"),
						tuple(81, "Villainess"),
						tuple(82, "Visual Arts"),
						tuple(48, "Workplace"),
						tuple(42, "Josei"),
						tuple(15, "Kids"),
						tuple(41, "Seinen"),
						tuple(25, "Shoujo"),
						tuple(27, "Shounen")
				);

		// Check if there is an enum value for all genres
		for (GenreWithCount genre : results) {
			Genre deserializedGenre = jikan.objectMapper.readValue("\"" + genre.getName() + "\"", Genre.class);

			softly = new SoftAssertions();
			softly.assertThat(deserializedGenre).as("failed to deserialize: " + genre).isNotNull();
			softly.assertThat(deserializedGenre).as("no enum match: " + genre).isNotEqualTo(Genre.UNKNOWN);
			softly.assertThat(deserializedGenre.mangaId()).as("mismatched ID: " + genre).isEqualTo(genre.malId);
			softly.assertThat(deserializedGenre.displayName()).as("mismatched name: " + genre).isEqualTo(genre.name);
			softly.assertAll();
		}
	}
}
