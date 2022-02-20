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
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaForumQueryTest extends QueryTest {

	@Test
	void fetchMangaForum() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/forum", "manga/getMangaTopics.json",
				Parameter.param("filter", "all"));

		/* Act */
		MangaForumQuery query = jikan.query().manga().forum(23390).type(ForumTopicType.ALL);
		Collection<ForumTopic> forumTopics = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/forum?filter=all");

		// Topics
		assertThat(forumTopics).isNotNull();
		assertThat(forumTopics).hasSize(1);

		ForumTopic topic = forumTopics.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(topic.toString()).isNotNull();
		softly.assertThat(topic.malId).isEqualTo(1986037);
		softly.assertThat(topic.url).isEqualTo("https://myanimelist.net/forum/?topicid=1986037");
		softly.assertThat(topic.title).isEqualTo("Will Anime Only Watchers be able to digest the abomination of the ending?");
		softly.assertThat(topic.date).isEqualTo(LocalDate.of(2022, Month.JANUARY, 10).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(topic.authorUsername).isEqualTo("USER_1");
		softly.assertThat(topic.authorUrl).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(topic.comments).isEqualTo(58);
		softly.assertThat(topic.lastComment.url).isEqualTo("https://myanimelist.net/forum/?topicid=1986037&goto=lastpost");
		softly.assertThat(topic.lastComment.authorUsername).isEqualTo("USER_2");
		softly.assertThat(topic.lastComment.authorUrl).isEqualTo("https://myanimelist.net/profile/USER_2");
		softly.assertThat(topic.lastComment.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 17).atTime(13, 38, 21).atOffset(ZoneOffset.UTC));
		softly.assertAll();
	}
}
