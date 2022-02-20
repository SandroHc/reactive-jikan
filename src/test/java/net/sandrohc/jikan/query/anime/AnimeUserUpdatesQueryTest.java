/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeUserUpdatesQueryTest extends QueryTest {

	@Test
	void fetchAnimeUserUpdates() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/userupdates", "anime/getAnimeUserUpdates.json");

		/* Act */
		AnimeUserUpdatesQuery query = jikan.query().anime().userUpdates(11757);
		Collection<UserUpdateWithUser> userUpdates = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/userupdates");

		// User Updates
		assertThat(userUpdates).isNotNull();
		assertThat(userUpdates).hasSize(1);

		UserUpdateWithUser userUpdate = userUpdates.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(userUpdate.toString()).isNotNull();
		softly.assertThat(userUpdate.user.username).isEqualTo("USERNAME_1");
		softly.assertThat(userUpdate.user.url).isEqualTo("https://myanimelist.net/profile/USERNAME_1");
		softly.assertThat(userUpdate.user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/14549589.jpg?t=1645014600");
		softly.assertThat(userUpdate.score).isEqualTo(7.0D);
		softly.assertThat(userUpdate.status).isEqualTo("Completed");
		softly.assertThat(userUpdate.seen).isEqualTo(25);
		softly.assertThat(userUpdate.total).isEqualTo(25);
		softly.assertThat(userUpdate.volumesRead).isEqualTo(0);
		softly.assertThat(userUpdate.volumesTotal).isEqualTo(0);
		softly.assertThat(userUpdate.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 16).atTime(12, 24, 41).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
