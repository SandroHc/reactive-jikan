/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaTopQueryTest extends RequestTest {

	@Test
	void fetchMangaTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/top/manga", 1, "manga/getTopManga.json");

		/* Act */
		MangaTopQuery query = jikan.query().manga().top();
		Collection<Manga> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/top/manga");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Manga result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(121496);
		softly.assertThat(result.title).isEqualTo("Solo Leveling");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/121496/Solo_Leveling");
		softly.assertThat(result.type).isEqualTo(MangaType.MANHWA);
		softly.assertThat(result.score).isEqualTo(8.94F);
		softly.assertAll();
	}
}
