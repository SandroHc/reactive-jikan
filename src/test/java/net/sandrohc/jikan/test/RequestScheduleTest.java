package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.DayOfWeek;
import net.sandrohc.jikan.model.enums.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestScheduleTest extends RequestTest {

	@Test
	void fetchSchedule() {
		// https://api.jikan.moe/v3/schedule/monday

		mock(mockServer, "/schedule/monday", response);

		Schedule schedule = jikan.query().schedule().day(DayOfWeek.MONDAY).execute().block();

		assertNotNull(schedule);
		assertNotNull(schedule.toString());
		assertNotNull(schedule.monday);
		assertNull(schedule.tuesday);
		assertNull(schedule.wednesday);
		assertNull(schedule.thursday);
		assertNull(schedule.friday);
		assertNull(schedule.saturday);
		assertNull(schedule.sunday);
		assertNull(schedule.other);
		assertNull(schedule.unknown);


		SeasonAnime anime = schedule.monday.iterator().next();

		assertNotNull(anime);
		assertNotNull(anime.toString());
		assertEquals(39587, anime.malId);
		assertEquals("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season", anime.url);
		assertEquals("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season", anime.title);
		assertEquals("https://cdn.myanimelist.net/images/anime/1444/108005.jpg", anime.imageUrl);
		assertEquals("Even after dying countless times, Subaru finally ended the threat...", anime.synopsis);
		assertEquals(AnimeType.TV, anime.type);
		assertEquals(OffsetDateTime.parse("2020-07-08T13:30:00+00:00"), anime.airingStart);
		assertEquals(13, anime.episodes);
		assertEquals(290261, anime.members);
		assertEquals("Light novel", anime.source);
		assertEquals(8.5F, anime.score);
		assertFalse(anime.r18);
		assertFalse(anime.kids);
		assertFalse(anime.continuing);

		Iterator<GenreEntity<AnimeGenre>> genresIt = anime.genres.iterator();
		GenreEntity<AnimeGenre> genre1 = genresIt.next();
		GenreEntity<AnimeGenre> genre2 = genresIt.next();
		GenreEntity<AnimeGenre> genre3 = genresIt.next();
		GenreEntity<AnimeGenre> genre4 = genresIt.next();
		assertFalse(genresIt.hasNext());
		assertNotNull(genre1.toString());
		assertEquals(AnimeGenre.DRAMA, genre1.name);
		assertEquals(AnimeGenre.FANTASY, genre2.name);
		assertEquals(AnimeGenre.PSYCHOLOGICAL, genre3.name);
		assertEquals(AnimeGenre.THRILLER, genre4.name);

		MalSubEntity producer = anime.producers.iterator().next();
		assertEquals(314, producer.malId);
		assertEquals(Type.ANIME, producer.type);
		assertEquals("White Fox", producer.name);
		assertEquals("https://myanimelist.net/anime/producer/314/White_Fox", producer.url);

		assertEquals("Crunchyroll", anime.licensors.iterator().next());
	}

	@Test
	void fetchSchedule_allDays() {
		// https://api.jikan.moe/v3/schedule

		mock(mockServer, "/schedule", response);

		Schedule schedule = jikan.query().schedule().execute().block();

		assertNotNull(schedule);
		assertNotNull(schedule.monday);
		assertNotNull(schedule.tuesday);
		assertNotNull(schedule.wednesday);
		assertNotNull(schedule.thursday);
		assertNotNull(schedule.friday);
		assertNotNull(schedule.saturday);
		assertNotNull(schedule.sunday);
		assertNotNull(schedule.other);
		assertNotNull(schedule.unknown);
	}

}
