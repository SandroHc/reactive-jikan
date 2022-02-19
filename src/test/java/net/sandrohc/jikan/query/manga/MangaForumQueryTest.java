/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaForumQueryTest extends RequestTest {

	void fetchMangaForum() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/forum", "manga/getMangaTopics.json");

		/* Act */
		MangaForumQuery query = jikan.query().manga().forum(23390);
		Collection<ForumTopic> forumTopics = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/forum");

		// Topics
		assertThat(forumTopics).isNotNull();
		assertThat(forumTopics).hasSize(1);

		ForumTopic topic = forumTopics.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(topic.toString()).isNotNull();
		softly.assertThat(topic.malId).isEqualTo(1731050);
		softly.assertThat(topic.url).isEqualTo("https://myanimelist.net/forum/?topicid=1731050");
		softly.assertThat(topic.title).isEqualTo("Kimetsu no Yaiba Chapter 110 Discussion");
		softly.assertThat(topic.date).isEqualTo(LocalDate.of(2020, Month.JULY, 15).atTime(15, 39).atOffset(ZoneOffset.UTC));
		softly.assertThat(topic.authorUsername).isEqualTo("AUTHOR");
		softly.assertThat(topic.authorUrl).isEqualTo("AUTHOR URL");
		softly.assertThat(topic.comments).isEqualTo(8);
		softly.assertThat(topic.lastComment)
				.extracting(c -> c.url, c -> c.authorUsername, c -> c.authorUrl, c -> c.date)
				.containsExactlyInAnyOrder(
						tuple("URL", "USER", "URL", LocalDate.of(2020, Month.JULY, 15).atTime(15, 39).atOffset(ZoneOffset.UTC))
				);
		softly.assertAll();
	}
}
