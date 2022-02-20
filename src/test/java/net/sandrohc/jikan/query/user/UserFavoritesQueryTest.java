/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class UserFavoritesQueryTest extends QueryTest {

	@Test
	void fetchUserFavorites() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/favorites", "users/getUserFavorites.json");

		/* Act */
		UserFavoritesQuery query = jikan.query().user().favorites("USER_NAME");
		UserFavorites favorites = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/favorites");

		// Favorites
		assertThat(favorites).isNotNull();

		// Favorites - Anime
		softly = new SoftAssertions();
		softly.assertThat(favorites.anime).hasSize(1);
		softly.assertThat(favorites.anime)
				.extracting(f -> f.malId, f -> f.url, f -> f.name, f -> f.type, f -> f.startYear)
				.containsExactlyInAnyOrder(
						tuple(467, "https://myanimelist.net/anime/467/Koukaku_Kidoutai__Stand_Alone_Complex", "Koukaku Kidoutai: Stand Alone Complex", AnimeType.TV, 2002)
				);
		softly.assertAll();

		// Favorites - Manga
		softly = new SoftAssertions();
		softly.assertThat(favorites.manga).hasSize(1);
		softly.assertThat(favorites.manga)
				.extracting(f -> f.malId, f -> f.url, f -> f.name, f -> f.type, f -> f.startYear)
				.containsExactlyInAnyOrder(
						tuple(1315, "https://myanimelist.net/manga/1315/Boogiepop_Series", "Boogiepop Series", MangaType.UNKNOWN, 1998)
				);
		softly.assertAll();

		// Favorites - Character
		softly = new SoftAssertions();
		softly.assertThat(favorites.characters).hasSize(1);
		softly.assertThat(favorites.characters)
				.extracting(f -> f.malId, f -> f.url, f -> f.name)
				.containsExactlyInAnyOrder(
						tuple(4808, "https://myanimelist.net/character/4808/Tachikoma", "Tachikoma")
				);
		softly.assertAll();

		// Favorites - Person
		softly = new SoftAssertions();
		softly.assertThat(favorites.people).hasSize(1);
		softly.assertThat(favorites.people)
				.extracting(f -> f.malId, f -> f.url, f -> f.name)
				.containsExactlyInAnyOrder(
						tuple(4097, "https://myanimelist.net/people/4097/Mamoru_Oshii", "Oshii, Mamoru")
				);
		softly.assertAll();
	}
}
