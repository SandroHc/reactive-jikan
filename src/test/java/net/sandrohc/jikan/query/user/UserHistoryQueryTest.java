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
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class UserHistoryQueryTest extends QueryTest {

	@Test
	void fetchUserHistory_anime() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/history", "users/getUserHistory_anime.json",
				Parameter.param("type", "anime"));

		/* Act */
		UserHistoryQuery query = jikan.query().user().history("USER_NAME").anime();
		Collection<UserHistoryEntry> history = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/history?type=anime");

		// History
		assertThat(history).isNotNull();
		assertThat(history).hasSize(1);

		UserHistoryEntry entry = history.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(entry.toString()).isNotNull();
		softly.assertThat(entry.entry.malId).isEqualTo(21);
		softly.assertThat(entry.entry.url).isEqualTo("https://myanimelist.net/anime/21");
		softly.assertThat(entry.entry.name).isEqualTo("One Piece");
		softly.assertThat(entry.entry.type).isEqualTo(Type.ANIME);
		softly.assertThat(entry.increment).isEqualTo(570);
		softly.assertThat(entry.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 17).atTime(7, 45).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}


	@Test
	void fetchUserHistory_manga() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/history", "users/getUserHistory_manga.json",
				Parameter.param("type", "manga"));

		/* Act */
		UserHistoryQuery query = jikan.query().user().history("USER_NAME").manga();
		Collection<UserHistoryEntry> history = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/history?type=manga");

		// History
		assertThat(history).isNotNull();
		assertThat(history).hasSize(1);

		UserHistoryEntry entry = history.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(entry.toString()).isNotNull();
		softly.assertThat(entry.entry.malId).isEqualTo(1);
		softly.assertThat(entry.entry.url).isEqualTo("https://myanimelist.net/manga/1");
		softly.assertThat(entry.entry.name).isEqualTo("Monster");
		softly.assertThat(entry.entry.type).isEqualTo(Type.MANGA);
		softly.assertThat(entry.increment).isEqualTo(570);
		softly.assertThat(entry.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 17).atTime(7, 45).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
