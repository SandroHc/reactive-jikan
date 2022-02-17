/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.test.RequestTest;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ClubRelationsQueryTest extends RequestTest {

	@Test
	void fetchClubRelations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/clubs/1/relations", 1, "clubs/getClubRelations.json");

		/* Act */
		ClubRelationsQuery query = jikan.query().club().relations(1);
		List<EntityWithType> related = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/clubs/1/relations");

		// Related
		assertThat(related).isNotNull();

		assertThat(related)
				.extracting(r -> r.malId, r -> r.type, r -> r.name, r -> r.url)
				.containsExactly(
						tuple(20, Type.ANIME, "Cowboy Bebop", "https://myanimelist.net/anime/20"),
						tuple(30, Type.MANGA, "Cowboy Bebop", "https://myanimelist.net/manga/30"),
						tuple(40, Type.CHARACTER, "Wen", "https://myanimelist.net/character/40")
				);

		for (EntityWithType entity : related) {
			assertThat(entity).isNotNull();
			assertThat(entity).isEqualTo(entity);
			assertThat(entity).isEqualTo(SerializationUtils.clone(entity));
			assertThat(entity).isNotEqualTo(new Object());
			assertThat(entity).hasSameHashCodeAs(SerializationUtils.clone(entity));
		}
	}
}
