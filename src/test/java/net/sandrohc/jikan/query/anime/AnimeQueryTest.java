/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.DayOfWeek;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimeQueryTest extends RequestTest {

	@Test
	void fetchAnime() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/anime/11757", 1, "anime/getAnimeById.json");

		/* Act */
		AnimeQuery query = jikan.query().anime().get(11757);
		Anime anime = query.execute().block();

		/* Assert */
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757");

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(anime).isNotNull();
		softly.assertThat(anime.toString()).isNotNull();
		softly.assertThat(anime.malId).isEqualTo(1);
		softly.assertThat(anime.url).isEqualTo("https://example.com/url");
		softly.assertThat(anime.images.jpg.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.jpg.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.jpg.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.jpg.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.jpg.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.webp.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.webp.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.webp.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.webp.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.images.webp.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.trailer.youtubeId).isEqualTo("https://example.com/trailer_url");
		softly.assertThat(anime.trailer.url).isEqualTo("https://example.com/trailer_url");
		softly.assertThat(anime.trailer.embedUrl).isEqualTo("https://example.com/trailer_url");
		softly.assertThat(anime.trailer.images.imageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.trailer.images.smallImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.trailer.images.mediumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.trailer.images.largeImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.trailer.images.maximumImageUrl).isEqualTo("https://example.com/image_url");
		softly.assertThat(anime.title).isEqualTo("TITLE");
		softly.assertThat(anime.titleEnglish).isEqualTo("TITLE ENGLISH");
		softly.assertThat(anime.titleJapanese).isEqualTo("TITLE 日本語");
		softly.assertThat(anime.titleSynonyms).containsExactly("TITLE_SYNONYM");
		softly.assertThat(anime.type).isEqualTo(AnimeType.TV);
		softly.assertThat(anime.source).isEqualTo("SOURCE");
		softly.assertThat(anime.episodes).isEqualTo(10);
		softly.assertThat(anime.status).isEqualTo(AnimeStatus.COMPLETED);
		softly.assertThat(anime.airing).isFalse();
		softly.assertThat(anime.aired.from).isEqualTo(LocalDateTime.of(2010, Month.JANUARY, 1, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(anime.aired.to).isEqualTo(LocalDateTime.of(2010, Month.DECEMBER, 1, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(anime.duration).isEqualTo("DURATION");
		softly.assertThat(anime.rating).isEqualTo(AgeRating.PG13);
		softly.assertThat(anime.score).isEqualTo(10.0F);
		softly.assertThat(anime.scoredBy).isEqualTo(20);
		softly.assertThat(anime.rank).isEqualTo(30);
		softly.assertThat(anime.popularity).isEqualTo(40);
		softly.assertThat(anime.members).isEqualTo(50);
		softly.assertThat(anime.favorites).isEqualTo(60);
		softly.assertThat(anime.synopsis).isEqualTo("SYNOPSIS");
		softly.assertThat(anime.background).isEqualTo("BACKGROUND");
		softly.assertThat(anime.season).isEqualTo(Season.SUMMER);
		softly.assertThat(anime.year).isEqualTo(2012);
		softly.assertThat(anime.broadcast.day).isEqualTo(DayOfWeek.MONDAY);
		softly.assertThat(anime.broadcast.time).isEqualTo("Sundays at 00:00 (JST)");
		softly.assertThat(anime.broadcast.timezone).isEqualTo("Sundays at 00:00 (JST)");
		softly.assertThat(anime.broadcast.string).isEqualTo("Sundays at 00:00 (JST)");
		softly.assertThat(anime.producers).extracting(Entity::getMalId, Entity::getName).containsExactly(tuple(1, "OPENING THEME"));
		softly.assertThat(anime.licensors).extracting(Entity::getMalId, Entity::getName).containsExactly(tuple(1, "OPENING THEME"));
		softly.assertThat(anime.studios).extracting(Entity::getMalId, Entity::getName).containsExactly(tuple(1, "OPENING THEME"));
		softly.assertThat(anime.genres).map(GenreEntity::getName).containsExactly(AnimeGenre.ACTION, AnimeGenre.ADVENTURE);
		softly.assertThat(anime.themes).map(GenreEntity::getName).containsExactly(AnimeGenre.ACTION);
		softly.assertThat(anime.demographics).containsExactly("ENDING THEME");
		softly.assertAll();
	}

	@Test
	void fetchAnimeWithUnknownEnums() throws JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757", 1, "anime/getAnimeById_unknowns.json");

		/* Act */
		Anime anime = jikan.query().anime().get(11757).execute().block();

		/* Assert */
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(anime).isNotNull();
		softly.assertThat(anime.type).isEqualTo(AnimeType.UNKNOWN);
		softly.assertThat(anime.rating).isEqualTo(AgeRating.UNKNOWN);
		softly.assertThat(anime.season).isEqualTo(Season.UNKNOWN);
		softly.assertThat(anime.broadcast.day).isEqualTo(DayOfWeek.UNKNOWN);
		softly.assertAll();
	}
}
