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

public class AnimeNewsQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeNews() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/news", 1, "anime/getAnimeNews.json");

		/* Act */
		AnimeNewsQuery query = jikan.query().anime().news(11757);
		Collection<NewsArticle> newsArticles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/news");

		// Articles
		assertThat(newsArticles).isNotNull();
		assertThat(newsArticles).hasSize(1);

		NewsArticle article = newsArticles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(article.toString()).isNotNull();
		softly.assertThat(article.malId).isEqualTo(1);
		softly.assertThat(article.url).isEqualTo("https://myanimelist.net/news/56114579");
		softly.assertThat(article.title).isEqualTo("Interview: Luna Haruna to Showcase Best Album at New York Anisong World Matsuri");
		softly.assertThat(article.date).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(article.authorUsername).isEqualTo("USERNAME_1");
		softly.assertThat(article.authorUrl).isEqualTo("USERNAME_1");
		softly.assertThat(article.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1748998");
		softly.assertThat(article.images.jpg.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.jpg.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.jpg.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.jpg.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.jpg.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.webp.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.webp.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.webp.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.webp.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.images.webp.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(article.comments).isEqualTo(5);
		softly.assertThat(article.excerpt).isEqualTo("Since making her international debut...");
		softly.assertAll();
	}
}
