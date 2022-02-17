/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimeForumQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchForum() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/forum", 1, "anime/getAnimeForum.json");

		/* Act */
		AnimeForumQuery query = jikan.query().anime().forum(11757);
		Collection<ForumTopic> forumTopics = query.execute().collectList().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/forum");

		// Topics
		SoftAssertions softly;
		assertThat(forumTopics).isNotNull();
		Iterator<ForumTopic> topicsIt = forumTopics.iterator();

		ForumTopic t1 = topicsIt.next();
		softly = new SoftAssertions();
		softly.assertThat(t1.toString()).isNotNull();
		softly.assertThat(t1.malId).isEqualTo(1797514);
		softly.assertThat(t1.url).isEqualTo("https://myanimelist.net/forum/?topicid=1797514");
		softly.assertThat(t1.title).isEqualTo("Is Kirito an UNLIKABLE character?");
		softly.assertThat(t1.date).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(t1.authorUsername).isEqualTo("AUTHOR");
		softly.assertThat(t1.authorUrl).isEqualTo("AUTHOR");
		softly.assertThat(t1.comments).isEqualTo(54);
		softly.assertThat(t1.lastComment)
				.extracting(c -> c.url, c -> c.authorUsername, c -> c.date)
				.containsExactly(
						tuple("URL", "AUTHOR", LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC))
				);
		softly.assertAll();

		assertThat(topicsIt).isExhausted();
	}
}
