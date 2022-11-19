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
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.enums.DayOfWeek;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimeQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757", "anime/getAnimeById.json");

		/* Act */
		AnimeQuery query = jikan.query().anime().get(11757);
		Anime anime = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757");

		// Anime
		softly = new SoftAssertions();
		softly.assertThat(anime).isNotNull();
		softly.assertThat(anime.toString()).isNotNull();
		softly.assertThat(anime.malId).isEqualTo(11757);
		softly.assertThat(anime.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online");
		softly.assertThat(anime.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717.jpg");
		softly.assertThat(anime.images.jpg.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717t.jpg");
		softly.assertThat(anime.images.jpg.mediumImageUrl).isNull();
		softly.assertThat(anime.images.jpg.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717l.jpg");
		softly.assertThat(anime.images.jpg.maximumImageUrl).isNull();
		softly.assertThat(anime.images.webp.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717.webp");
		softly.assertThat(anime.images.webp.smallImageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717t.webp");
		softly.assertThat(anime.images.webp.mediumImageUrl).isNull();
		softly.assertThat(anime.images.webp.largeImageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/11/39717l.webp");
		softly.assertThat(anime.images.webp.maximumImageUrl).isNull();
		softly.assertThat(anime.trailer.youtubeId).isEqualTo("6ohYYtxfDCg");
		softly.assertThat(anime.trailer.url).isEqualTo("https://www.youtube.com/watch?v=6ohYYtxfDCg");
		softly.assertThat(anime.trailer.embedUrl).isEqualTo("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1");
		softly.assertThat(anime.trailer.images.imageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/default.jpg");
		softly.assertThat(anime.trailer.images.smallImageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/sddefault.jpg");
		softly.assertThat(anime.trailer.images.mediumImageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/mqdefault.jpg");
		softly.assertThat(anime.trailer.images.largeImageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/hqdefault.jpg");
		softly.assertThat(anime.trailer.images.maximumImageUrl).isEqualTo("https://img.youtube.com/vi/6ohYYtxfDCg/maxresdefault.jpg");
		softly.assertThat(anime.title).isEqualTo("Sword Art Online");
		softly.assertThat(anime.titleEnglish).isEqualTo("Sword Art Online");
		softly.assertThat(anime.titleJapanese).isEqualTo("ソードアート・オンライン");
		softly.assertThat(anime.titleSynonyms).containsExactlyInAnyOrder("S.A.O", "SAO");
		softly.assertThat(anime.type).isEqualTo(AnimeType.TV);
		softly.assertThat(anime.source).isEqualTo("Light novel");
		softly.assertThat(anime.episodes).isEqualTo(25);
		softly.assertThat(anime.status).isEqualTo(AnimeStatus.COMPLETED);
		softly.assertThat(anime.airing).isFalse();
		softly.assertThat(anime.aired.from).isEqualTo(LocalDate.of(2012, Month.JULY, 8).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(anime.aired.to).isEqualTo(LocalDate.of(2012, Month.DECEMBER, 23).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(anime.duration).isEqualTo(Duration.ofMinutes(23));
		softly.assertThat(anime.rating).isEqualTo(AgeRating.PG13);
		softly.assertThat(anime.score).isEqualTo(7.2D);
		softly.assertThat(anime.scoredBy).isEqualTo(1845274);
		softly.assertThat(anime.rank).isEqualTo(2897);
		softly.assertThat(anime.popularity).isEqualTo(5);
		softly.assertThat(anime.members).isEqualTo(2669774);
		softly.assertThat(anime.favorites).isEqualTo(62703);
		softly.assertThat(anime.synopsis).startsWith("In the year 2022, virtual reality has progressed by leaps and bounds, and a massive online role-playing game called Sword Art Online");
		softly.assertThat(anime.synopsis).hasSize(1241);
		softly.assertThat(anime.background).isEqualTo("BACKGROUND");
		softly.assertThat(anime.season).isEqualTo(Season.SUMMER);
		softly.assertThat(anime.year).isEqualTo(2012);
		softly.assertThat(anime.broadcast.day).isEqualTo(DayOfWeek.SUNDAY);
		softly.assertThat(anime.broadcast.time).isEqualTo(LocalTime.of(0, 0));
		softly.assertThat(anime.broadcast.timezone).isEqualTo(ZoneId.of("Asia/Tokyo"));
		softly.assertThat(anime.broadcast.string).isEqualTo("Sundays at 00:00 (JST)");
		softly.assertThat(anime.producers)
				.extracting(Entity::getMalId, Entity::getName)
				.containsExactlyInAnyOrder(
						tuple(17, "Aniplex"),
						tuple(79, "Genco")
				);
		softly.assertThat(anime.licensors)
				.extracting(Entity::getMalId, Entity::getName)
				.containsExactlyInAnyOrder(
						tuple(493, "Aniplex of America")
				);
		softly.assertThat(anime.studios)
				.extracting(Entity::getMalId, Entity::getName)
				.containsExactlyInAnyOrder(
						tuple(56, "A-1 Pictures")
				);
		softly.assertThat(anime.genres).map(GenreEntity::getName).containsExactlyInAnyOrder(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY, Genre.ROMANCE);
		softly.assertThat(anime.themes).map(GenreEntity::getName).containsExactlyInAnyOrder(Genre.LOVE_POLYGON, Genre.VIDEO_GAME);
		softly.assertThat(anime.demographics).map(Entity::getName).containsExactlyInAnyOrder("Shounen");
		softly.assertAll();
	}

	@Test
	void fetchAnimeWithUnknownEnums() throws JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757", "anime/getAnimeById_unknowns.json");

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
