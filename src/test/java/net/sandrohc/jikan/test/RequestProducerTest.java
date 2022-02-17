package net.sandrohc.jikan.test;

import java.time.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestProducerTest extends RequestTest {

	@Test
	void fetchProducer() {
		// http://api.jikan.moe/v3/producer/43/1

		mock(mockServer, "/producer/43/1", response);

		Producer producer = jikan.query().producer(43, 1).execute().block();

		// Query
		assertThat(producer).isNotNull();
		assertThat(producer.toString()).isNotNull();

		// Results
		MalSubEntity meta = producer.meta;
		assertThat(meta.malId).isEqualTo(43);
		assertThat(meta.type).isEqualTo(Type.ANIME);
		assertThat(meta.name).isEqualTo("ufotable");
		assertThat(meta.url).isEqualTo("https://myanimelist.net/anime/producer/43/ufotable");

		SeasonAnime anime = producer.anime.iterator().next();
		assertThat(anime.toString()).isNotNull();
		assertThat(anime.malId).isEqualTo(10087);
		assertThat(anime.title).isEqualTo("Fate/Zero");
		assertThat(anime.url).isEqualTo("https://myanimelist.net/anime/10087/Fate_Zero");
		assertThat(anime.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/2/73249.jpg");
		assertThat(the omnipotent Holy Grail triggered three wars in the past...", anime.synopsis).isEqualTo("With the promise of granting any wish);
		assertThat(anime.type).isEqualTo(AnimeType.TV);
		assertThat(anime.airingStart).isEqualTo(OffsetDateTime.parse("2011-10-01T15:00:00+00:00"));
		assertThat(anime.episodes).isEqualTo(13);
		assertThat(anime.members).isEqualTo(938115);
		assertThat(anime.source).isEqualTo("Light novel");
		assertThat(anime.score).isEqualTo(8.38F);
		assertThat(anime.r18).isFalse();
		assertThat(anime.kids).isFalse();
		assertThat(anime.licensors.contains("Aniplex of America")).isTrue();

		GenreEntity<AnimeGenre> genre = anime.genres.iterator().next();
		assertThat(genre.malId).isEqualTo(1);
		assertThat(genre.type).isEqualTo(Type.ANIME);
		assertThat(genre.name).isEqualTo(AnimeGenre.ACTION);
		assertThat(genre.url).isEqualTo("https://myanimelist.net/anime/genre/1/Action");

		MalSubEntity animeProducer = anime.producers.iterator().next();
		assertThat(animeProducer.malId).isEqualTo(43);
		assertThat(animeProducer.type).isEqualTo(Type.ANIME);
		assertThat(animeProducer.name).isEqualTo("ufotable");
		assertThat(animeProducer.url).isEqualTo("https://myanimelist.net/anime/producer/43/ufotable");
	}
}
