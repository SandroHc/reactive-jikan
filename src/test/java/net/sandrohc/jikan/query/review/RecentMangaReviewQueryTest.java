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
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class RecentMangaReviewQueryTest extends QueryTest {

	@Test
	void fetchMangaReviews() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/reviews/manga", "reviews/getRecentMangaReviews.json");

		/* Act */
		RecentMangaReviewQuery query = jikan.query().review().manga().page(1).limit(2);
		Collection<Review> reviews = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/reviews/manga?page=1&limit=2");

		// Result
		assertThat(reviews).isNotNull();
		assertThat(reviews).hasSize(1);

		Review review = reviews.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(review.toString()).isNotNull();
		softly.assertThat(review.malId).isEqualTo(435554);
		softly.assertThat(review.url).isEqualTo("https://myanimelist.net/reviews.php?id=435554");
		softly.assertThat(review.type).isEqualTo(Type.MANGA);
		softly.assertThat(review.votes).isEqualTo(0);
		softly.assertThat(review.date).isEqualTo(LocalDate.of(2022, Month.FEBRUARY, 18).atTime(23, 45).atOffset(ZoneOffset.UTC));
		softly.assertThat(review.review).startsWith("i solely like this manga because it is the only one i found that did not make the big boobed girl");
		softly.assertThat(review.review).hasSize(560);
		softly.assertThat(review.seen).isEqualTo(38);
		softly.assertAll();

		ReviewScores scores = review.scores;
		softly = new SoftAssertions();
		softly.assertThat(scores).isNotNull();
		softly.assertThat(scores.toString()).isNotNull();
		softly.assertThat(scores.overall).isEqualTo(5);
		softly.assertThat(scores.story).isEqualTo(4);
		softly.assertThat(scores.art).isEqualTo(7);
		softly.assertThat(scores.animation).isEqualTo(0);
		softly.assertThat(scores.sound).isEqualTo(0);
		softly.assertThat(scores.character).isEqualTo(4);
		softly.assertThat(scores.enjoyment).isEqualTo(6);
		softly.assertAll();

		EntityWithImage entry = review.entry;
		softly = new SoftAssertions();
		softly.assertThat(entry).isNotNull();
		softly.assertThat(entry.malId).isEqualTo(132519);
		softly.assertThat(entry.url).isEqualTo("https://myanimelist.net/manga/132519/Sekimen_Shinaide_Sekime-san");
		softly.assertThat(entry.name).isEqualTo("Sekimen Shinaide Sekime-san");
		softly.assertThat(entry.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/3/244109.jpg?s=725e818b4bec9a5e617f1d6ea1d47bfe");
		softly.assertAll();

		UserSimple user = review.user;
		softly = new SoftAssertions();
		softly.assertThat(user).isNotNull();
		softly.assertThat(user.toString()).isNotNull();
		softly.assertThat(user.username).isEqualTo("USER_1");
		softly.assertThat(user.url).isEqualTo("https://myanimelist.net/profile/USER_1");
		softly.assertThat(user.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/14569386.jpg?t=1645258200");
		softly.assertAll();
	}
}
