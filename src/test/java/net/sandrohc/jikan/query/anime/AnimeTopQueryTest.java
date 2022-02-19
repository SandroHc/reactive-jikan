/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeTopQueryTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeAnimeTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/top/anime", "top/getTopAnime.json");

		/* Act */
		AnimeTopQuery query = jikan.query().anime().top();
		Collection<Anime> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/top/anime");

		// Top
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Anime result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(39587);
		softly.assertThat(result.rank).isEqualTo(3);
		softly.assertThat(result.title).isEqualTo("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1444/108005.jpg?s=b998e66dcfad4bbd4510b9ece4c9eb99");
		softly.assertThat(result.type).isEqualTo(AnimeType.TV);
		softly.assertThat(result.episodes).isEqualTo(13);
		softly.assertThat(result.aired.from).isEqualTo("Jul 2020");
		softly.assertThat(result.aired.to).isNull();
		softly.assertThat(result.members).isEqualTo(290261);
		softly.assertThat(result.score).isEqualTo(8.5D);
		softly.assertAll();
	}
}
