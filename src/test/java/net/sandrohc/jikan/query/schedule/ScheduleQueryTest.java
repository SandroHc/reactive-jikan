/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.schedule;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.DayOfWeek;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleQueryTest extends QueryTest {

	@Test
	void fetchSchedule() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/schedules", "schedules/getSchedules.json");

		/* Act */
		ScheduleQuery query = jikan.query().schedule().day(DayOfWeek.MONDAY);
		Collection<Anime> schedule = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/schedules?filter=monday");

		// Schedules
		assertThat(schedule).isNotNull();
		assertThat(schedule).hasSize(1);

		Anime anime = schedule.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(anime.toString()).isNotNull();
		softly.assertThat(anime.malId).isEqualTo(49689);
		softly.assertThat(anime.url).isEqualTo("https://myanimelist.net/anime/49689/Hikaribe_my_light");
		softly.assertThat(anime.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1419/118906.jpg");
		softly.assertThat(anime.title).isEqualTo("Hikari~be my light");
		softly.assertThat(anime.titleEnglish).isEqualTo("Hikari~be my light");
		softly.assertThat(anime.titleJapanese).isEqualTo("自主制作アニメ");
		softly.assertThat(anime.titleSynonyms).containsExactlyInAnyOrder("自主制作アニメ");
		softly.assertThat(anime.type).isEqualTo(AnimeType.TV);
		softly.assertThat(anime.source).isEqualTo("Original");
		softly.assertThat(anime.episodes).isNull();
		softly.assertThat(anime.status).isEqualTo(AnimeStatus.AIRING);
		softly.assertThat(anime.airing).isTrue();
		softly.assertThat(anime.aired.from).isEqualTo(LocalDate.of(2020, Month.JANUARY, 17).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(anime.aired.to).isNull();
		softly.assertThat(anime.duration).isEqualTo(Duration.ofMinutes(4));
		softly.assertThat(anime.rating).isEqualTo(AgeRating.PG13);
		softly.assertThat(anime.score).isEqualTo(0.0D);
		softly.assertThat(anime.scoredBy).isEqualTo(0);
		softly.assertThat(anime.rank).isEqualTo(0);
		softly.assertThat(anime.popularity).isEqualTo(0);
		softly.assertThat(anime.members).isEqualTo(0);
		softly.assertThat(anime.favorites).isEqualTo(2);
		softly.assertThat(anime.synopsis).isEqualTo("He is the only light. Story about an office worker who is suffering from depression and a university student.");
		softly.assertThat(anime.background).isNull();
		softly.assertThat(anime.season).isNull();
		softly.assertThat(anime.year).isNull();
		softly.assertThat(anime.broadcast.day).isNull();
		softly.assertThat(anime.broadcast.time).isNull();
		softly.assertThat(anime.broadcast.timezone).isNull();
		softly.assertThat(anime.broadcast.string).isEqualTo("Unknown");
		softly.assertThat(anime.producers).extracting(Entity::getMalId, Entity::getName).containsExactlyInAnyOrder();
		softly.assertThat(anime.licensors).extracting(Entity::getMalId, Entity::getName).containsExactlyInAnyOrder();
		softly.assertThat(anime.studios).extracting(Entity::getMalId, Entity::getName).containsExactlyInAnyOrder();
		softly.assertThat(anime.genres).map(GenreEntity::getName).containsExactlyInAnyOrder(Genre.BOYS_LOVE);
		softly.assertThat(anime.themes).map(GenreEntity::getName).containsExactlyInAnyOrder();
		softly.assertThat(anime.demographics).containsExactlyInAnyOrder();
		softly.assertAll();
	}

	@Test
	void fetchSchedule_allDays() throws JikanUrlException {
		ScheduleQuery query = jikan.query().schedule();
		assertThat(query.day).isNull();
		assertThat(query.getUrl().build()).isEqualTo("/schedules");
	}

}
