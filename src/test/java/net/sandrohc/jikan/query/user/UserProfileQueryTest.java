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

public class UserProfileQueryTest extends QueryTest {

	@Test
	void fetchUserProfile() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME", "users/getUserProfile.json");

		/* Act */
		UserProfileQuery query = jikan.query().user().profile("USER_NAME");
		User user = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME");

		// Profile
		softly = new SoftAssertions();
		softly.assertThat(user).isNotNull();
		softly.assertThat(user.toString()).isNotNull();
		softly.assertThat(user.malId).isEqualTo(6384999);
		softly.assertThat(user.username).isEqualTo("USER_1");
		softly.assertThat(user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/6384999.jpg?t=1645315200");
		softly.assertThat(user.lastOnline).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 20).atTime(0, 2, 1).atOffset(ZoneOffset.UTC));
		softly.assertThat(user.gender).isEqualTo(UserGender.FEMALE);
		softly.assertThat(user.birthday).isEqualTo(LocalDate.of(2000, Month.JANUARY, 1).atTime(0, 2, 1).atOffset(ZoneOffset.UTC));
		softly.assertThat(user.location).isEqualTo("Japan");
		softly.assertThat(user.joined).isEqualTo(LocalDate.of(2017, Month.JULY, 9).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
