package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.schedule.*;
import net.sandrohc.jikan.model.season.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestScheduleTest extends RequestTest {

	@Test
	void fetchSchedule() {
		// https://api.jikan.moe/v3/schedule/monday
		String response = "{\n" +
				"    \"monday\": [\n" +
				"        {\n" +
				"            \"mal_id\": 39587,\n" +
				"            \"url\": \"https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season\",\n" +
				"            \"title\": \"Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/1444/108005.jpg\",\n" +
				"            \"synopsis\": \"Even after dying countless times, Subaru finally ended the threat...\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"airing_start\": \"2020-07-08T13:30:00+00:00\",\n" +
				"            \"episodes\": 13,\n" +
				"            \"members\": 290261,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 8,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Drama\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/8/Drama\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 10,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Fantasy\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/10/Fantasy\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 40,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Psychological\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/40/Psychological\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 41,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Thriller\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/41/Thriller\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"source\": \"Light novel\",\n" +
				"            \"producers\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 314,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"White Fox\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/producer/314/White_Fox\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": 8.5,\n" +
				"            \"licensors\": [ \"Crunchyroll\" ],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/schedule/monday", response);

		Schedule schedule = jikan.query().schedule().day(Day.MONDAY).execute().block();

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
		String response = "{\n" +
				"    \"monday\": [],\n" +
				"    \"tuesday\": [],\n" +
				"    \"wednesday\": [],\n" +
				"    \"thursday\": [],\n" +
				"    \"friday\": [],\n" +
				"    \"saturday\": [],\n" +
				"    \"sunday\": [],\n" +
				"    \"other\": [],\n" +
				"    \"unknown\": []\n" +
				"}";

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
