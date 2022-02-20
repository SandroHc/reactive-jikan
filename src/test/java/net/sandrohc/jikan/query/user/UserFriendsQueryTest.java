/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

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

public class UserFriendsQueryTest extends QueryTest {

	@Test
	void fetchUserFriends() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/friends", "users/getUserFriends.json");

		/* Act */
		UserFriendsQuery query = jikan.query().user().friends("USER_NAME");
		Collection<UserFriend> friends = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/friends");

		// Friends
		assertThat(friends).isNotNull();
		assertThat(friends).hasSize(1);

		UserFriend friend = friends.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(friend.toString()).isNotNull();
		softly.assertThat(friend.user.username).isEqualTo("USER_1");
		softly.assertThat(friend.user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(friend.user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/4040761.jpg?t=1645315200");
		softly.assertThat(friend.lastOnline).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 19).atTime(23, 56, 55).atOffset(ZoneOffset.UTC));
		softly.assertThat(friend.friendsSince).isEqualTo(LocalDate.of(2021, Month.MARCH, 14).atTime(9, 13).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
