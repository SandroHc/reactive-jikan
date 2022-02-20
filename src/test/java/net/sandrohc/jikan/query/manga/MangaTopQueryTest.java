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
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaTopQueryTest extends QueryTest {

	@Test
	void fetchMangaTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/top/manga", "top/getTopManga.json");

		/* Act */
		MangaTopQuery query = jikan.query().manga().top();
		Collection<Manga> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/top/manga");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Manga result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(2);
		softly.assertThat(result.title).isEqualTo("Berserk");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/2/Berserk");
		softly.assertThat(result.type).isEqualTo(MangaType.MANGA);
		softly.assertThat(result.score).isEqualTo(9.43D);
		softly.assertAll();
	}
}
