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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaReviewsQueryTest extends QueryTest {

	@Test
	void fetchMangaReviews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/reviews", "manga/getMangaReviews.json");

		/* Act */
		MangaReviewsQuery query = jikan.query().manga().reviews(23390).page(1);
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/reviews?page=1");

		// Reviews
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(390656);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=390656");
		softly.assertThat(review.type).isEqualTo(Type.MANGA);
		softly.assertThat(review.votes).isEqualTo(1888);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2021, Month.APRIL, 8).atTime(8, 47).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).startsWith("*This review contains spoilers* Ladies and Gentlemen, Hello. My name is Hajime Isayama and today I'll teach");
		softly.assertThat(review.review).hasSize(9391);
		softly.assertThat(review.seen).isEqualTo(141);
		softly.assertAll();

		ReviewScores scores = review.scores;
		softly = new SoftAssertions();
		softly.assertThat(scores).isNotNull();
		softly.assertThat(scores.toString()).isNotNull();
		softly.assertThat(scores.overall).isEqualTo(1);
		softly.assertThat(scores.story).isEqualTo(1);
		softly.assertThat(scores.art).isEqualTo(7);
		softly.assertThat(scores.animation).isEqualTo(0);
		softly.assertThat(scores.sound).isEqualTo(0);
		softly.assertThat(scores.character).isEqualTo(1);
		softly.assertThat(scores.enjoyment).isEqualTo(1);
		softly.assertAll();

		EntityWithImage entry = review.entry;
		softly = new SoftAssertions();
		softly.assertThat(entry).isNull();
		softly.assertAll();

		UserSimple user = review.user;
		softly = new SoftAssertions();
		softly.assertThat(user).isNotNull();
		softly.assertThat(user.toString()).isNotNull();
		softly.assertThat(user.username).isEqualTo("USER_1");
		softly.assertThat(user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/10783406.jpg?t=1640443800");
		softly.assertAll();
	}
}
