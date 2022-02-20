/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ClubMembersQueryTest extends QueryTest {

	@Test
	void fetchClubMembers() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/clubs/1/members", "clubs/getClubMembers.json");

		/* Act */
		ClubMembersQuery query = jikan.query().club().members(1);
		List<UserSimple> members = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/clubs/1/members");

		// Members
		softly = new SoftAssertions();
		softly.assertThat(members).isNotNull();
		softly.assertThat(members)
				.extracting(m -> m.username, m -> m.url, m -> m.images.jpg.imageUrl)
				.containsExactlyInAnyOrder(
						tuple("USER_1", "https://myanimelist.net/profile/USER_1", "https://myanimelist.net/images/userimages/4468359.jpg?t=1645044600")
				);
		softly.assertAll();

		for (UserSimple user : members) {
			assertThat(user).isNotNull();
			assertThat(user).isEqualTo(user);
			assertThat(user).isEqualTo(SerializationUtils.clone(user));
			assertThat(user).isNotEqualTo(new Object());
			assertThat(user).hasSameHashCodeAs(SerializationUtils.clone(user));
		}
	}
}
