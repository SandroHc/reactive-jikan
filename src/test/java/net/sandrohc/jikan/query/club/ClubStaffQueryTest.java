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

public class ClubStaffQueryTest extends QueryTest {

	@Test
	void fetchClubStaff() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/clubs/1/staff", "clubs/getClubStaff.json");

		/* Act */
		ClubStaffQuery query = jikan.query().club().staff(1);
		List<UserSimple> staff = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/clubs/1/staff");

		// Related
		softly = new SoftAssertions();
		softly.assertThat(staff).isNotNull();
		softly.assertThat(staff).hasSize(1);
		softly.assertThat(staff)
				.extracting(r -> r.username, r -> r.url, r -> r != null && r.images != null && r.images.jpg != null ? r.images.jpg.imageUrl : null)
				.containsExactlyInAnyOrder(
						tuple("USER_1", "https://myanimelist.net/profile/USER_1", null)
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
