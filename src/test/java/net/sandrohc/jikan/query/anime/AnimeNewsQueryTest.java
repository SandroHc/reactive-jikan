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
	void fetchNews() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/news", 1, "anime/getAnimeNews.json");

		/* Act */
		AnimeNewsQuery query = jikan.query().anime().news(11757);
		Collection<NewsArticle> newsArticles = query.execute().collectList().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/news");

		// Articles
		SoftAssertions softly;
		assertThat(newsArticles).isNotNull();
		Iterator<NewsArticle> articlesIt = newsArticles.iterator();

		NewsArticle a1 = articlesIt.next();
		softly = new SoftAssertions();
		softly.assertThat(a1.toString()).isNotNull();
		softly.assertThat(a1.malId).isEqualTo(1);
		softly.assertThat(a1.url).isEqualTo("https://myanimelist.net/news/56114579");
		softly.assertThat(a1.title).isEqualTo("Interview: Luna Haruna to Showcase Best Album at New York Anisong World Matsuri");
		softly.assertThat(a1.date).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(a1.authorUsername).isEqualTo("USERNAME_1");
		softly.assertThat(a1.authorUrl).isEqualTo("USERNAME_1");
		softly.assertThat(a1.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1748998");
		softly.assertThat(a1.images.jpg.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.jpg.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.jpg.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.jpg.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.jpg.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.webp.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.webp.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.webp.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.webp.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.images.webp.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a1.comments).isEqualTo(5);
		softly.assertThat(a1.excerpt).isEqualTo("Since making her international debut...");
		softly.assertAll();

		NewsArticle a2 = articlesIt.next();
		softly = new SoftAssertions();
		softly.assertThat(a2.toString()).isNotNull();
		softly.assertThat(a2.malId).isEqualTo(2);
		softly.assertThat(a2.url).isEqualTo("https://myanimelist.net/news/50992876");
		softly.assertThat(a2.title).isEqualTo("North American Anime & Manga Releases for June");
		softly.assertThat(a2.date).isEqualTo(LocalDateTime.of(2012, Month.JULY, 15, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(a2.authorUsername).isEqualTo("Sakana-san");
		softly.assertThat(a2.authorUrl).isEqualTo("Sakana-san");
		softly.assertThat(a2.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1623111");
		softly.assertThat(a2.images.jpg.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.jpg.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.jpg.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.jpg.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.jpg.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.webp.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.webp.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.webp.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.webp.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.images.webp.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(a2.comments).isEqualTo(12);
		softly.assertThat(a2.excerpt).isEqualTo("Here are the North American anime & manga...");
		softly.assertAll();
	}
}
