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
import net.sandrohc.jikan.test.RequestTest;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ClubMembersQueryTest extends RequestTest {

	@Test
	void fetchMembers() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/clubs/1/members", 1, "clubs/getClubMembers.json");

		/* Act */
		ClubMembersQuery query = jikan.query().club().members(1);
		List<UserSimple> members = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/clubs/1/members");

		// Members
		assertThat(members).isNotNull();
		assertThat(members)
				.extracting(m -> m.username, m -> m.url, m -> m.images.jpg.imageUrl)
				.containsExactly(
						tuple("USER", "URL", "IMAGE")
				);

		for (UserSimple user : members) {
			assertThat(user).isNotNull();
			assertThat(user).isEqualTo(user);
			assertThat(user).isEqualTo(SerializationUtils.clone(user));
			assertThat(user).isNotEqualTo(new Object());
			assertThat(user).hasSameHashCodeAs(SerializationUtils.clone(user));
		}
	}
}
