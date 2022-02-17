/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeStaffQueryTest extends RequestTest {

	@Test
	void fetchAnimeStaff() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/staff", 1, "anime/getAnimeStaff.json");

		/* Act */
		AnimeStaffQuery query = jikan.query().anime().staff(11757);
		List<AnimeStaff> staffList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/staff");

		// Staff
		assertThat(staffList).isNotNull();
		assertThat(staffList).hasSize(1);

		AnimeStaff staff = staffList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(staff.toString()).isNotNull();
		softly.assertThat(staff.person.malId).isEqualTo(10801);
		softly.assertThat(staff.person.name).isEqualTo("Itou, Tomohiko");
		softly.assertThat(staff.positions).containsExactly("Director", "Episode Director", "Storyboard");
		softly.assertAll();
	}
}
