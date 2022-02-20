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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaNewsQueryTest extends QueryTest {

	@Test
	void fetchMangaNews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/news", "manga/getMangaNews.json");

		/* Act */
		MangaNewsQuery query = jikan.query().manga().news(23390);
		Collection<NewsArticle> newsArticles = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/news");

		// News Articles
		assertThat(newsArticles).isNotNull();
		assertThat(newsArticles).hasSize(1);

		NewsArticle article = newsArticles.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(article.toString()).isNotNull();
		softly.assertThat(article.url).isEqualTo("https://myanimelist.net/news/65168030");
		softly.assertThat(article.title).isEqualTo("'Kono Manga ga Sugoi!' 2022 Rankings Revealed");
		softly.assertThat(article.date).isEqualTo(LocalDate.of(2021, Month.DECEMBER, 12).atTime(1, 11).atOffset(ZoneOffset.UTC));
		softly.assertThat(article.authorUsername).isEqualTo("USER_1");
		softly.assertThat(article.authorUrl).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(article.forumUrl).isEqualTo("https://myanimelist.net/forum/?topicid=1978847");
		softly.assertThat(article.images.jpg.imageUrl).isEqualTo( "https://cdn.myanimelist.net/s/common/uploaded_files/1639300126-5b3f7ab8681fbd31a28c3556dcb4e891.jpeg?s=fb5f7b8f9844443a824273b6b06eca46");
		softly.assertThat(article.comments).isEqualTo(10);
		softly.assertThat(article.excerpt).startsWith("Here are the top 20 manga rankings collected by the \"Kono Manga ga Sugoi!\" (This Manga is Amazing!) annual magazine");
		softly.assertThat(article.excerpt).hasSize(153);
		softly.assertAll();
	}
}
