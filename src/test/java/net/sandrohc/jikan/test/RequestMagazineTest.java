package net.sandrohc.jikan.test;

import java.time.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.magazine.*;
import net.sandrohc.jikan.model.legacy.manga.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestMagazineTest extends RequestTest {

	@Test
	void fetchMagazine() throws JikanInvalidArgumentException {
		// http://api.jikan.moe/v3/magazine/83/1
		String response = "{\n" +
				"    \"meta\": {\n" +
				"        \"mal_id\": 83,\n" +
				"        \"type\": \"manga\",\n" +
				"        \"name\": \"Shounen Jump (Weekly)\",\n" +
				"        \"url\": \"https://myanimelist.net/manga/magazine/83/Shounen_Jump_Weekly\"\n" +
				"    },\n" +
				"    \"manga\": [\n" +
				"        {\n" +
				"            \"mal_id\": 11,\n" +
				"            \"url\": \"https://myanimelist.net/manga/11/Naruto\",\n" +
				"            \"title\": \"Naruto\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/117681.jpg?s=c0ee4733879676bb2ab708f8c983dec8\",\n" +
				"            \"synopsis\": \"Whenever Naruto Uzumaki proclaims that he will someday become the Hokage...\",\n" +
				"            \"type\": \"Manga\",\n" +
				"            \"publishing_start\": \"1999-09-20T00:00:00+00:00\",\n" +
				"            \"volumes\": 72,\n" +
				"            \"members\": 306486,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 31,\n" +
				"                    \"type\": \"manga\",\n" +
				"                    \"name\": \"Super Power\",\n" +
				"                    \"url\": \"https://myanimelist.net/manga/genre/31/Super_Power\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"authors\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1879,\n" +
				"                    \"type\": \"people\",\n" +
				"                    \"name\": \"Kishimoto, Masashi\",\n" +
				"                    \"url\": \"https://myanimelist.net/people/1879/Masashi_Kishimoto\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": 8.07,\n" +
				"            \"serialization\": [\n" +
				"                \"Shounen Jump (Weekly)\"\n" +
				"            ]\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/magazine/83/1", response);

		Magazine magazine = jikan.query().magazine(83, 1).execute().block();

		assertNotNull(magazine);
		assertNotNull(magazine.toString());

		/* Results */
		MalSubEntity meta = magazine.meta;
		assertEquals(83, meta.malId);
		assertEquals(Type.MANGA, meta.type);
		assertEquals("Shounen Jump (Weekly)", meta.name);
		assertEquals("https://myanimelist.net/manga/magazine/83/Shounen_Jump_Weekly", meta.url);

		MangaGenreSub manga = magazine.manga.iterator().next();
		assertNotNull(manga.toString());
		assertEquals(11, manga.malId);
		assertEquals("Naruto", manga.title);
		assertEquals("https://myanimelist.net/manga/11/Naruto", manga.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/117681.jpg?s=c0ee4733879676bb2ab708f8c983dec8", manga.imageUrl);
		assertEquals("Whenever Naruto Uzumaki proclaims that he will someday become the Hokage...", manga.synopsis);
		assertEquals(MangaType.MANGA, manga.type);
		assertEquals(LocalDate.parse("1999-09-20"), manga.published.from);
		assertEquals(72, manga.volumes);
		assertEquals(306486, manga.members);
		assertEquals(8.07F, manga.score);
		assertTrue(manga.serialization.contains("Shounen Jump (Weekly)"));

		GenreEntity<MangaGenre> genre = manga.genres.iterator().next();
		assertEquals(31, genre.malId);
		assertEquals(Type.MANGA, genre.type);
		assertEquals(MangaGenre.SUPERPOWER, genre.name);
		assertEquals("https://myanimelist.net/manga/genre/31/Super_Power", genre.url);

		MalSubEntity author = manga.authors.iterator().next();
		assertEquals(1879, author.malId);
		assertEquals(Type.PERSON, author.type);
		assertEquals("Kishimoto, Masashi", author.name);
		assertEquals("https://myanimelist.net/people/1879/Masashi_Kishimoto", author.url);
	}

	@Test
	void fetchMagazine_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().magazine(10, 0), "page starts at index 1");
	}

}
