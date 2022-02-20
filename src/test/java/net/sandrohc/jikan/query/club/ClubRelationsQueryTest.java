/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryTest;
import org.apache.commons.lang3.SerializationUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class ClubRelationsQueryTest extends QueryTest {

	@Test
	void fetchClubRelations() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/clubs/1/relations", "clubs/getClubRelations.json");

		/* Act */
		ClubRelationsQuery query = jikan.query().club().relations(1);
		ClubRelations related = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/clubs/1/relations");

		// Related
		assertThat(related).isNotNull();

		// Related - Anime
		softly = new SoftAssertions();
		softly.assertThat(related.anime).isNotNull();
		softly.assertThat(related.anime).hasSize(1);
		softly.assertThat(related.anime)
				.extracting(r -> r.malId, r -> r.type, r -> r.name, r -> r.url)
				.containsExactlyInAnyOrder(
						tuple(1, Type.ANIME, "Cowboy Bebop", "https://myanimelist.net/anime/1")
				);
		softly.assertAll();

		// Related - Manga
		softly = new SoftAssertions();
		softly.assertThat(related.manga).isNotNull();
		softly.assertThat(related.manga).hasSize(1);
		softly.assertThat(related.manga)
				.extracting(r -> r.malId, r -> r.type, r -> r.name, r -> r.url)
				.containsExactlyInAnyOrder(
						tuple(173, Type.MANGA, "Cowboy Bebop", "https://myanimelist.net/manga/173")
				);
		softly.assertAll();

		// Related - Characters
		softly = new SoftAssertions();
		softly.assertThat(related.characters).isNotNull();
		softly.assertThat(related.characters).hasSize(1);
		softly.assertThat(related.characters)
				.extracting(r -> r.malId, r -> r.type, r -> r.name, r -> r.url)
				.containsExactlyInAnyOrder(
						tuple(19119, Type.CHARACTER, "Wen", "https://myanimelist.net/character/19119")
				);
		softly.assertAll();

		for (EntityWithType entity : related.anime) {
			assertThat(entity).isNotNull();
			assertThat(entity).isEqualTo(entity);
			assertThat(entity).isEqualTo(SerializationUtils.clone(entity));
			assertThat(entity).isNotEqualTo(new Object());
			assertThat(entity).hasSameHashCodeAs(SerializationUtils.clone(entity));
		}
	}
}
