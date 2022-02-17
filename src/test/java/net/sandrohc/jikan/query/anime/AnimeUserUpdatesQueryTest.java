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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeUserUpdatesQueryTest extends RequestTest {

	@Test
	void fetchUserUpdates() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/userupdates", 1, "anime/getAnimeUserUpdates.json");

		/* Act */
		AnimeUserUpdatesQuery query = jikan.query().anime().userUpdates(11757);
		Collection<UserUpdateWithUser> userUpdates = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/userupdates");

		// User Updates
		assertThat(userUpdates).isNotNull();
		Iterator<UserUpdateWithUser> usersIt = userUpdates.iterator();

		UserUpdateWithUser userUpdate = usersIt.next();
		softly = new SoftAssertions();
		softly.assertThat(userUpdate.toString()).isNotNull();
		softly.assertThat(userUpdate.user.username).isEqualTo("Vincent1307");
		softly.assertThat(userUpdate.user.url).isEqualTo("https://myanimelist.net/profile/Vincent1307");
		softly.assertThat(userUpdate.user.images.jpg.imageUrl).isEqualTo("https://myanimelist.net/profile/Vincent1307");
		softly.assertThat(userUpdate.score).isEqualTo(8.5F);
		softly.assertThat(userUpdate.status).isEqualTo("Completed");
		softly.assertThat(userUpdate.seen).isEqualTo(25);
		softly.assertThat(userUpdate.total).isEqualTo(25);
		softly.assertThat(userUpdate.volumesRead).isNull();
		softly.assertThat(userUpdate.volumesTotal).isNull();
		softly.assertThat(userUpdate.date).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertAll();

		assertThat(usersIt).isExhausted();
	}
}
