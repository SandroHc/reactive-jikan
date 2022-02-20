/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.review;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RecentAnimeReviewQueryTest extends QueryTest {

	@Test
	void fetchAnimeReviews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/reviews/anime", "reviews/getRecentAnimeReviews.json");

		/* Act */
		RecentAnimeReviewQuery query = jikan.query().review().anime().page(1).limit(2);
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/reviews/anime?page=1&limit=2");

		// Result
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(435556);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=435556");
		softly.assertThat(review.type).isEqualTo(Type.ANIME);
		softly.assertThat(review.votes).isEqualTo(0);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 19).atTime(0, 25).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).startsWith("Short Review (Spoiler-free): In summary, Re:Zero Season 2 Part 2 is amazing. It won't change your perception");
		softly.assertThat(review.review).hasSize(5469);
		softly.assertThat(review.seen).isEqualTo(12);
		softly.assertThat(review.scores).isNotNull();
		softly.assertThat(review.scores.toString()).isNotNull();
		softly.assertThat(review.scores.overall).isEqualTo(9);
		softly.assertThat(review.scores.story).isEqualTo(9);
		softly.assertThat(review.scores.art).isEqualTo(0);
		softly.assertThat(review.scores.animation).isEqualTo(10);
		softly.assertThat(review.scores.sound).isEqualTo(9);
		softly.assertThat(review.scores.character).isEqualTo(10);
		softly.assertThat(review.scores.enjoyment).isEqualTo(9);
		softly.assertThat(review.user.toString()).isNotNull();
		softly.assertThat(review.user.username).isEqualTo("USER_1");
		softly.assertThat(review.user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(review.user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/13325969.jpg?t=1645261200");
		softly.assertAll();
	}
}
