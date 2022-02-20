/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeEpisodeQueryTest extends QueryTest {

	@Test
	void fetchAnimeEpisodeDetails() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/episodes/1", "anime/getAnimeEpisodeById.json");

		/* Act */
		AnimeEpisodeQuery query = jikan.query().anime().episode(11757, 1);
		AnimeEpisode episode = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/episodes/1");

		// Episode
		softly = new SoftAssertions();
		softly.assertThat(episode).isNotNull();
		softly.assertThat(episode.malId).isEqualTo(1);
		softly.assertThat(episode.url).isEqualTo("https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1");
		softly.assertThat(episode.title).isEqualTo("The World of Swords");
		softly.assertThat(episode.titleJapanese).isEqualTo("剣の世界");
		softly.assertThat(episode.titleRomanji).isEqualTo("Ken no Sekai");
		softly.assertThat(episode.duration).isEqualTo(Duration.ofMinutes(24));
		softly.assertThat(episode.aired).isEqualTo(LocalDate.of(2012, Month.JULY, 7).atTime(15, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(episode.filler).isFalse();
		softly.assertThat(episode.recap).isFalse();
		softly.assertThat(episode.forumUrl).isNull();
		softly.assertThat(episode.synopsis).isEqualTo("A virtual reality gaming system known as the \"Nerve Gear\" is finally released to the public alongside the world's first virtual reality game called \"Sword Art Online\" (SAO). Kirito, one of the game's beta-testers, logs-in to Aincrad, the world of SAO and quickly befriends novice player Klein, to whom he teaches the basics of the game. After a few hours, Kirito and Klein discover that they cannot log-out. Akihiko Kayaba, the game's creator, announces to the players that he intentionally removed the log-out option and told all player will die if the Nerve Gear is forcefully pull out or health bar is depleted to zero in the game itself, only way to escape is to beat and clear all 100 floors of Aincrad. If they die in the game, their real bodies will perish as well. (Source: Wikipedia)");
		softly.assertAll();
	}
}
