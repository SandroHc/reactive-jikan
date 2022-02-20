/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class UserUpdatesQueryTest extends QueryTest {

	@Test
	void fetchUserUpdates() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/userupdates", "users/getUserUpdates.json");

		/* Act */
		UserUpdatesQuery query = jikan.query().user().updates("USER_NAME");
		UserUpdates updates = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/userupdates");

		// Updates
		softly = new SoftAssertions();
		softly.assertThat(updates).isNotNull();
		softly.assertThat(updates.toString()).isNotNull();
		softly.assertThat(updates.anime).hasSize(1);
		softly.assertThat(updates.manga).hasSize(1);
		softly.assertAll();

		// Updates - Anime
		UserUpdateWithEntry animeUpdate = updates.anime.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(animeUpdate.score).isEqualTo(10.0D);
		softly.assertThat(animeUpdate.status).isEqualTo("Watching");
		softly.assertThat(animeUpdate.seen).isEqualTo(0);
		softly.assertThat(animeUpdate.total).isEqualTo(0);
		softly.assertThat(animeUpdate.volumesRead).isEqualTo(0);
		softly.assertThat(animeUpdate.volumesTotal).isEqualTo(0);
		softly.assertThat(animeUpdate.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 17).atTime(7, 45).atOffset(ZoneOffset.UTC));
		softly.assertThat(animeUpdate.entry.malId).isEqualTo(21);
		softly.assertThat(animeUpdate.entry.url).isEqualTo("https://myanimelist.net/anime/21/One_Piece");
		softly.assertThat(animeUpdate.entry.name).isEqualTo("One Piece");
		softly.assertThat(animeUpdate.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/6/73245.jpg?s=863b16ac7b76f7783ac11f165012ea8c");
		softly.assertAll();

		// Updates - Manga
		UserUpdateWithEntry mangaUpdate = updates.manga.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(mangaUpdate.score).isEqualTo(9.0D);
		softly.assertThat(mangaUpdate.status).isEqualTo("Completed");
		softly.assertThat(mangaUpdate.seen).isEqualTo(9);
		softly.assertThat(mangaUpdate.total).isEqualTo(9);
		softly.assertThat(mangaUpdate.volumesRead).isEqualTo(0);
		softly.assertThat(mangaUpdate.volumesTotal).isEqualTo(0);
		softly.assertThat(mangaUpdate.date).isEqualTo(LocalDate.of(2021, Month.DECEMBER, 10).atTime(7, 53).atOffset(ZoneOffset.UTC));
		softly.assertThat(mangaUpdate.entry.malId).isEqualTo(17465);
		softly.assertThat(mangaUpdate.entry.url).isEqualTo("https://myanimelist.net/manga/17465/Omoide_Emanon");
		softly.assertThat(mangaUpdate.entry.name).isEqualTo("Omoide Emanon");
		softly.assertThat(mangaUpdate.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/56581.jpg?s=7794a169b8dc1765d0e464ad79f629c8");
		softly.assertAll();
	}
}
