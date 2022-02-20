/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

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

public class UserReviewsQueryTest extends QueryTest {

	@Test
	void fetchUserReviews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/reviews", "users/getUserReviews.json");

		/* Act */
		UserReviewsQuery query = jikan.query().user().reviews("USER_NAME");
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/reviews");

		// Reviews
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review).isNotNull();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(434781);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=434781");
		softly.assertThat(review.type).isEqualTo(Type.ANIME);
		softly.assertThat(review.votes).isEqualTo(281);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 13).atTime(7, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).startsWith("*out-of-context spoilers* People always say Demon Slayer is only popular thanks to its dazzling animation, but");
		softly.assertThat(review.review).hasSize(18772);
		softly.assertThat(review.seen).isEqualTo(11);
		softly.assertThat(review.scores.overall).isEqualTo(4);
		softly.assertThat(review.scores.story).isEqualTo(2);
		softly.assertThat(review.scores.art).isEqualTo(0);
		softly.assertThat(review.scores.animation).isEqualTo(8);
		softly.assertThat(review.scores.sound).isEqualTo(7);
		softly.assertThat(review.scores.character).isEqualTo(2);
		softly.assertThat(review.scores.enjoyment).isEqualTo(3);
		softly.assertThat(review.entry.malId).isEqualTo(47778);
		softly.assertThat(review.entry.url).isEqualTo("https://myanimelist.net/anime/47778/Kimetsu_no_Yaiba__Yuukaku-hen");
		softly.assertThat(review.entry.name).isEqualTo("Kimetsu no Yaiba: Yuukaku-hen");
		softly.assertThat(review.entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1908/120036.jpg?s=458e378dd801eb8cdf849a7afa10fd23");
		softly.assertThat(review.user).isNull();
		softly.assertAll();
	}
}
