/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class MangaRelationsQueryTest extends QueryTest {

	@Test
	void fetchMangaRelations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/relations", "manga/getMangaRelations.json");

		/* Act */
		MangaRelationsQuery query = jikan.query().manga().relations(23390);
		Collection<Related> relatedList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/relations");

		// Relations
		assertThat(relatedList).isNotNull();
		assertThat(relatedList).hasSize(1);

		Related related = relatedList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(related.toString()).isNotNull();
		softly.assertThat(related.relation).isEqualTo(RelatedType.ALTERNATIVE_VERSION);
		softly.assertThat(related.entry)
				.extracting(r -> r.malId, r -> r.url, r -> r.name, r -> r.type)
				.containsExactlyInAnyOrder(
						tuple(58203, "https://myanimelist.net/manga/58203/Shingeki_no_Kyojin_Volume_0", "Shingeki no Kyojin Volume 0", Type.MANGA)
				);
		softly.assertAll();
	}
}
