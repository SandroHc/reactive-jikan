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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaUserUpdatesQueryTest extends RequestTest {

	@Test
	void fetchMangaUserUpdates() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/userupdates", 1, "manga/getMangaUserUpdates.json");

		/* Act */
		MangaUserUpdatesQuery query = jikan.query().manga().userUpdates(23390);
		Collection<UserUpdateWithUser> userUpdates = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/userupdates");

		// User Updates
		assertThat(userUpdates).isNotNull();
		assertThat(userUpdates).hasSize(1);

		UserUpdateWithUser userUpdate = userUpdates.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(userUpdate.toString()).isNotNull();
		softly.assertThat(userUpdate.user.username).isEqualTo("Vincent1307");
		softly.assertThat(userUpdate.user.url).isEqualTo("https://myanimelist.net/profile/Vincent1307");
		softly.assertThat(userUpdate.user.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertThat(userUpdate.score).isEqualTo(8.5F);
		softly.assertThat(userUpdate.status).isEqualTo("Completed");
		softly.assertThat(userUpdate.seen).isEqualTo(10);
		softly.assertThat(userUpdate.total).isEqualTo(10);
		softly.assertThat(userUpdate.volumesRead).isEqualTo(0);
		softly.assertThat(userUpdate.volumesTotal).isEqualTo(0);
		softly.assertThat(userUpdate.date).isEqualTo(LocalDate.of(2020, Month.JULY, 15).atTime(15, 39).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
