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

public class ClubStaffQueryTest extends RequestTest {

	@Test
	void fetchClubStaff() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/clubs/1/staff", 1, "clubs/getClubStaff.json");

		/* Act */
		ClubStaffQuery query = jikan.query().club().staff(1);
		List<UserSimple> staff = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/clubs/1/staff");

		// Related
		softly = new SoftAssertions();
		softly.assertThat(staff).isNotNull();
		softly.assertThat(staff).hasSize(1);
		softly.assertThat(staff)
				.extracting(r -> r.username, r -> r.url, r -> r.images.jpg.imageUrl)
				.containsExactly(
						tuple("NAME", "URL", "https://myanimelist.net/anime/20")
				);
		softly.assertAll();

		for (UserSimple entity : staff) {
			assertThat(entity).isNotNull();
			assertThat(entity).isEqualTo(entity);
			assertThat(entity).isEqualTo(SerializationUtils.clone(entity));
			assertThat(entity).isNotEqualTo(new Object());
			assertThat(entity).hasSameHashCodeAs(SerializationUtils.clone(entity));
		}
	}
}
