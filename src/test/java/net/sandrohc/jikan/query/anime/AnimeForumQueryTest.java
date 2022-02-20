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
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeForumQueryTest extends QueryTest {

	@Test
	void fetchAnimeForum() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/forum", "anime/getAnimeForum.json",
				Parameter.param("filter", "all"));

		/* Act */
		AnimeForumQuery query = jikan.query().anime().forum(11757).type(ForumTopicType.ALL);
		Collection<ForumTopic> forumTopics = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/forum?filter=all");

		// Topics
		assertThat(forumTopics).isNotNull();
		assertThat(forumTopics).hasSize(1);

		ForumTopic topic = forumTopics.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(topic.toString()).isNotNull();
		softly.assertThat(topic.malId).isEqualTo(535278);
		softly.assertThat(topic.url).isEqualTo("https://myanimelist.net/forum/?topicid=535278");
		softly.assertThat(topic.title).isEqualTo("Sword Art Online Episode 25 Discussion");
		softly.assertThat(topic.date).isEqualTo(LocalDate.of(2012, Month.DECEMBER, 22).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(topic.authorUsername).isEqualTo("USERNAME_1");
		softly.assertThat(topic.authorUrl).isEqualTo("https://myanimelist.net/profile/USERNAME_1");
		softly.assertThat(topic.comments).isEqualTo(1);
		softly.assertThat(topic.lastComment.url).isEqualTo("https://myanimelist.net/forum/?topicid=535278&goto=lastpost");
		softly.assertThat(topic.lastComment.authorUsername).isEqualTo("USERNAME_2");
		softly.assertThat(topic.lastComment.authorUrl).isEqualTo("https://myanimelist.net/profile/USERNAME_2");
		softly.assertThat(topic.lastComment.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 16).atTime(9, 23, 45).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
