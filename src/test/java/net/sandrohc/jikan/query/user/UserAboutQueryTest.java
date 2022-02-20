/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class UserAboutQueryTest extends QueryTest {

	@Test
	void fetchUserAbout() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/about", "users/getUserAbout.json");

		/* Act */
		UserAboutQuery query = jikan.query().user().about("USER_NAME");
		UserAbout about = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/about");

		// About
		softly = new SoftAssertions();
		softly.assertThat(about).isNotNull();
		softly.assertThat(about.toString()).isNotNull();
		softly.assertThat(about.about).startsWith("<span style=\"font-size: 150%;\"><a href=\"https://myanimelist.net/reviews.php?id=434781\" rel=\"nofollow\">Demon Slayer Season 2 Review");
		softly.assertThat(about.about).hasSize(36696);
		softly.assertAll();
	}
}
