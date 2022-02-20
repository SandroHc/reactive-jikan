/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

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

public class AnimeRelationsQueryTest extends QueryTest {

	@Test
	void fetchAnimeRelations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/relations", "anime/getAnimeRelations.json");

		/* Act */
		AnimeRelationsQuery query = jikan.query().anime().relations(11757);
		Collection<Related> relatedList = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/relations");

		// Relations
		assertThat(relatedList).isNotNull();
		assertThat(relatedList).hasSize(1);

		Related related = relatedList.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(related.toString()).isNotNull();
		softly.assertThat(related.relation).isEqualTo(RelatedType.ADAPTATION);
		softly.assertThat(related.entry)
				.extracting(r -> r.malId, r -> r.url, r -> r.name, r -> r.type)
				.containsExactlyInAnyOrder(
						tuple(21479, "https://myanimelist.net/manga/21479/Sword_Art_Online", "Sword Art Online", Type.MANGA),
						tuple(43921, "https://myanimelist.net/manga/43921/Sword_Art_Online__Progressive", "Sword Art Online: Progressive", Type.MANGA)
				);
		softly.assertAll();
	}
}
