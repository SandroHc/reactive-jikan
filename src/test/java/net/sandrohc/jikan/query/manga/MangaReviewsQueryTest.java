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
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaReviewsQueryTest extends RequestTest {

	@Test
	void fetchMangaReviews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390/reviews", 1, "manga/getMangaReviews.json");

		/* Act */
		MangaReviewsQuery query = jikan.query().manga().reviews(96792).page(1);
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390/reviews");

		// Reviews
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(340220);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=340220");
		softly.assertThat(review.type).isEqualTo(Type.ANIME);
		softly.assertThat(review.votes).isEqualTo(259);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2020, Month.JULY, 15).atTime(15, 39).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).isEqualTo("As of the time of writing this review...");
		softly.assertThat(review.seen).isEqualTo(207);
		softly.assertAll();

		ReviewScores scores = review.scores;
		softly = new SoftAssertions();
		softly.assertThat(scores).isNotNull();
		softly.assertThat(scores.toString()).isNotNull();
		softly.assertThat(scores.overall).isEqualTo(5);
		softly.assertThat(scores.story).isEqualTo(5);
		softly.assertThat(scores.art).isEqualTo(7);
		softly.assertThat(scores.animation).isEqualTo(7);
		softly.assertThat(scores.sound).isEqualTo(7);
		softly.assertThat(scores.character).isEqualTo(6);
		softly.assertThat(scores.enjoyment).isEqualTo(5);
		softly.assertAll();

		EntityWithImage entry = review.entry;
		softly = new SoftAssertions();
		softly.assertThat(entry).isNotNull();
		softly.assertThat(entry.toString()).isNotNull();
		softly.assertThat(entry.malId).isEqualTo(5);
		softly.assertThat(entry.url).isEqualTo("URL");
		softly.assertThat(entry.name).isEqualTo("NAME");
		softly.assertThat(entry.images.jpg.imageUrl).isEqualTo("IMAGE");
		softly.assertAll();

		UserSimple user = review.user;
		softly = new SoftAssertions();
		softly.assertThat(user).isNotNull();
		softly.assertThat(user.toString()).isNotNull();
		softly.assertThat(user.username).isEqualTo("abystoma2");
		softly.assertThat(user.url).isEqualTo("https://myanimelist.net/profile/abystoma2");
		softly.assertThat(user.images.jpg.imageUrl).isEqualTo("https://myanimelist.net/profile/abystoma2");
		softly.assertAll();
	}
}
