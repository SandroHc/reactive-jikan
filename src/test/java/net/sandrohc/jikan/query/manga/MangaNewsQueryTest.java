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
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaNewsQueryTest extends RequestTest {

	@Test
	void fetchMangaNews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/news", 1, "manga/getMangaNews.json");

		/* Act */
		MangaNewsQuery query = jikan.query().manga().news(23390);
		Collection<NewsArticle> newsArticles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/news");

		// News Articles
		assertThat(newsArticles).isNotNull();
		assertThat(newsArticles).hasSize(1);

		NewsArticle article = newsArticles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(article.toString()).isNotNull();
		softly.assertThat(article.url).isEqualTo("https://myanimelist.net/news/60161703");
		softly.assertThat(article.title).isEqualTo("North American Anime & Manga Releases for July");
		softly.assertThat(article.date).isEqualTo(LocalDate.of(2020, Month.JULY, 15).atTime(15, 39).atOffset(ZoneOffset.UTC));
		softly.assertThat(article.authorUsername).isEqualTo("ImperfectBlue");
		softly.assertThat(article.authorUrl).isEqualTo("AUTHOR URL");
		softly.assertThat(article.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1850747");
		softly.assertThat(article.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertThat(article.comments).isEqualTo(0);
		softly.assertThat(article.excerpt).isEqualTo("Here are the North American anime & manga releases...");
		softly.assertAll();
	}
}
