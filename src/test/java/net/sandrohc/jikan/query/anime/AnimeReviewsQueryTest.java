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

public class AnimeReviewsQueryTest extends QueryTest {

	@Test
	void fetchAnimeReviews() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/reviews", "anime/getAnimeReviews.json");

		/* Act */
		AnimeReviewsQuery query = jikan.query().anime().reviews(11757);
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/reviews");

		// Reviews
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(138911);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=138911");
		softly.assertThat(review.votes).isEqualTo(5216);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2014, Month.APRIL, 6).atTime(2, 33).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).startsWith("--The review contains minor spoilers-- Since I've seen a plethora of scores of 10 for this show, I thought I'd write");
		softly.assertThat(review.review).hasSize(5240);
		softly.assertThat(review.seen).isEqualTo(25);
		softly.assertThat(review.scores).isNotNull();
		softly.assertThat(review.scores.toString()).isNotNull();
		softly.assertThat(review.scores.overall).isEqualTo(4);
		softly.assertThat(review.scores.story).isEqualTo(4);
		softly.assertThat(review.scores.art).isEqualTo(0);
		softly.assertThat(review.scores.animation).isEqualTo(8);
		softly.assertThat(review.scores.sound).isEqualTo(8);
		softly.assertThat(review.scores.character).isEqualTo(1);
		softly.assertThat(review.scores.enjoyment).isEqualTo(3);
		softly.assertThat(review.user.toString()).isNotNull();
		softly.assertThat(review.user.username).isEqualTo("USERNAME_1");
		softly.assertThat(review.user.url).isEqualTo("https://myanimelist.net/profile/USERNAME_1");
		softly.assertThat(review.user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/3441293.jpg?t=1644724200");
		softly.assertAll();
	}
}
