/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.magazine;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MagazineQueryTest extends QueryTest {

	@Test
	void fetchMagazines() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/magazines", "magazines/getMagazines.json");

		/* Act */
		MagazineQuery query = jikan.query().magazine().list();
		Collection<MagazineWithCount> magazines = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/magazines");

		/* Results */
		softly = new SoftAssertions();
		softly.assertThat(magazines).isNotNull();
		softly.assertThat(magazines).hasSize(1);
		softly.assertThat(magazines)
				.extracting(m -> m.malId, m -> m.url, m -> m.name, m -> m.count)
				.containsExactlyInAnyOrder(
						tuple(1, "https://myanimelist.net/manga/magazine/1/Big_Comic_Original", "Big Comic Original", 66)
				);
		softly.assertAll();
	}
}
