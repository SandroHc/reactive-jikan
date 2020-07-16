package net.sandrohc.jikan.test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Iterator;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.model.search.*;
import org.junit.jupiter.api.Test;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestMangaTest extends RequestTest {

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/manga?q=test&page=1&limit=2
		String response = "{\n" +
						  "    \"results\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 2915,\n" +
						  "            \"url\": \"https://myanimelist.net/manga/2915/Testarotho\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/200365.jpg?s=2925c3aa1fca9d2e89f5480cfbf95984\",\n" +
						  "            \"title\": \"Testarotho\",\n" +
						  "            \"publishing\": false,\n" +
						  "            \"synopsis\": \"Capria has spent her life sheltered...\",\n" +
						  "            \"type\": \"Manga\",\n" +
						  "            \"chapters\": 26,\n" +
						  "            \"volumes\": 4,\n" +
						  "            \"score\": 6.6,\n" +
						  "            \"start_date\": \"2000-06-09T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2002-10-09T00:00:00+00:00\",\n" +
						  "            \"members\": 612\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 12087,\n" +
						  "            \"url\": \"https://myanimelist.net/manga/12087/Testify\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/manga/1/16557.jpg?s=a0a3be348323d317207d9aa35db7c3a3\",\n" +
						  "            \"title\": \"Testify\",\n" +
						  "            \"publishing\": false,\n" +
						  "            \"synopsis\": \"\",\n" +
						  "            \"type\": \"One-shot\",\n" +
						  "            \"chapters\": 1,\n" +
						  "            \"volumes\": 0,\n" +
						  "            \"score\": 5.73,\n" +
						  "            \"start_date\": \"2004-11-17T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2004-11-17T00:00:00+00:00\",\n" +
						  "            \"members\": 404\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"last_page\": 20\n" +
						  "}";

		mock(mockServer, "/search/manga", response,
				Parameter.param("q", "test"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("genre[]", "1,2"),
				Parameter.param("status", "completed"),
				Parameter.param("orderBy", "id"),
				Parameter.param("sort", "asc"),
				Parameter.param("rated", "pg"),
				Parameter.param("score", "1.0"),
				Parameter.param("startDate", "2020-01-01"),
				Parameter.param("endDate", "2020-12-31"));

		MangaSearch search = jikan.query().manga().search()
				.query("test")
				.page(1)
				.limit(1)
				.genres(MangaGenre.ACTION, MangaGenre.ADVENTURE)
				.status(MangaStatus.COMPLETED)
				.orderBy(MangaOrderBy.ID)
				.sort(Sort.ASCENDING)
				.rated(AgeRating.PG)
				.score(1.0F)
				.startDate(LocalDate.parse("2020-01-01"))
				.endDate(LocalDate.parse("2020-12-31"))
				.execute().block();

		assertNotNull(search);
		assertNotNull(search.toString());
		assertNotNull(search.results);
		assertEquals(20, search.lastPage);

		/* Results */
		Iterator<Manga> resultsIt = search.results.iterator();

		Manga m1 = resultsIt.next();
		assertNotNull(m1.toString());
		assertEquals(2915, m1.malId);
		assertEquals("https://myanimelist.net/manga/2915/Testarotho", m1.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/200365.jpg?s=2925c3aa1fca9d2e89f5480cfbf95984", m1.imageUrl);
		assertEquals("Testarotho", m1.title);
		assertFalse(m1.publishing);
		assertEquals("Capria has spent her life sheltered...", m1.synopsis);
		assertEquals(MangaType.MANGA, m1.type);
		assertEquals(26, m1.chapters);
		assertEquals(4, m1.volumes);
		assertEquals(6.60F, m1.score);
//		assertEquals(OffsetDateTime.parse("2000-06-09T00:00:00+00:00"), m1.startDate); // TODO
//		assertEquals(OffsetDateTime.parse("2002-10-09T00:00:00+00:00"), m1.endDate);
		assertEquals(612, m1.members);

		Manga m2 = resultsIt.next();
		assertNotNull(m2.toString());
		assertEquals(12087, m2.malId);
		assertEquals("https://myanimelist.net/manga/12087/Testify", m2.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/16557.jpg?s=a0a3be348323d317207d9aa35db7c3a3", m2.imageUrl);
		assertEquals("Testify", m2.title);
		assertFalse(m2.publishing);
		assertEquals("", m2.synopsis);
		assertEquals(MangaType.ONESHOT, m2.type);
		assertEquals(1, m2.chapters);
		assertEquals(0, m2.volumes);
		assertEquals(5.73F, m2.score);
//		assertEquals(OffsetDateTime.parse("2004-11-17T00:00:00+00:00"), m2.startDate); // TODO
//		assertEquals(OffsetDateTime.parse("2004-11-17T00:00:00+00:00"), m2.endDate);
		assertEquals(404, m2.members);

		assertFalse(resultsIt.hasNext());
	}

}
