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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeNewsQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeNews() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/news", "anime/getAnimeNews.json");

		/* Act */
		AnimeNewsQuery query = jikan.query().anime().news(11757);
		Collection<NewsArticle> newsArticles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/news");

		// Articles
		assertThat(newsArticles).isNotNull();
		assertThat(newsArticles).hasSize(1);

		NewsArticle article = newsArticles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(article.toString()).isNotNull();
		softly.assertThat(article.malId).isEqualTo(56114579);
		softly.assertThat(article.url).isEqualTo("https://myanimelist.net/news/56114579");
		softly.assertThat(article.title).isEqualTo("Interview: Luna Haruna to Showcase Best Album at New York Anisong World Matsuri");
		softly.assertThat(article.date).isEqualTo(LocalDate.of(2018, Month.OCTOBER, 31).atTime(20, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(article.authorUsername).isEqualTo("USERNAME_1");
		softly.assertThat(article.authorUrl).isEqualTo("https://myanimelist.net/profile/USERNAME_1");
		softly.assertThat(article.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1748998");
		softly.assertThat(article.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/s/common/uploaded_files/1541039084-d87d2d92a6923a7b69e2f1775ae39c93.jpeg?s=3288e6769101fc3bc22f4728c933d9f4");
		softly.assertThat(article.images.jpg.smallImageUrl).isNull();
		softly.assertThat(article.images.jpg.mediumImageUrl).isNull();
		softly.assertThat(article.images.jpg.largeImageUrl).isNull();
		softly.assertThat(article.images.jpg.maximumImageUrl).isNull();
		softly.assertThat(article.comments).isEqualTo(5);
		softly.assertThat(article.excerpt).startsWith("Since making her international debut at Sakura-Con in Seattle five years ago, model-turned-singer Luna Haruna has performed");
		softly.assertThat(article.excerpt).hasSize(167);
		softly.assertAll();
	}
}
