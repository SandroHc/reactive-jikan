/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchMangaDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390", "manga/getMangaById.json");

		/* Act */
		MangaQuery query = jikan.query().manga().get(23390);
		Manga manga = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390");

		// Manga
		softly = new SoftAssertions();
		softly.assertThat(manga).isNotNull();
		softly.assertThat(manga.toString()).isNotNull();
		softly.assertThat(manga.malId).isEqualTo(23390);
		softly.assertThat(manga.url).isEqualTo("https://myanimelist.net/manga/23390/Shingeki_no_Kyojin");
		softly.assertThat(manga.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/2/37846.jpg");
		softly.assertThat(manga.title).isEqualTo("Shingeki no Kyojin");
		softly.assertThat(manga.titleEnglish).isEqualTo("Attack on Titan");
		softly.assertThat(manga.titleJapanese).isEqualTo("進撃の巨人");
		softly.assertThat(manga.titleSynonyms).containsExactlyInAnyOrder();
		softly.assertThat(manga.type).isEqualTo(MangaType.MANGA);
		softly.assertThat(manga.chapters).isEqualTo(141);
		softly.assertThat(manga.volumes).isEqualTo(34);
		softly.assertThat(manga.publishing).isFalse();
		softly.assertThat(manga.published.from).isEqualTo(LocalDate.of(2009, Month.SEPTEMBER,9).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(manga.published.to).isEqualTo(LocalDate.of(2021, Month.APRIL, 9).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(manga.score).isEqualTo(8.58D);
		softly.assertThat(manga.scoredBy).isEqualTo(337814);
		softly.assertThat(manga.rank).isEqualTo(80);
		softly.assertThat(manga.popularity).isEqualTo(1);
		softly.assertThat(manga.members).isEqualTo(555038);
		softly.assertThat(manga.favorites).isEqualTo(65044);
		softly.assertThat(manga.synopsis).startsWith("Hundreds of years ago, horrifying creatures which resembled humans appeared. These mindless, towering giants");
		softly.assertThat(manga.synopsis).hasSize(1182);
		softly.assertThat(manga.background).startsWith("Shingeki no Kyojin won the Kodansha Manga Award in the shounen category in 2011 and was nominated for both");
		softly.assertThat(manga.background).hasSize(1654);
		softly.assertThat(manga.authors)
				.extracting(a -> a.malId, a -> a.url, a -> a.name, a -> a.type)
				.containsExactlyInAnyOrder(
						tuple(11705, "https://myanimelist.net/people/11705/Hajime_Isayama", "Isayama, Hajime", Type.PERSON)
				);
		softly.assertThat(manga.serializations)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactlyInAnyOrder(
						tuple(450, "https://myanimelist.net/manga/magazine/450/Bessatsu_Shounen_Magazine", "Bessatsu Shounen Magazine", Type.MANGA)
				);
		softly.assertThat(manga.genres)
				.extracting(g -> g.malId, g -> g.type, g -> g.name, g -> g.url)
				.containsExactlyInAnyOrder(
						tuple(1, Type.MANGA, Genre.ACTION, "https://myanimelist.net/manga/genre/1/Action"),
						tuple(8, Type.MANGA, Genre.DRAMA, "https://myanimelist.net/manga/genre/8/Drama"),
						tuple(10, Type.MANGA, Genre.FANTASY, "https://myanimelist.net/manga/genre/10/Fantasy"),
						tuple(7, Type.MANGA, Genre.MYSTERY, "https://myanimelist.net/manga/genre/7/Mystery")
				);
		softly.assertThat(manga.explicitGenres)
				.extracting(g -> g.malId, g -> g.type, g -> g.name, g -> g.url)
				.containsExactlyInAnyOrder(
				);
		softly.assertThat(manga.themes)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactlyInAnyOrder(
						tuple(38, "https://myanimelist.net/manga/genre/38/Military", "Military", Type.MANGA),
						tuple(31, "https://myanimelist.net/manga/genre/31/Super_Power", "Super Power", Type.MANGA)
				);
		softly.assertThat(manga.demographics)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactlyInAnyOrder(
						tuple(27, "https://myanimelist.net/manga/genre/27/Shounen", "Shounen", Type.MANGA)
				);
		softly.assertAll();
	}
}
