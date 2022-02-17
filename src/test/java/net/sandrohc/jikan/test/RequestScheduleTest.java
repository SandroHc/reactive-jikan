package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.DayOfWeek;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.schedule.ScheduleQuery;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestScheduleTest extends RequestTest {

	@Test
	void fetchSchedule() {
		// https://api.jikan.moe/v3/schedule/monday

		mock(mockServer, "/schedule/monday", response);

		ScheduleQuery query = jikan.query().schedule().day(DayOfWeek.MONDAY);
		Collection<Anime> schedule = query.execute().collectList().block();

		assertThat(schedule).isNotNull();
		assertThat(schedule.toString()).isNotNull();
		assertThat(schedule.monday).isNotNull();
		assertThat(schedule.tuesday).isNull();
		assertThat(schedule.wednesday).isNull();
		assertThat(schedule.thursday).isNull();
		assertThat(schedule.friday).isNull();
		assertThat(schedule.saturday).isNull();
		assertThat(schedule.sunday).isNull();
		assertThat(schedule.other).isNull();
		assertThat(schedule.unknown).isNull();


		SeasonAnime anime = schedule.monday.iterator().next();

		assertThat(anime).isNotNull();
		assertThat(anime.toString()).isNotNull();
		assertThat(anime.malId).isEqualTo(39587);
		assertThat(anime.url).isEqualTo("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season");
		assertThat(anime.title).isEqualTo("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season");
		assertThat(anime.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1444/108005.jpg");
		assertThat(Subaru finally ended the threat...", anime.synopsis).isEqualTo("Even after dying countless times);
		assertThat(anime.type).isEqualTo(AnimeType.TV);
		assertThat(anime.airingStart).isEqualTo(OffsetDateTime.parse("2020-07-08T13:30:00+00:00"));
		assertThat(anime.episodes).isEqualTo(13);
		assertThat(anime.members).isEqualTo(290261);
		assertThat(anime.source).isEqualTo("Light novel");
		assertThat(anime.score).isEqualTo(8.5F);
		assertThat(anime.r18).isFalse();
		assertThat(anime.kids).isFalse();
		assertThat(anime.continuing).isFalse();

		Iterator<GenreEntity<AnimeGenre>> genresIt = anime.genres.iterator();
		GenreEntity<AnimeGenre> genre1 = genresIt.next();
		GenreEntity<AnimeGenre> genre2 = genresIt.next();
		GenreEntity<AnimeGenre> genre3 = genresIt.next();
		GenreEntity<AnimeGenre> genre4 = genresIt.next();
		assertThat(genresIt.hasNext()).isFalse();
		assertThat(genre1.toString()).isNotNull();
		assertThat(genre1.name).isEqualTo(AnimeGenre.DRAMA);
		assertThat(genre2.name).isEqualTo(AnimeGenre.FANTASY);
		assertThat(genre3.name).isEqualTo(AnimeGenre.PSYCHOLOGICAL);
		assertThat(genre4.name).isEqualTo(AnimeGenre.THRILLER);

		MalSubEntity producer = anime.producers.iterator().next();
		assertThat(producer.malId).isEqualTo(314);
		assertThat(producer.type).isEqualTo(Type.ANIME);
		assertThat(producer.name).isEqualTo("White Fox");
		assertThat(producer.url).isEqualTo("https://myanimelist.net/anime/producer/314/White_Fox");

		assertThat(anime.licensors.iterator().next()).isEqualTo("Crunchyroll");
	}

	@Test
	void fetchSchedule_allDays() {
		// https://api.jikan.moe/v3/schedule

		mock(mockServer, "/schedule", response);

		Schedule schedule = jikan.query().schedule().execute().block();

		assertThat(schedule).isNotNull();
		assertThat(schedule.monday).isNotNull();
		assertThat(schedule.tuesday).isNotNull();
		assertThat(schedule.wednesday).isNotNull();
		assertThat(schedule.thursday).isNotNull();
		assertThat(schedule.friday).isNotNull();
		assertThat(schedule.saturday).isNotNull();
		assertThat(schedule.sunday).isNotNull();
		assertThat(schedule.other).isNotNull();
		assertThat(schedule.unknown).isNotNull();
	}

}
