/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

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

public class MangaUserUpdatesQueryTest extends QueryTest {

	@Test
	void fetchMangaUserUpdates() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/userupdates", "manga/getMangaUserUpdates.json");

		/* Act */
		MangaUserUpdatesQuery query = jikan.query().manga().userUpdates(23390);
		Collection<UserUpdateWithUser> userUpdates = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/userupdates");

		// User Updates
		assertThat(userUpdates).isNotNull();
		assertThat(userUpdates).hasSize(1);

		UserUpdateWithUser userUpdate = userUpdates.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(userUpdate.toString()).isNotNull();
		softly.assertThat(userUpdate.user.username).isEqualTo("USER_1");
		softly.assertThat(userUpdate.user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(userUpdate.user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/6288511.jpg?t=1645123200");
		softly.assertThat(userUpdate.score).isEqualTo(9.0D);
		softly.assertThat(userUpdate.status).isEqualTo("Completed");
		softly.assertThat(userUpdate.seen).isEqualTo(141);
		softly.assertThat(userUpdate.total).isEqualTo(141);
		softly.assertThat(userUpdate.volumesRead).isEqualTo(34);
		softly.assertThat(userUpdate.volumesTotal).isEqualTo(34);
		softly.assertThat(userUpdate.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 17).atTime(18, 36, 11).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
