/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaQueryTest extends RequestTest {

	@Test
	void fetchMangaDetails() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/manga/23390", 1, "manga/getMangaById.json");

		/* Act */
		MangaQuery query = jikan.query().manga().get(96792);
		Manga manga = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/manga/23390");

		// Manga
		softly = new SoftAssertions();
		softly.assertThat(manga).isNotNull();
		softly.assertThat(manga.toString()).isNotNull();
		softly.assertThat(manga.malId).isEqualTo(96792);
		softly.assertThat(manga.url).isEqualTo("https://myanimelist.net/manga/96792/Kimetsu_no_Yaiba");
		softly.assertThat(manga.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/manga/3/179023.jpg");
		softly.assertThat(manga.title).isEqualTo("Kimetsu no Yaiba");
		softly.assertThat(manga.titleEnglish).isEqualTo("Demon Slayer: Kimetsu no Yaiba");
		softly.assertThat(manga.titleJapanese).isEqualTo("鬼滅の刃");
		softly.assertThat(manga.titleSynonyms).containsExactly("Blade of Demon Destruction");
		softly.assertThat(manga.type).isEqualTo(MangaType.MANGA);
		softly.assertThat(manga.chapters).isEqualTo(207);
		softly.assertThat(manga.volumes).isEqualTo(1);
		softly.assertThat(manga.publishing).isFalse();
		softly.assertThat(manga.published.from).isEqualTo(LocalDate.of(2016, Month.FEBRUARY, 15));
		softly.assertThat(manga.published.to).isEqualTo(LocalDate.of(2020, Month.MAY, 18));
		softly.assertThat(manga.score).isEqualTo(8.38F);
		softly.assertThat(manga.scoredBy).isEqualTo(62398);
		softly.assertThat(manga.rank).isEqualTo(185);
		softly.assertThat(manga.popularity).isEqualTo(27);
		softly.assertThat(manga.members).isEqualTo(118299);
		softly.assertThat(manga.favorites).isEqualTo(10370);
		softly.assertThat(manga.synopsis).isEqualTo("Tanjirou Kamado lives with his impoverished family on a remote mountain...");
		softly.assertThat(manga.background).isEqualTo("As a part of the JUMP START initiative...");
		softly.assertThat(manga.authors)
				.extracting(a -> a.malId, a -> a.url, a -> a.name, a -> a.type)
				.containsExactly(
						tuple(1, "URl", "NAME", Type.PERSON)
				);
		softly.assertThat(manga.serializations)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactly(
						tuple(1, "URl", "NAME", Type.PERSON)
				);
		softly.assertThat(manga.genres)
				.extracting(g -> g.malId, g -> g.type, g -> g.name, g -> g.url)
				.containsExactly(
						tuple(1, Type.PERSON, MangaGenre.ACTION, "URL")
				);
		softly.assertThat(manga.explicitGenres)
				.extracting(g -> g.malId, g -> g.type, g -> g.name, g -> g.url)
				.containsExactly(
						tuple(1, Type.PERSON, MangaGenre.ACTION, "URL")
				);
		softly.assertThat(manga.themes)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactly(
						tuple(1, "URl", "NAME", Type.PERSON)
				);
		softly.assertThat(manga.demographics)
				.extracting(s -> s.malId, s -> s.url, s -> s.name, s -> s.type)
				.containsExactly(
						tuple(1, "URl", "NAME", Type.PERSON)
				);
		softly.assertAll();
	}
}
