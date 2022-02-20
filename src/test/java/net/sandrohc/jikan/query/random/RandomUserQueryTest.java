/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomUserQueryTest extends QueryTest {

	@Test
	void fetchRandomUser() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/random/users", "random/getRandomUsers.json");

		/* Act */
		RandomUserQuery query = jikan.query().random().user();
		User result = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/random/users");

		// Result
		assertThat(result).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(10933025);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(result.username).isEqualTo("USER_1");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/10933025.jpg?t=1623223200");
		softly.assertAll();
	}
}
