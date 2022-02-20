/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.QueryTest;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class ClubQueryTest extends QueryTest {

	@Test
	void fetchClubDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/clubs/1", "clubs/getClubsById.json");

		/* Act */
		ClubQuery query = jikan.query().club().get(1);
		Club club = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/clubs/1");

		// Club
		assertThat(club).isNotNull();
		softly = new SoftAssertions();
		softly.assertThat(club.toString()).isNotNull();
		softly.assertThat(club.malId).isEqualTo(1);
		softly.assertThat(club.url).isEqualTo("https://myanimelist.net/clubs.php?cid=1");
		softly.assertThat(club.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/clubs/16/222057.jpg");
		softly.assertThat(club.name).isEqualTo("Cowboy Bebop");
		softly.assertThat(club.members).isEqualTo(1379);
		softly.assertThat(club.category).isEqualTo(ClubCategory.ANIME);
		softly.assertThat(club.created).isEqualTo(LocalDateTime.of(2007, Month.MARCH, 29, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(club.access).isEqualTo(ClubType.PUBLIC);
		softly.assertAll();

		assertThat(club).isNotNull();
		assertThat(club).isEqualTo(club);
		assertThat(club).isEqualTo(SerializationUtils.clone(club));
		assertThat(club).isNotEqualTo(new Object());
		assertThat(club).hasSameHashCodeAs(SerializationUtils.clone(club));
	}
}
