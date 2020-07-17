package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.season.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestSeasonTest extends RequestTest {

	@Test
	void fetchSeason() {
		// https://api.jikan.moe/v3/season/2020/summer
		String response = "{\n" +
				"    \"season_name\": \"Summer\",\n" +
				"    \"season_year\": 2020,\n" +
				"    \"anime\": [\n" +
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
				"            \"licensors\": [ \"Crunchyroll\"],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false,\n" +
				"            \"continuing\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/season/2020/summer", response);

		Collection<SeasonAnime> results = jikan.query().season().get(2020, net.sandrohc.jikan.model.enums.Season.SUMMER)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new SeasonList().toString());

		/* Results */
		SeasonAnime anime = results.iterator().next();

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
	void fetchSeasonArchive() {
		// https://api.jikan.moe/v3/season/archive
		String response = "{\n" +
				"    \"archive\": [\n" +
				"        {\n" +
				"            \"year\": 2020,\n" +
				"            \"seasons\": [\n" +
				"                \"Winter\",\n" +
				"                \"Spring\",\n" +
				"                \"Summer\",\n" +
				"                \"Fall\"\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"year\": 1917,\n" +
				"            \"seasons\": [\n" +
				"                \"Winter\",\n" +
				"                \"Spring\",\n" +
				"                \"Summer\",\n" +
				"                \"Fall\"\n" +
				"            ]\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/season/archive", response);

		List<SeasonArchiveYear> results = jikan.query().season().archive()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new SeasonArchive().toString());

		/* Results */
		Iterator<SeasonArchiveYear> yearIt = results.iterator();

		SeasonArchiveYear year1 = yearIt.next();
		assertNotNull(year1);
		assertNotNull(year1.toString());
		assertEquals(2020, year1.year);
		assertTrue(year1.seasons.containsAll(Arrays.asList(
				net.sandrohc.jikan.model.enums.Season.WINTER,
				net.sandrohc.jikan.model.enums.Season.SPRING,
				net.sandrohc.jikan.model.enums.Season.SUMMER,
				net.sandrohc.jikan.model.enums.Season.FALL
		)));

		SeasonArchiveYear year2 = yearIt.next();
		assertNotNull(year2);
		assertNotNull(year2.toString());
		assertEquals(1917, year2.year);
		assertTrue(year2.seasons.containsAll(Arrays.asList(
				net.sandrohc.jikan.model.enums.Season.WINTER,
				net.sandrohc.jikan.model.enums.Season.SPRING,
				net.sandrohc.jikan.model.enums.Season.SUMMER,
				net.sandrohc.jikan.model.enums.Season.FALL
		)));

		assertFalse(yearIt.hasNext());
	}

	@Test
	void fetchSeasonLater() {
		// https://api.jikan.moe/v3/season/later
		String response = "{\n" +
				"    \"season_name\": \"Later\",\n" +
				"    \"season_year\": null,\n" +
				"    \"anime\": [\n" +
				"        {\n" +
				"            \"mal_id\": 40028,\n" +
				"            \"url\": \"https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season\",\n" +
				"            \"title\": \"Shingeki no Kyojin: The Final Season\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/1384/107759.jpg\",\n" +
				"            \"synopsis\": \"Final Season of Shingeki no Kyojin.\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"airing_start\": null,\n" +
				"            \"episodes\": null,\n" +
				"            \"members\": 126784,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Action\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 38,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Military\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/38/Military\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 7,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Mystery\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/7/Mystery\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 31,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Super Power\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/31/Super_Power\"\n" +
				"                },\n" +
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
				"                    \"mal_id\": 27,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Shounen\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/27/Shounen\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"source\": \"Manga\",\n" +
				"            \"producers\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 569,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"MAPPA\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/producer/569/MAPPA\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": null,\n" +
				"            \"licensors\": [],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false,\n" +
				"            \"continuing\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/season/later", response);

		Collection<SeasonAnime> results = jikan.query().season().later()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);

		/* Results */
		SeasonAnime anime = results.iterator().next();

		assertNotNull(anime);
		assertNotNull(anime.toString());
		assertEquals(40028, anime.malId);
		assertEquals("https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season", anime.url);
		assertEquals("Shingeki no Kyojin: The Final Season", anime.title);
		assertEquals("https://cdn.myanimelist.net/images/anime/1384/107759.jpg", anime.imageUrl);
		assertEquals("Final Season of Shingeki no Kyojin.", anime.synopsis);
		assertEquals(AnimeType.TV, anime.type);
		assertNull(anime.airingStart);
		assertNull(anime.episodes);
		assertEquals(126784, anime.members);
		assertEquals("Manga", anime.source);
		assertNull(anime.score);
		assertFalse(anime.r18);
		assertFalse(anime.kids);
		assertFalse(anime.continuing);

		Iterator<GenreEntity<AnimeGenre>> genresIt = anime.genres.iterator();
		assertEquals(AnimeGenre.ACTION, genresIt.next().name);
		assertEquals(AnimeGenre.MILITARY, genresIt.next().name);
		assertEquals(AnimeGenre.MYSTERY, genresIt.next().name);
		assertEquals(AnimeGenre.SUPERPOWER, genresIt.next().name);
		assertEquals(AnimeGenre.DRAMA, genresIt.next().name);
		assertEquals(AnimeGenre.FANTASY, genresIt.next().name);
		assertEquals(AnimeGenre.SHOUNEN, genresIt.next().name);

		MalSubEntity producer = anime.producers.iterator().next();
		assertEquals(569, producer.malId);
		assertEquals(Type.ANIME, producer.type);
		assertEquals("MAPPA", producer.name);
		assertEquals("https://myanimelist.net/anime/producer/569/MAPPA", producer.url);

		assertTrue(anime.licensors.isEmpty());
	}

}
