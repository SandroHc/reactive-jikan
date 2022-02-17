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
	void fetchStaff() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/staff", 1, "anime/getAnimeStaff.json");

		/* Act */
		AnimeStaffQuery query = jikan.query().anime().staff(11757);
		List<AnimeStaff> staff = query.execute().collectList().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/staff");

		assertThat(staff).isNotNull();
		Iterator<AnimeStaff> staffIt = staff.iterator();

		AnimeStaff staff1 = staffIt.next();
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(staff1.toString()).isNotNull();
		softly.assertThat(staff1.person.malId).isEqualTo(10801);
		softly.assertThat(staff1.person.name).isEqualTo("Itou, Tomohiko");
		softly.assertThat(staff1.positions).containsExactly("Director", "Episode Director", "Storyboard");
		softly.assertAll();

		AnimeStaff staff2 = staffIt.next();
		softly = new SoftAssertions();
		softly.assertThat(staff2.toString()).isNotNull();
		softly.assertThat(staff2.person.malId).isEqualTo(19775);
		softly.assertThat(staff2.person.name).isEqualTo("Fujiwara, Yoshiyuki");
		softly.assertThat(staff2.positions).containsExactly("Episode Director", "Storyboard", "2nd Key Animation", "Key Animation");
		softly.assertAll();

		assertThat(staffIt).isExhausted();
	}
}
