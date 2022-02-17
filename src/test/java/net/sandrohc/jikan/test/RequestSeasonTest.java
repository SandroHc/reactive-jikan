package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;
import java.util.stream.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.season.SeasonQuery;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestSeasonTest extends RequestTest {

	@Test
	void fetchSeason() {
		// https://api.jikan.moe/v3/season/2020/summer

		mock(mockServer, "/season/2020/summer", response);

		SeasonQuery query = jikan.query().season().get(2020, Season.SUMMER);
		Collection<Anime> results = query.execute().collectList().block();

		assertThat(results).hasSize(1);

		/* Results */
		Anime anime = results.iterator().next();

		assertThat(anime).isNotNull();
		assertThat(anime.toString()).isNotNull();
		assertThat(anime.malId).isEqualTo(39587);
		assertThat(anime.url).isEqualTo("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season");
		assertThat(anime.title).isEqualTo("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season");
		assertThat(anime.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1444/108005.jpg");
		assertThat(anime.synopsis).isEqualTo("Even after dying countless times, Subaru finally ended the threat...");
		assertThat(anime.type).isEqualTo(AnimeType.TV);
		assertThat(anime.aired.from).isEqualTo(OffsetDateTime.parse("2020-07-08T13:30:00+00:00"));
		assertThat(anime.episodes).isEqualTo(13);
		assertThat(anime.members).isEqualTo(290261);
		assertThat(anime.source).isEqualTo("Light novel");
		assertThat(anime.score).isEqualTo(8.5F);
		assertThat(anime.r18).isFalse();
		assertThat(anime.kids).isFalse();
		assertThat(anime.continuing).isFalse();
		List<AnimeGenre> animeGenres = anime.genres.stream().map(GenreEntity::getName).collect(Collectors.toList());
		assertThat(animeGenres).containsExactly(AnimeGenre.DRAMA, AnimeGenre.FANTASY, AnimeGenre.PSYCHOLOGICAL, AnimeGenre.THRILLER);

		EntityWithType producer = anime.producers.iterator().next();
		assertThat(314, producer.malId).isEqualTo();
		assertThat(Type.ANIME, producer.type).isEqualTo();
		assertThat("White Fox", producer.name).isEqualTo();
		assertThat("https://myanimelist.net/anime/producer/314/White_Fox", producer.url).isEqualTo();

		assertThat("Crunchyroll", anime.licensors.iterator().next()).isEqualTo();
	}

	@Test
	void fetchSeasonArchive() {
		// https://api.jikan.moe/v3/season/archive

		mock(mockServer, "/season/archive", response);

		List<SeasonEntry> results = jikan.query().season().archive()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();
		assertThat(new SeasonArchive().toString()).isNotNull();

		/* Results */
		Iterator<SeasonEntry> yearIt = results.iterator();

		SeasonEntry year1 = yearIt.next();
		assertThat(year1).isNotNull();
		assertThat(year1.toString()).isNotNull();
		assertThat(2020, year1.year).isEqualTo();
		assertTrue(year1.seasons.containsAll(Arrays.asList(
				Season.WINTER,
				Season.SPRING,
				Season.SUMMER,
				Season.FALL
		)));

		SeasonEntry year2 = yearIt.next();
		assertThat(year2).isNotNull();
		assertThat(year2.toString()).isNotNull();
		assertThat(1917, year2.year).isEqualTo();
		assertTrue(year2.seasons.containsAll(Arrays.asList(
				Season.WINTER,
				Season.SPRING,
				Season.SUMMER,
				Season.FALL
		)));

		assertThat(yearIt.hasNext()).isFalse();
	}

	@Test
	void fetchSeasonLater() {
		// https://api.jikan.moe/v3/season/later

		mock(mockServer, "/season/later", response);

		Collection<SeasonAnime> results = jikan.query().season().later()
				.execute()
				.collectList()
				.block();

		assertThat(results).isNotNull();

		/* Results */
		SeasonAnime anime = results.iterator().next();

		assertThat(anime).isNotNull();
		assertThat(anime.toString()).isNotNull();
		assertThat(40028, anime.malId).isEqualTo();
		assertThat("https://myanimelist.net/anime/40028/Shingeki_no_Kyojin__The_Final_Season", anime.url).isEqualTo();
		assertThat("Shingeki no Kyojin: The Final Season", anime.title).isEqualTo();
		assertThat("https://cdn.myanimelist.net/images/anime/1384/107759.jpg", anime.imageUrl).isEqualTo();
		assertThat("Final Season of Shingeki no Kyojin.", anime.synopsis).isEqualTo();
		assertThat(AnimeType.TV, anime.type).isEqualTo();
		assertThat(anime.airingStart).isNull();
		assertThat(anime.episodes).isNull();
		assertThat(126784, anime.members).isEqualTo();
		assertThat("Manga", anime.source).isEqualTo();
		assertThat(anime.score).isNull();
		assertThat(anime.r18).isFalse();
		assertThat(anime.kids).isFalse();
		assertThat(anime.continuing).isFalse();

		Iterator<GenreEntity<AnimeGenre>> genresIt = anime.genres.iterator();
		assertThat(AnimeGenre.ACTION, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.MILITARY, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.MYSTERY, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.SUPERPOWER, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.DRAMA, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.FANTASY, genresIt.next().name).isEqualTo();
		assertThat(AnimeGenre.SHOUNEN, genresIt.next().name).isEqualTo();

		EntityWithType producer = anime.producers.iterator().next();
		assertThat(569, producer.malId).isEqualTo();
		assertThat(Type.ANIME, producer.type).isEqualTo();
		assertThat("MAPPA", producer.name).isEqualTo();
		assertThat("https://myanimelist.net/anime/producer/569/MAPPA", producer.url).isEqualTo();

		assertTrue(anime.licensors.isEmpty());
	}

}
