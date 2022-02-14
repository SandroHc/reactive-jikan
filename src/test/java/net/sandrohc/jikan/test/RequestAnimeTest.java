package net.sandrohc.jikan.test;

import java.io.*;
import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.anime.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.common.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.query.anime.AnimeQuery;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestAnimeTest extends RequestTest {

	@Test
	void fetchAnime() {
		// https://api.jikan.moe/v3/anime/11757
		String response = "{" +
						  "  \"mal_id\": 1," +
						  "  \"url\": \"https://example.com/url\"," +
						  "  \"image_url\": \"https://example.com/image_url\"," +
						  "  \"trailer_url\": \"https://example.com/trailer_url\"," +
						  "  \"title\": \"TITLE\"," +
						  "  \"title_english\": \"TITLE ENGLISH\"," +
						  "  \"title_japanese\": \"TITLE 日本語\"," +
						  "  \"title_synonyms\": [ \"TITLE_SYNONYM\" ]," +
						  "  \"type\": \"TV\"," +
						  "  \"source\": \"SOURCE\"," +
						  "  \"episodes\": 10," +
						  "  \"status\": \"Finished Airing\"," +
						  "  \"airing\": false," +
						  "  \"aired\": {" +
						  "    \"from\": \"2010-01-01T00:00:00+00:00\"," +
						  "    \"to\": \"2010-12-01T00:00:00+00:00\"" +
						  "  }," +
						  "  \"duration\": \"DURATION\"," +
						  "  \"rating\": \"PG-13 - Teens 13 or older\"," +
						  "  \"score\": 10.00," +
						  "  \"scored_by\": 20," +
						  "  \"rank\": 30," +
						  "  \"popularity\": 40," +
						  "  \"members\": 50," +
						  "  \"favorites\": 60," +
						  "  \"synopsis\": \"SYNOPSIS\"," +
						  "  \"background\": \"BACKGROUND\"," +
						  "  \"premiered\": \"Summer 2012\"," +
						  "  \"broadcast\": \"Sundays at 00:00 (JST)\"," +
						  "  \"related\": {" +
						  "    \"Other\": [" +
						  "      { \"mal_id\": 10000, \"type\": \"anime\", \"name\": \"RELATED_OTHER\", \"url\": \"https://example.com/related_other\" }" +
						  "    ]," +
						  "    \"Sequel\": [" +
						  "      { \"mal_id\": 11000, \"type\": \"anime\", \"name\": \"RELATED_SEQUEL\", \"url\": \"https://example.com/related_sequel\" }" +
						  "    ]" +
						  "  }," +
						  "  \"producers\": [" +
						  "    { \"mal_id\": 1000, \"type\": \"anime\", \"name\": \"PRODUCER\", \"url\": \"https://example.com/producer\" }" +
						  "  ]," +
						  "  \"licensors\": [" +
						  "    { \"mal_id\": 2000, \"type\": \"anime\", \"name\": \"LICENSOR\", \"url\": \"https://example.com/licensor\" }" +
						  "  ]," +
						  "  \"studios\": [" +
						  "    { \"mal_id\": 3000, \"type\": \"anime\", \"name\": \"STUDIO\", \"url\": \"https://example.com/studio\" }" +
						  "  ]," +
						  "  \"genres\": [" +
						  "    { \"mal_id\": 1, \"type\": \"anime\", \"name\": \"Action\", \"url\": \"https://myanimelist.net/anime/genre/1/Action\" }," +
						  "    { \"mal_id\": 2, \"type\": \"anime\", \"name\": \"Adventure\", \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\" }" +
						  "  ]," +
						  "  \"opening_themes\": [ \"OPENING THEME\" ]," +
						  "  \"ending_themes\":  [ \"ENDING THEME\" ]" +
						  "}";

		mock(mockServer, "/anime/1", response);

		AnimeQuery query = jikan.query().anime().get(1);
		assertNotNull(query);
		assertNotNull(query.toString());

		Anime anime = query.execute().block();

		assertNotNull(anime);
		assertNotNull(anime.toString());
		assertEquals(1, anime.malId);
		assertEquals("https://example.com/url", anime.url);
		assertEquals("https://example.com/image_url", anime.imageUrl);
		assertEquals("https://example.com/trailer_url", anime.trailerUrl);
		assertEquals("TITLE", anime.title);
		assertEquals("TITLE ENGLISH", anime.titleEnglish);
		assertEquals("TITLE 日本語", anime.titleJapanese);
		assertEquals("TITLE_SYNONYM", anime.titleSynonyms.iterator().next());
		assertEquals(AnimeType.TV, anime.type);
		assertEquals("SOURCE", anime.source);
		assertEquals(10, anime.episodes);
		assertEquals(AnimeStatus.COMPLETED, anime.status);
		assertEquals(LocalDate.parse("2010-01-01"), anime.aired.from);
		assertEquals(LocalDate.parse("2010-12-01"), anime.aired.to);
		assertEquals("DURATION", anime.duration);
		assertEquals(AgeRating.PG13, anime.rating);
		assertEquals(10.0F, anime.score);
		assertEquals(20, anime.scoredBy);
		assertEquals(30, anime.rank);
		assertEquals(40, anime.popularity);
		assertEquals(50, anime.members);
		assertEquals(60, anime.favorites);
		assertEquals("SYNOPSIS", anime.synopsis);
		assertEquals("BACKGROUND", anime.background);
		assertEquals("Summer 2012", anime.premiered);
		assertEquals("Sundays at 00:00 (JST)", anime.broadcast);
		assertEquals("OPENING THEME", anime.openingThemes.iterator().next());
		assertEquals("ENDING THEME", anime.endingThemes.iterator().next());

		assertEquals("RELATED_OTHER", anime.related.others.iterator().next().name);
		assertEquals("RELATED_SEQUEL", anime.related.sequels.iterator().next().name);
		assertTrue(anime.related.prequels.isEmpty());
		assertTrue(anime.related.alternativeVersions.isEmpty());
		assertTrue(anime.related.alternativeSettings.isEmpty());
		assertTrue(anime.related.characters.isEmpty());
		assertTrue(anime.related.spinOffs.isEmpty());
		assertTrue(anime.related.adaptations.isEmpty());
		assertTrue(anime.related.summaries.isEmpty());
		assertTrue(anime.related.sideStories.isEmpty());
		assertTrue(anime.related.parentStories.isEmpty());
		assertTrue(anime.related.fullStories.isEmpty());

		Iterator<GenreEntity<AnimeGenre>> genresIt = anime.genres.iterator();
		GenreEntity<AnimeGenre> genre1 = genresIt.next();
		GenreEntity<AnimeGenre> genre2 = genresIt.next();
		assertFalse(genresIt.hasNext());
		assertNotNull(genre1.toString());
		assertEquals(AnimeGenre.ACTION, genre1.name);
		assertEquals(AnimeGenre.ADVENTURE, genre2.name);

		MalSubEntity producer = anime.producers.iterator().next();
		assertEquals(1000, producer.malId);
		assertEquals(Type.ANIME, producer.type);
		assertEquals("PRODUCER", producer.name);
		assertEquals("https://example.com/producer", producer.url);

		MalSubEntity licensor = anime.licensors.iterator().next();
		assertEquals(2000, licensor.malId);
		assertEquals(Type.ANIME, licensor.type);
		assertEquals("LICENSOR", licensor.name);
		assertEquals("https://example.com/licensor", licensor.url);

		MalSubEntity studio = anime.studios.iterator().next();
		assertEquals(3000, studio.malId);
		assertEquals(Type.ANIME, studio.type);
		assertEquals("STUDIO", studio.name);
		assertEquals("https://example.com/studio", studio.url);
	}

	@Test
	void fetchAnimeWithUnknownAgeRating() {
		// https://api.jikan.moe/v3/anime/11757
		String response = "{" +
				"  \"mal_id\": 1," +
				"  \"url\": \"https://example.com/url\"," +
				"  \"image_url\": \"https://example.com/image_url\"," +
				"  \"trailer_url\": \"https://example.com/trailer_url\"," +
				"  \"title\": \"TITLE\"," +
				"  \"title_english\": \"TITLE ENGLISH\"," +
				"  \"title_japanese\": \"TITLE 日本語\"," +
				"  \"title_synonyms\": [ \"TITLE_SYNONYM\" ]," +
				"  \"type\": \"TV\"," +
				"  \"source\": \"SOURCE\"," +
				"  \"episodes\": 10," +
				"  \"status\": \"Finished Airing\"," +
				"  \"airing\": false," +
				"  \"aired\": {" +
				"    \"from\": \"2010-01-01T00:00:00+00:00\"," +
				"    \"to\": \"2010-12-01T00:00:00+00:00\"" +
				"  }," +
				"  \"duration\": \"DURATION\"," +
				"  \"rating\": \"Invalid age rating\"," + // unknown age rating
				"  \"score\": 10.00," +
				"  \"scored_by\": 20," +
				"  \"rank\": 30," +
				"  \"popularity\": 40," +
				"  \"members\": 50," +
				"  \"favorites\": 60," +
				"  \"synopsis\": \"SYNOPSIS\"," +
				"  \"background\": \"BACKGROUND\"," +
				"  \"premiered\": \"Summer 2012\"," +
				"  \"broadcast\": \"Sundays at 00:00 (JST)\"," +
				"  \"related\": {" +
				"    \"Other\": [" +
				"      { \"mal_id\": 10000, \"type\": \"anime\", \"name\": \"RELATED_OTHER\", \"url\": \"https://example.com/related_other\" }" +
				"    ]," +
				"    \"Sequel\": [" +
				"      { \"mal_id\": 11000, \"type\": \"anime\", \"name\": \"RELATED_SEQUEL\", \"url\": \"https://example.com/related_sequel\" }" +
				"    ]" +
				"  }," +
				"  \"producers\": [" +
				"    { \"mal_id\": 1000, \"type\": \"anime\", \"name\": \"PRODUCER\", \"url\": \"https://example.com/producer\" }" +
				"  ]," +
				"  \"licensors\": [" +
				"    { \"mal_id\": 2000, \"type\": \"anime\", \"name\": \"LICENSOR\", \"url\": \"https://example.com/licensor\" }" +
				"  ]," +
				"  \"studios\": [" +
				"    { \"mal_id\": 3000, \"type\": \"anime\", \"name\": \"STUDIO\", \"url\": \"https://example.com/studio\" }" +
				"  ]," +
				"  \"genres\": [" +
				"    { \"mal_id\": 1, \"type\": \"anime\", \"name\": \"Action\", \"url\": \"https://myanimelist.net/anime/genre/1/Action\" }," +
				"    { \"mal_id\": 2, \"type\": \"anime\", \"name\": \"Adventure\", \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\" }" +
				"  ]," +
				"  \"opening_themes\": [ \"OPENING THEME\" ]," +
				"  \"ending_themes\":  [ \"ENDING THEME\" ]" +
				"}";

		mock(mockServer, "/anime/1", response);

		AnimeQuery query = jikan.query().anime().get(1);
		assertNotNull(query);
		assertNotNull(query.toString());
		Anime anime = query.execute().block();
		assertNotNull(anime);
		assertEquals(AgeRating.NONE, anime.rating);
	}

	@Test
	void fetchAnimeWithUnknownType() {
		// https://api.jikan.moe/v3/anime/11757
		String response = "{" +
				"  \"mal_id\": 1," +
				"  \"url\": \"https://example.com/url\"," +
				"  \"image_url\": \"https://example.com/image_url\"," +
				"  \"trailer_url\": \"https://example.com/trailer_url\"," +
				"  \"title\": \"TITLE\"," +
				"  \"title_english\": \"TITLE ENGLISH\"," +
				"  \"title_japanese\": \"TITLE 日本語\"," +
				"  \"title_synonyms\": [ \"TITLE_SYNONYM\" ]," +
				"  \"type\": \"INVALID TYPE\"," +
				"  \"source\": \"SOURCE\"," +
				"  \"episodes\": 10," +
				"  \"status\": \"Finished Airing\"," +
				"  \"airing\": false," +
				"  \"aired\": {" +
				"    \"from\": \"2010-01-01T00:00:00+00:00\"," +
				"    \"to\": \"2010-12-01T00:00:00+00:00\"" +
				"  }," +
				"  \"duration\": \"DURATION\"," +
				"  \"rating\": \"Invalid age rating\"," + // unknown age rating
				"  \"score\": 10.00," +
				"  \"scored_by\": 20," +
				"  \"rank\": 30," +
				"  \"popularity\": 40," +
				"  \"members\": 50," +
				"  \"favorites\": 60," +
				"  \"synopsis\": \"SYNOPSIS\"," +
				"  \"background\": \"BACKGROUND\"," +
				"  \"premiered\": \"Summer 2012\"," +
				"  \"broadcast\": \"Sundays at 00:00 (JST)\"," +
				"  \"related\": {" +
				"    \"Other\": [" +
				"      { \"mal_id\": 10000, \"type\": \"anime\", \"name\": \"RELATED_OTHER\", \"url\": \"https://example.com/related_other\" }" +
				"    ]," +
				"    \"Sequel\": [" +
				"      { \"mal_id\": 11000, \"type\": \"anime\", \"name\": \"RELATED_SEQUEL\", \"url\": \"https://example.com/related_sequel\" }" +
				"    ]" +
				"  }," +
				"  \"producers\": [" +
				"    { \"mal_id\": 1000, \"type\": \"anime\", \"name\": \"PRODUCER\", \"url\": \"https://example.com/producer\" }" +
				"  ]," +
				"  \"licensors\": [" +
				"    { \"mal_id\": 2000, \"type\": \"anime\", \"name\": \"LICENSOR\", \"url\": \"https://example.com/licensor\" }" +
				"  ]," +
				"  \"studios\": [" +
				"    { \"mal_id\": 3000, \"type\": \"anime\", \"name\": \"STUDIO\", \"url\": \"https://example.com/studio\" }" +
				"  ]," +
				"  \"genres\": [" +
				"    { \"mal_id\": 1, \"type\": \"anime\", \"name\": \"Action\", \"url\": \"https://myanimelist.net/anime/genre/1/Action\" }," +
				"    { \"mal_id\": 2, \"type\": \"anime\", \"name\": \"Adventure\", \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\" }" +
				"  ]," +
				"  \"opening_themes\": [ \"OPENING THEME\" ]," +
				"  \"ending_themes\":  [ \"ENDING THEME\" ]" +
				"}";

		mock(mockServer, "/anime/1", response);

		AnimeQuery query = jikan.query().anime().get(1);
		assertNotNull(query);
		assertNotNull(query.toString());
		Anime anime = query.execute().block();
		assertNotNull(anime);
		assertEquals(AnimeType.UNKNOWN, anime.type);
	}

	@Test
	void fetchCharactersAndStaff() {
		// https://api.jikan.moe/v3/anime/11757/characters_staff
		String response = "{\n" +
						  "    \"characters\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 36765,\n" +
						  "            \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg?s=6a96a832b35d0ff3af9292bdb082546e\",\n" +
						  "            \"name\": \"Kirigaya, Kazuto\",\n" +
						  "            \"role\": \"Main\",\n" +
						  "            \"voice_actors\": [\n" +
						  "                {\n" +
						  "                    \"mal_id\": 732,\n" +
						  "                    \"name\": \"Papenbrook, Bryce\",\n" +
						  "                    \"url\": \"https://myanimelist.net/people/732/Bryce_Papenbrook\",\n" +
						  "                    \"image_url\": \"https://cdn.myanimelist.net/r/42x62/images/voiceactors/3/29853.jpg?s=c0dded60545804ead1a1300c69236bce\",\n" +
						  "                    \"language\": \"English\"\n" +
						  "                },\n" +
						  "                {\n" +
						  "                    \"mal_id\": 11817,\n" +
						  "                    \"name\": \"Matsuoka, Yoshitsugu\",\n" +
						  "                    \"url\": \"https://myanimelist.net/people/11817/Yoshitsugu_Matsuoka\",\n" +
						  "                    \"image_url\": \"https://cdn.myanimelist.net/r/42x62/images/voiceactors/2/40132.jpg?s=1c7f44d53fe3c1641b79ca5104dd7caa\",\n" +
						  "                    \"language\": \"Japanese\"\n" +
						  "                }\n" +
						  "            ]\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 36828,\n" +
						  "            \"url\": \"https://myanimelist.net/character/36828/Asuna_Yuuki\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/characters/15/262053.jpg?s=449153df46cce80307c9f8ae622b4514\",\n" +
						  "            \"name\": \"Yuuki, Asuna\",\n" +
						  "            \"role\": \"Main\",\n" +
						  "            \"voice_actors\": [\n" +
						  "                {\n" +
						  "                    \"mal_id\": 890,\n" +
						  "                    \"name\": \"Tomatsu, Haruka\",\n" +
						  "                    \"url\": \"https://myanimelist.net/people/890/Haruka_Tomatsu\",\n" +
						  "                    \"image_url\": \"https://cdn.myanimelist.net/r/42x62/images/voiceactors/2/54591.jpg?s=59d98cf69807cb64edf914f860d8eee5\",\n" +
						  "                    \"language\": \"Japanese\"\n" +
						  "                },\n" +
						  "                {\n" +
						  "                    \"mal_id\": 1650,\n" +
						  "                    \"name\": \"Leigh, Cherami\",\n" +
						  "                    \"url\": \"https://myanimelist.net/people/1650/Cherami_Leigh\",\n" +
						  "                    \"image_url\": \"https://cdn.myanimelist.net/r/42x62/images/voiceactors/2/47505.jpg?s=70a4eeaa04501d3a866693fb2f80e0fb\",\n" +
						  "                    \"language\": \"English\"\n" +
						  "                }\n" +
						  "            ]\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"staff\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 10801,\n" +
						  "            \"url\": \"https://myanimelist.net/people/10801/Tomohiko_Itou\",\n" +
						  "            \"name\": \"Itou, Tomohiko\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/1/54737.jpg?s=671d2cead4aa20f1f4c72d141d6e1e7c\",\n" +
						  "            \"positions\": [\n" +
						  "                \"Director\",\n" +
						  "                \"Episode Director\",\n" +
						  "                \"Storyboard\"\n" +
						  "            ]\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 19775,\n" +
						  "            \"url\": \"https://myanimelist.net/people/19775/Yoshiyuki_Fujiwara\",\n" +
						  "            \"name\": \"Fujiwara, Yoshiyuki\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/questionmark_23.gif?s=f7dcbc4a4603d18356d3dfef8abd655c\",\n" +
						  "            \"positions\": [\n" +
						  "                \"Episode Director\",\n" +
						  "                \"Storyboard\",\n" +
						  "                \"2nd Key Animation\",\n" +
						  "                \"Key Animation\"\n" +
						  "            ]\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/11757/characters_staff", response);

		AnimeCharacters charactersAndStaff = jikan.query().anime().charactersAndStaff(11757).execute().block();

		assertNotNull(charactersAndStaff);
		assertNotNull(charactersAndStaff.toString());

		/* Characters */
		Iterator<CharacterBasic> charactersIt = charactersAndStaff.characters.iterator();

		CharacterBasic character1 = charactersIt.next();
		assertNotNull(character1.toString());
		assertEquals(36765, character1.malId);
		assertEquals("Kirigaya, Kazuto", character1.name);
		assertEquals("Main", character1.role);
		Iterator<CharacterVoiceActor> character1VoiceActorsIt = character1.voiceActors.iterator();
		CharacterVoiceActor cha1va1 = character1VoiceActorsIt.next();
		assertNotNull(cha1va1.toString());
		assertEquals(732, cha1va1.malId);
		assertEquals("Papenbrook, Bryce", cha1va1.name);
		assertEquals("English", cha1va1.language);
		CharacterVoiceActor cha1va2 = character1VoiceActorsIt.next();
		assertNotNull(cha1va2.toString());
		assertEquals(11817, cha1va2.malId);
		assertEquals("Matsuoka, Yoshitsugu", cha1va2.name);
		assertEquals("Japanese", cha1va2.language);

		CharacterBasic character2 = charactersIt.next();
		assertNotNull(character2.toString());
		assertEquals(36828, character2.malId);
		assertEquals("Yuuki, Asuna", character2.name);
		assertEquals("Main", character2.role);
		Iterator<CharacterVoiceActor> character2VoiceActorsIt = character2.voiceActors.iterator();
		CharacterVoiceActor cha2va1 = character2VoiceActorsIt.next();
		assertNotNull(cha2va1.toString());
		assertEquals(890, cha2va1.malId);
		assertEquals("Tomatsu, Haruka", cha2va1.name);
		assertEquals("Japanese", cha2va1.language);
		CharacterVoiceActor cha2va2 = character2VoiceActorsIt.next();
		assertNotNull(cha2va2.toString());
		assertEquals(1650, cha2va2.malId);
		assertEquals("Leigh, Cherami", cha2va2.name);
		assertEquals("English", cha2va2.language);

		assertFalse(charactersIt.hasNext());

		/* Staff */
		Iterator<AnimeStaff> staffIt = charactersAndStaff.staff.iterator();

		AnimeStaff staff1 = staffIt.next();
		assertNotNull(staff1.toString());
		assertEquals(10801, staff1.malId);
		assertEquals("Itou, Tomohiko", staff1.name);
		assertTrue(staff1.positions.containsAll(Arrays.asList("Director", "Episode Director", "Storyboard")));

		AnimeStaff staff2 = staffIt.next();
		assertNotNull(staff2.toString());
		assertEquals(19775, staff2.malId);
		assertEquals("Fujiwara, Yoshiyuki", staff2.name);
		assertTrue(staff2.positions.containsAll(Arrays.asList("Episode Director", "Storyboard", "2nd Key Animation", "Key Animation")));

		assertFalse(staffIt.hasNext());
	}

	@Test
	void fetchEpisodes() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/anime/11757/episodes/1
		String response = "{\n" +
						  "    \"episodes_last_page\": 1,\n" +
						  "    \"episodes\": [\n" +
						  "        {\n" +
						  "            \"episode_id\": 1,\n" +
						  "            \"title\": \"The World of Swords\",\n" +
						  "            \"title_japanese\": \"剣の世界\",\n" +
						  "            \"title_romanji\": \"Ken no Sekai \",\n" +
						  "            \"aired\": \"2012-07-08T00:00:00+00:00\",\n" +
						  "            \"filler\": false,\n" +
						  "            \"recap\": false,\n" +
						  "            \"video_url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1\",\n" +
						  "            \"forum_url\": \"https://myanimelist.net/forum/?topicid=459625\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"episode_id\": 2,\n" +
						  "            \"title\": \"Beater\",\n" +
						  "            \"title_japanese\": \"ビーター\",\n" +
						  "            \"title_romanji\": \"Beater \",\n" +
						  "            \"aired\": \"2012-07-15T00:00:00+00:00\",\n" +
						  "            \"filler\": false,\n" +
						  "            \"recap\": false,\n" +
						  "            \"video_url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online/episode/2\",\n" +
						  "            \"forum_url\": \"https://myanimelist.net/forum/?topicid=463839\"\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/11757/episodes/1", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().episodes(10, 0), "page starts at index 1");

		AnimeEpisodes episodes = jikan.query().anime().episodes(11757, 1).execute().block();

		assertNotNull(episodes);
		assertNotNull(episodes.toString());
		assertNotNull(episodes.episodes);
		assertEquals(1, episodes.lastPage);

		/* Episodes */
		Iterator<AnimeEpisode> episodesIt = episodes.episodes.iterator();

		AnimeEpisode ep1 = episodesIt.next();
		assertNotNull(ep1.toString());
		assertEquals(1, ep1.episodeId);
		assertEquals("The World of Swords", ep1.title);
		assertEquals(OffsetDateTime.parse("2012-07-08T00:00:00+00:00"), ep1.aired);
		assertFalse(ep1.filler);
		assertFalse(ep1.recap);

		AnimeEpisode ep2 = episodesIt.next();
		assertNotNull(ep2.toString());
		assertEquals(2, ep2.episodeId);
		assertEquals("Beater", ep2.title);
		assertEquals(OffsetDateTime.parse("2012-07-15T00:00:00+00:00"), ep2.aired);
		assertFalse(ep2.filler);
		assertFalse(ep2.recap);
	}

	@Test
	void fetchNews() {
		// https://api.jikan.moe/v3/anime/11757/news
		String response = "{\n" +
						  "    \"articles\": [\n" +
						  "        {\n" +
						  "            \"url\": \"https://myanimelist.net/news/56114579\",\n" +
						  "            \"title\": \"Interview: Luna Haruna to Showcase Best Album at New York Anisong World Matsuri\",\n" +
						  "            \"date\": \"2018-10-31T20:00:00+00:00\",\n" +
						  "            \"author_name\": \"arsonal\",\n" +
						  "            \"author_url\": \"https://myanimelist.net/profile/arsonal\",\n" +
						  "            \"forum_url\": \"https://myanimelist.net/forum/?topicid=1748998\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/s/common/uploaded_files/1541039084-d87d2d92a6923a7b69e2f1775ae39c93.jpeg?s=3288e6769101fc3bc22f4728c933d9f4\",\n" +
						  "            \"comments\": 5,\n" +
						  "            \"intro\": \"Since making her international debut...\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"url\": \"https://myanimelist.net/news/50992876\",\n" +
						  "            \"title\": \"North American Anime & Manga Releases for June\",\n" +
						  "            \"date\": \"2017-06-05T11:01:00+00:00\",\n" +
						  "            \"author_name\": \"Sakana-san\",\n" +
						  "            \"author_url\": \"https://myanimelist.net/profile/Sakana-san\",\n" +
						  "            \"forum_url\": \"https://myanimelist.net/forum/?topicid=1623111\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/s/common/uploaded_files/1496685526-62c913dabd65ccbcc2980704531918c8.jpeg?s=413df56d899170c2e81c41a54235740c\",\n" +
						  "            \"comments\": 12,\n" +
						  "            \"intro\": \"Here are the North American anime & manga...\"\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/11757/news", response);

		Collection<NewsArticle> newsArticles = jikan.query().anime().news(11757).execute().collectList().block();

		assertNotNull(newsArticles);
		assertNotNull(new News().toString());

		/* Articles */
		Iterator<NewsArticle> articlesIt = newsArticles.iterator();

		NewsArticle a1 = articlesIt.next();
		assertNotNull(a1.toString());
		assertEquals("https://myanimelist.net/news/56114579", a1.url);
		assertEquals("Interview: Luna Haruna to Showcase Best Album at New York Anisong World Matsuri", a1.title);
		assertEquals(OffsetDateTime.parse("2018-10-31T20:00:00+00:00"), a1.date);
		assertEquals("arsonal", a1.authorName);
		assertEquals("https://myanimelist.net/forum/?topicid=1748998", a1.forumUrl);
		assertEquals(5, a1.comments);
		assertEquals("Since making her international debut...", a1.intro);

		NewsArticle a2 = articlesIt.next();
		assertNotNull(a2.toString());
		assertEquals("https://myanimelist.net/news/50992876", a2.url);
		assertEquals("North American Anime & Manga Releases for June", a2.title);
		assertEquals(OffsetDateTime.parse("2017-06-05T11:01:00+00:00"), a2.date);
		assertEquals("Sakana-san", a2.authorName);
		assertEquals("https://myanimelist.net/forum/?topicid=1623111", a2.forumUrl);
		assertEquals(12, a2.comments);
		assertEquals("Here are the North American anime & manga...", a2.intro);
	}

	@Test
	void fetchPictures() {
		// https://api.jikan.moe/v3/anime/11757/pictures
		String response = "{\n" +
						  "    \"pictures\": [\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/anime/8/36343l.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/anime/8/36343.jpg\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/anime/11/39717l.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/anime/11/39717.jpg\"\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/11757/pictures", response);

		Collection<Picture> pictures = jikan.query().anime().pictures(11757).execute().collectList().block();

		assertNotNull(pictures);
		assertNotNull(new Pictures().toString());

		/* Pictures */
		Iterator<Picture> picturesIt = pictures.iterator();

		Picture p1 = picturesIt.next();
		assertNotNull(p1.toString());
		assertEquals("https://cdn.myanimelist.net/images/anime/8/36343l.jpg", p1.large);
		assertEquals("https://cdn.myanimelist.net/images/anime/8/36343.jpg", p1.small);

		Picture p2 = picturesIt.next();
		assertNotNull(p2.toString());
		assertEquals("https://cdn.myanimelist.net/images/anime/11/39717l.jpg", p2.large);
		assertEquals("https://cdn.myanimelist.net/images/anime/11/39717.jpg", p2.small);
	}

	@Test
	void fetchVideos() {
		// https://api.jikan.moe/v3/anime/11757/videos
		String response = "{\n" +
						  "    \"promo\": [\n" +
						  "        {\n" +
						  "            \"title\": \"PV English dub version\",\n" +
						  "            \"image_url\": \"https://i.ytimg.com/vi/6ohYYtxfDCg/mqdefault.jpg\",\n" +
						  "            \"video_url\": \"https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1\"\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"episodes\": [\n" +
						  "        {\n" +
						  "            \"title\": \"The World of Swords\",\n" +
						  "            \"episode\": \"Episode 1\",\n" +
						  "            \"url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1\",\n" +
						  "            \"image_url\": \"https://img1.ak.crunchyroll.com/i/spire1-tmb/018d02b49a25a58bfd8a64416bdb69b41341616322_large.jpg\"\n" +
						  "        }" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/1/videos", response);

		AnimeVideos videos = jikan.query().anime().videos(1).execute().block();

		assertNotNull(videos);
		assertNotNull(videos.toString());
		assertNotNull(videos.promo);
		assertNotNull(videos.episodes);

		/* Promo */
		AnimeVideosPromo promo = videos.promo.iterator().next();
		assertNotNull(promo.toString());
		assertEquals("PV English dub version", promo.title);
		assertEquals("https://i.ytimg.com/vi/6ohYYtxfDCg/mqdefault.jpg", promo.imageUrl);
		assertEquals("https://www.youtube.com/embed/6ohYYtxfDCg?enablejsapi=1&wmode=opaque&autoplay=1", promo.videoUrl);

		/* Episode */
		AnimeVideosEpisode episode = videos.episodes.iterator().next();
		assertNotNull(episode.toString());
		assertEquals("The World of Swords", episode.title);
		assertEquals("Episode 1", episode.episode);
		assertEquals("https://myanimelist.net/anime/11757/Sword_Art_Online/episode/1", episode.url);
		assertEquals("https://img1.ak.crunchyroll.com/i/spire1-tmb/018d02b49a25a58bfd8a64416bdb69b41341616322_large.jpg", episode.imageUrl);
	}

	@Test
	void fetchStats() {
		// https://api.jikan.moe/v3/anime/11757/stats
		String response = "{\n" +
						  "    \"watching\": 68292,\n" +
						  "    \"completed\": 1624882,\n" +
						  "    \"on_hold\": 21722,\n" +
						  "    \"dropped\": 75644,\n" +
						  "    \"plan_to_watch\": 87594,\n" +
						  "    \"total\": 1878134,\n" +
						  "    \"scores\": {\n" +
						  "        \"1\": { \"votes\": 19669, \"percentage\": 1.5 },\n" +
						  "        \"2\": { \"votes\": 21048, \"percentage\": 1.6 },\n" +
						  "        \"3\": { \"votes\": 35624, \"percentage\": 2.7 },\n" +
						  "        \"4\": { \"votes\": 64411, \"percentage\": 4.8 },\n" +
						  "        \"5\": { \"votes\": 98516, \"percentage\": 7.4 },\n" +
						  "        \"6\": { \"votes\": 150171, \"percentage\": 11.2 },\n" +
						  "        \"7\": { \"votes\": 250296, \"percentage\": 18.7 },\n" +
						  "        \"8\": { \"votes\": 264412, \"percentage\": 19.8 },\n" +
						  "        \"9\": { \"votes\": 212935, \"percentage\": 15.9 },\n" +
						  "        \"10\": { \"votes\": 219496, \"percentage\": 16.4 }\n" +
						  "    }\n" +
						  "}";

		mock(mockServer, "/anime/11757/stats", response);

		Statistics statistics = jikan.query().anime().stats(11757).execute().block();

		assertNotNull(statistics);
		assertNotNull(statistics.toString());
		assertEquals(68292,   statistics.seeing);
		assertEquals(1624882, statistics.completed);
		assertEquals(21722,   statistics.onHold);
		assertEquals(75644,   statistics.dropped);
		assertEquals(87594,   statistics.planToSee);
		assertEquals(1878134, statistics.total);
		assertEquals(19669,  statistics.scores.get(1).votes);  assertEquals(1.5F,  statistics.scores.get(1).percentage);
		assertEquals(21048,  statistics.scores.get(2).votes);  assertEquals(1.6F,  statistics.scores.get(2).percentage);
		assertEquals(35624,  statistics.scores.get(3).votes);  assertEquals(2.7F,  statistics.scores.get(3).percentage);
		assertEquals(64411,  statistics.scores.get(4).votes);  assertEquals(4.8F,  statistics.scores.get(4).percentage);
		assertEquals(98516,  statistics.scores.get(5).votes);  assertEquals(7.4F,  statistics.scores.get(5).percentage);
		assertEquals(150171, statistics.scores.get(6).votes);  assertEquals(11.2F, statistics.scores.get(6).percentage);
		assertEquals(250296, statistics.scores.get(7).votes);  assertEquals(18.7F, statistics.scores.get(7).percentage);
		assertEquals(264412, statistics.scores.get(8).votes);  assertEquals(19.8F, statistics.scores.get(8).percentage);
		assertEquals(212935, statistics.scores.get(9).votes);  assertEquals(15.9F, statistics.scores.get(9).percentage);
		assertEquals(219496, statistics.scores.get(10).votes); assertEquals(16.4F, statistics.scores.get(10).percentage);
		assertNotNull(statistics.scores.values().iterator().next().toString());

		int totalScores = statistics.scores.values().stream().mapToInt(s -> s.votes).sum();
		assertTrue(statistics.total >= totalScores);
	}

	@Test
	void fetchForum() {
		// https://api.jikan.moe/v3/anime/11757/forum
		String response = "{\n" +
						  "    \"topics\": [\n" +
						  "        {\n" +
						  "            \"topic_id\": 1797514,\n" +
						  "            \"url\": \"https://myanimelist.net/forum/?topicid=1797514\",\n" +
						  "            \"title\": \"Is Kirito an UNLIKABLE character?\",\n" +
						  "            \"date_posted\": \"2019-08-15T00:00:00+00:00\",\n" +
						  "            \"author_name\": \"AUTHOR\",\n" +
						  "            \"author_url\": \"https://myanimelist.net/profile/AUTHOR\",\n" +
						  "            \"replies\": 54,\n" +
						  "            \"last_post\": {\n" +
						  "                \"url\": \"https://myanimelist.net/forum/?topicid=1797514&goto=lastpost\",\n" +
						  "                \"author_name\": \"LAST POST\",\n" +
						  "                \"author_url\": \"https://myanimelist.net/profile/LAST POST\",\n" +
						  "                \"date_posted\": \"2020-07-14T08:46:00+00:00\"\n" +
						  "            }\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/anime/11757/forum", response);

		Collection<ForumTopic> forumTopics = jikan.query().anime().forum(11757).execute().collectList().block();

		assertNotNull(forumTopics);
		assertNotNull(new Forum().toString());

		/* Topics */
		Iterator<ForumTopic> topicsIt = forumTopics.iterator();

		ForumTopic t1 = topicsIt.next();
		assertNotNull(t1.toString());
		assertEquals(1797514, t1.topicId);
		assertEquals("https://myanimelist.net/forum/?topicid=1797514", t1.url);
		assertEquals("Is Kirito an UNLIKABLE character?", t1.title);
		assertEquals(OffsetDateTime.parse("2019-08-15T00:00:00+00:00"), t1.datePosted);
		assertEquals("AUTHOR", t1.authorName);
		assertEquals(54, t1.replies);

		ForumTopicPost t1Last = t1.lastPost;
		assertNotNull(t1Last.toString());
		assertEquals("https://myanimelist.net/forum/?topicid=1797514&goto=lastpost", t1Last.url);
		assertEquals("LAST POST", t1Last.authorName);
		assertEquals(OffsetDateTime.parse("2020-07-14T08:46:00+00:00"), t1Last.datePosted);

		assertFalse(topicsIt.hasNext());
	}

	@Test
	void fetchMoreInfo() {
		// https://api.jikan.moe/v3/anime/11757/moreinfo
		String response = "{\n" +
						  "    \"moreinfo\": null\n" +
						  "}";

		mock(mockServer, "/anime/11757/moreinfo", response);

		MoreInfo moreInfo = jikan.query().anime().moreInfo(11757).execute().block();

		assertNotNull(moreInfo);
		assertNotNull(moreInfo.toString());
		assertNull(moreInfo.moreInfo);
	}

	@Test
	void fetchReviews() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/anime/11757/reviews/1
		String response = "{\n" +
				"    \"reviews\": [\n" +
				"        {\n" +
				"            \"mal_id\": 138911,\n" +
				"            \"url\": \"https://myanimelist.net/reviews.php?id=138911\",\n" +
				"            \"type\": null,\n" +
				"            \"helpful_count\": 4426,\n" +
				"            \"date\": \"2014-04-06T02:33:00+00:00\",\n" +
				"            \"reviewer\": {\n" +
				"                \"url\": \"https://myanimelist.net/profile/LordAura\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/userimages/3441293.jpg?t=1594695000\",\n" +
				"                \"username\": \"LordAura\",\n" +
				"                \"episodes_seen\": 25,\n" +
				"                \"scores\": {\n" +
				"                    \"overall\": 4,\n" +
				"                    \"story\": 4,\n" +
				"                    \"animation\": 8,\n" +
				"                    \"sound\": 8,\n" +
				"                    \"character\": 1,\n" +
				"                    \"enjoyment\": 3\n" +
				"                }\n" +
				"            },\n" +
				"            \"content\": \"The review contains minor spoilers...\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/anime/11757/reviews/1", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().reviews(10, 0), "page starts at index 1");

		Collection<Review> reviews = jikan.query().anime().reviews(11757, 1).execute().collectList().block();

		assertNotNull(reviews);
		assertNotNull(new Reviews().toString());

		/* Reviews */
		Iterator<Review> reviewsIt = reviews.iterator();

		Review review = reviewsIt.next();
		assertNotNull(review.toString());
		assertEquals(138911, review.malId);
		assertEquals("https://myanimelist.net/reviews.php?id=138911", review.url);
		assertEquals(4426, review.helpfulCount);
		assertEquals(OffsetDateTime.parse("2014-04-06T02:33:00+00:00"), review.date);
		assertEquals("The review contains minor spoilers...", review.content);

		Reviewer reviewer = review.reviewer;
		assertNotNull(reviewer.toString());
		assertEquals("https://myanimelist.net/profile/LordAura", reviewer.url);
		assertEquals("LordAura", reviewer.username);
		assertEquals(25, reviewer.read);

		ReviewScores scores = reviewer.scores;
		assertNotNull(scores);
		assertNotNull(scores.toString());
		assertEquals(4, scores.overall);
		assertEquals(4, scores.story);
		assertEquals(8, scores.animation);
		assertEquals(8, scores.sound);
		assertEquals(1, scores.character);
		assertEquals(3, scores.enjoyment);

		assertFalse(reviewsIt.hasNext());
	}

	@Test
	void fetchRecommendations() {
		// https://api.jikan.moe/v3/anime/11757/recommendations
		String response = "{\n" +
				"    \"recommendations\": [\n" +
				"        {\n" +
				"            \"mal_id\": 17265,\n" +
				"            \"url\": \"https://myanimelist.net/anime/17265/Log_Horizon\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/5/84004.jpg?s=73d4b44ed253ca5c865ef6e026cec99f\",\n" +
				"            \"recommendation_url\": \"https://myanimelist.net/recommendations/anime/11757-17265\",\n" +
				"            \"title\": \"Log Horizon\",\n" +
				"            \"recommendation_count\": 160\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/anime/11757/recommendations", response);

		Collection<Recommendation> recommendations = jikan.query().anime().recommendations(11757).execute().collectList().block();

		assertNotNull(recommendations);
		assertNotNull(new Recommendations().toString());

		/* Recommendations */
		Iterator<Recommendation> recommendationsIt = recommendations.iterator();

		Recommendation recommendation = recommendationsIt.next();
		assertNotNull(recommendation.toString());
		assertEquals(17265, recommendation.malId);
		assertEquals("https://myanimelist.net/anime/17265/Log_Horizon", recommendation.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/5/84004.jpg?s=73d4b44ed253ca5c865ef6e026cec99f", recommendation.imageUrl);
		assertEquals("https://myanimelist.net/recommendations/anime/11757-17265", recommendation.recommendationUrl);
		assertEquals("Log Horizon", recommendation.title);
		assertEquals(160, recommendation.recommendationCount);

		assertFalse(recommendationsIt.hasNext());
	}

	@Test
	void fetchUserUpdates() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/anime/11757/userupdates/1
		String response = "{\n" +
				"    \"users\": [\n" +
				"        {\n" +
				"            \"username\": \"Vincent1307\",\n" +
				"            \"url\": \"https://myanimelist.net/profile/Vincent1307\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/questionmark_50.gif\",\n" +
				"            \"score\": 8.5,\n" +
				"            \"status\": \"Completed\",\n" +
				"            \"episodes_seen\": 25,\n" +
				"            \"episodes_total\": 25,\n" +
				"            \"date\": \"2020-07-16T20:15:05+00:00\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/anime/11757/userupdates/1", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().userUpdates(10, 0), "page starts at index 1");

		Collection<UserUpdate> userUpdates = jikan.query().anime().userUpdates(11757, 1).execute().collectList().block();

		assertNotNull(userUpdates);
		assertNotNull(new UserUpdates().toString());

		/* User Updates */
		Iterator<UserUpdate> usersIt = userUpdates.iterator();

		UserUpdate userUpdate = usersIt.next();
		assertNotNull(userUpdate.toString());
		assertEquals("Vincent1307", userUpdate.username);
		assertEquals("https://myanimelist.net/profile/Vincent1307", userUpdate.url);
		assertEquals(8.5F, userUpdate.score);
		assertEquals("Completed", userUpdate.status);
		assertEquals(25, userUpdate.seen);
		assertEquals(25, userUpdate.total);
		assertEquals(OffsetDateTime.parse("2020-07-16T20:15:05+00:00"), userUpdate.date);

		assertFalse(usersIt.hasNext());
	}

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/anime?q=test&page=1&limit=2
		String response = "{\n" +
						  "    \"results\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 6347,\n" +
						  "            \"url\": \"https://myanimelist.net/anime/6347/Baka_to_Test_to_Shoukanjuu\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/anime/3/50389.jpg?s=bb898c33476da7c587e8ec82afecee57\",\n" +
						  "            \"title\": \"Baka to Test to Shoukanjuu\",\n" +
						  "            \"airing\": false,\n" +
						  "            \"synopsis\": \"Fumizuki Academy isn't a typical Japanese high school...\",\n" +
						  "            \"type\": \"TV\",\n" +
						  "            \"episodes\": 13,\n" +
						  "            \"score\": 7.65,\n" +
						  "            \"start_date\": \"2010-01-07T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2010-04-01T00:00:00+00:00\",\n" +
						  "            \"members\": 484011,\n" +
						  "            \"rated\": \"PG-13\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 9471,\n" +
						  "            \"url\": \"https://myanimelist.net/anime/9471/Baka_to_Test_to_Shoukanjuu__Matsuri\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/anime/3/67303.jpg?s=048fca5bdfb21cefa072a303fe8047ad\",\n" +
						  "            \"title\": \"Baka to Test to Shoukanjuu: Matsuri\",\n" +
						  "            \"airing\": false,\n" +
						  "            \"synopsis\": \"OVA of Baka to Test to Shoukanjuu...\",\n" +
						  "            \"type\": \"OVA\",\n" +
						  "            \"episodes\": 2,\n" +
						  "            \"score\": 7.66,\n" +
						  "            \"start_date\": \"2011-02-23T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2011-03-30T00:00:00+00:00\",\n" +
						  "            \"members\": 101901,\n" +
						  "            \"rated\": \"PG-13\"\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"last_page\": 20\n" +
						  "}";

		mock(mockServer, "/search/anime", response,
				Parameter.param("q", "test"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"),
				Parameter.param("genre[]", "1,2"),
				Parameter.param("status", "completed"),
				Parameter.param("orderBy", "id"),
				Parameter.param("sort", "asc"),
				Parameter.param("rated", "pg"),
				Parameter.param("score", "1.0"),
				Parameter.param("startDate", "2020-01-01"),
				Parameter.param("endDate", "2020-12-31"));

		Collection<AnimeSearchSub> results = jikan.query().anime().search()
				.query("test")
				.page(1)
				.limit(1)
				.genres(AnimeGenre.ACTION, AnimeGenre.ADVENTURE)
				.status(AnimeStatus.COMPLETED)
				.orderBy(AnimeOrderBy.ID)
				.sort(Sort.ASCENDING)
				.rated(AgeRating.PG)
				.score(1.0F)
				.startDate(LocalDate.parse("2020-01-01"))
				.endDate(LocalDate.parse("2020-12-31"))
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new AnimeSearch().toString());

		/* Results */
		Iterator<AnimeSearchSub> resultsIt = results.iterator();

		AnimeSearchSub r1 = resultsIt.next();
		assertNotNull(r1.toString());
		assertEquals(6347, r1.malId);
		assertEquals("https://myanimelist.net/anime/6347/Baka_to_Test_to_Shoukanjuu", r1.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/3/50389.jpg?s=bb898c33476da7c587e8ec82afecee57", r1.imageUrl);
		assertEquals("Baka to Test to Shoukanjuu", r1.title);
		assertFalse(r1.airing);
		assertEquals("Fumizuki Academy isn't a typical Japanese high school...", r1.synopsis);
		assertEquals(AnimeType.TV, r1.type);
		assertEquals(13, r1.episodes);
		assertEquals(7.65F, r1.score);
		assertEquals(LocalDate.parse("2010-01-07"), r1.aired.from);
		assertEquals(LocalDate.parse("2010-04-01"), r1.aired.to);
		assertEquals(484011, r1.members);
		assertEquals(AgeRating.PG13, r1.rating);

		AnimeSearchSub r2 = resultsIt.next();
		assertNotNull(r2.toString());
		assertEquals(9471, r2.malId);
		assertEquals("https://myanimelist.net/anime/9471/Baka_to_Test_to_Shoukanjuu__Matsuri", r2.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/3/67303.jpg?s=048fca5bdfb21cefa072a303fe8047ad", r2.imageUrl);
		assertEquals("Baka to Test to Shoukanjuu: Matsuri", r2.title);
		assertFalse(r2.airing);
		assertEquals("OVA of Baka to Test to Shoukanjuu...", r2.synopsis);
		assertEquals(AnimeType.OVA, r2.type);
		assertEquals(2, r2.episodes);
		assertEquals(7.66F, r2.score);
		assertEquals(LocalDate.parse("2011-02-23"), r2.aired.from);
		assertEquals(LocalDate.parse("2011-03-30"), r2.aired.to);
		assertEquals(101901, r2.members);
		assertEquals(AgeRating.PG13, r2.rating);

		assertFalse(resultsIt.hasNext());
	}

	@Test
	void fetchSearch_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().query("ab"), "query must be a minimum of 3 characters");
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().limit(-1),                        "limit must be between 0 and " + SearchQuery.LIMIT_MAX);
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().limit(SearchQuery.LIMIT_MAX + 1), "limit must be between 0 and " + SearchQuery.LIMIT_MAX);
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().score(-0.1F), "score must be between 0.0 and 10.0");
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().search().score(10.1F), "score must be between 0.0 and 10.0");
	}

	@Test
	void fetchSearch_excludeGenres() {
		// https://api.jikan.moe/v3/search/anime?genre[]=1&genre_exclude=0
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/anime", response,
				Parameter.param("genre", "1"),
				Parameter.param("genre_exclude", "1"));

		Collection<AnimeSearchSub> results = jikan.query().anime().search()
				.genres(AnimeGenre.ACTION)
				.excludeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
	}

	@Test
	void fetchSearch_includeGenres() {
		// https://api.jikan.moe/v3/search/anime?genre_exclude=1
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/anime", response,
				Parameter.param("genre_exclude", "1"));

		Collection<AnimeSearchSub> results = jikan.query().anime().search()
				.genres(AnimeGenre.ACTION)
				.includeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
	}

	@Test
	void fetchTop() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/top/anime/1/airing
		String response = "{\n" +
				"    \"top\": [\n" +
				"        {\n" +
				"            \"mal_id\": 39587,\n" +
				"            \"rank\": 3,\n" +
				"            \"title\": \"Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season\",\n" +
				"            \"url\": \"https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/1444/108005.jpg?s=b998e66dcfad4bbd4510b9ece4c9eb99\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"episodes\": 13,\n" +
				"            \"start_date\": \"Jul 2020\",\n" +
				"            \"end_date\": null,\n" +
				"            \"members\": 290261,\n" +
				"            \"score\": 8.5\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		String responseEmpty = "{\n" +
				"    \"top\": [ ]\n" +
				"}";

		// without subtype
		mock(mockServer, "/top/anime/1", responseEmpty);
		Collection<AnimeTopSub> resultsNoSubtype = jikan.query().anime().top(1).execute().collectList().block();
		assertNotNull(resultsNoSubtype);
		assertTrue(resultsNoSubtype.isEmpty());


		// with subtype
		mock(mockServer, "/top/anime/1/airing", response);
		Collection<AnimeTopSub> results = jikan.query().anime().top(1).subtype(AnimeSubType.AIRING)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new AnimeTop().toString());

		/* Results */
		AnimeTopSub result = results.iterator().next();
		assertNotNull(result.toString());
		assertEquals(39587, result.malId);
		assertEquals(3, result.rank);
		assertEquals("Re:Zero kara Hajimeru Isekai Seikatsu 2nd Season", result.title);
		assertEquals("https://myanimelist.net/anime/39587/Re_Zero_kara_Hajimeru_Isekai_Seikatsu_2nd_Season", result.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/1444/108005.jpg?s=b998e66dcfad4bbd4510b9ece4c9eb99", result.imageUrl);
		assertEquals(AnimeType.TV, result.type);
		assertEquals(13, result.episodes);
		assertEquals("Jul 2020", result.startDate);
		assertNull(result.endDate);
		assertEquals(290261, result.members);
		assertEquals(8.5F, result.score);
	}

	@Test
	void fetchTop_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().top(0), "page starts at index 1");
	}

	@Test
	void fetchGenre() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/genre/anime/1/1
		String response = "{\n" +
				"    \"mal_url\": {\n" +
				"        \"mal_id\": 1,\n" +
				"        \"type\": \"anime\",\n" +
				"        \"name\": \"Action Anime\",\n" +
				"        \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"    },\n" +
				"    \"item_count\": 3731,\n" +
				"    \"anime\": [\n" +
				"        {\n" +
				"            \"mal_id\": 16498,\n" +
				"            \"url\": \"https://myanimelist.net/anime/16498/Shingeki_no_Kyojin\",\n" +
				"            \"title\": \"Shingeki no Kyojin\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/10/47347.jpg\",\n" +
				"            \"synopsis\": \"Centuries ago, mankind was slaughtered to near extinction...\",\n" +
				"            \"type\": \"TV\",\n" +
				"            \"airing_start\": \"2013-04-06T16:58:00+00:00\",\n" +
				"            \"episodes\": 25,\n" +
				"            \"members\": 2005593,\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Action\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"mal_id\": 38,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Military\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/genre/38/Military\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"source\": \"Manga\",\n" +
				"            \"producers\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 858,\n" +
				"                    \"type\": \"anime\",\n" +
				"                    \"name\": \"Wit Studio\",\n" +
				"                    \"url\": \"https://myanimelist.net/anime/producer/858/Wit_Studio\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"score\": 8.44,\n" +
				"            \"licensors\": [\n" +
				"                \"Funimation\"\n" +
				"            ],\n" +
				"            \"r18\": false,\n" +
				"            \"kids\": false\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/genre/anime/1/1", response);

		Collection<AnimeGenreSub> results = jikan.query().anime().genre(AnimeGenre.ACTION, 1)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new AnimeGenreList().toString());

		/* Results */
		AnimeGenreSub result = results.iterator().next();
		assertNotNull(result.toString());
		assertEquals(16498, result.malId);
		assertEquals("Shingeki no Kyojin", result.title);
		assertEquals("https://myanimelist.net/anime/16498/Shingeki_no_Kyojin", result.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/10/47347.jpg", result.imageUrl);
		assertEquals("Centuries ago, mankind was slaughtered to near extinction...", result.synopsis);
		assertEquals(AnimeType.TV, result.type);
		assertEquals(LocalDate.parse("2013-04-06"), result.aired.from);
		assertEquals(25, result.episodes);
		assertEquals(2005593, result.members);
		assertEquals("Manga", result.source);
		assertEquals(8.44F, result.score);
		assertFalse(result.r18);
		assertFalse(result.kids);
		assertTrue(result.licensors.contains("Funimation"));


		Iterator<MalSubEntity> genresIt = result.genres.iterator();

		MalSubEntity genre1 = genresIt.next();
		assertEquals(1, genre1.malId);
		assertEquals(Type.ANIME, genre1.type);
		assertEquals("Action", genre1.name);
		assertEquals("https://myanimelist.net/anime/genre/1/Action", genre1.url);

		MalSubEntity genre2 = genresIt.next();
		assertEquals(38, genre2.malId);
		assertEquals(Type.ANIME, genre2.type);
		assertEquals("Military", genre2.name);
		assertEquals("https://myanimelist.net/anime/genre/38/Military", genre2.url);

		assertFalse(genresIt.hasNext());


		MalSubEntity producer = result.producers.iterator().next();
		assertEquals(858, producer.malId);
		assertEquals(Type.ANIME, producer.type);
		assertEquals("Wit Studio", producer.name);
		assertEquals("https://myanimelist.net/anime/producer/858/Wit_Studio", producer.url);
	}

	@Test
	void fetchGenre_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().anime().genre(AnimeGenre.ACTION, 0), "page starts at index 1");
	}

}
