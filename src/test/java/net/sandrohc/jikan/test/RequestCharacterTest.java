package net.sandrohc.jikan.test;

import java.io.UnsupportedEncodingException;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.search.*;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestCharacterTest extends RequestTest {

	@Test
	void fetchCharacter() {
		// https://api.jikan.moe/v3/character/36765
		String response = "{\n" +
				"    \"mal_id\": 36765,\n" +
				"    \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
				"    \"name\": \"Kazuto Kirigaya\",\n" +
				"    \"name_kanji\": \"桐ヶ谷 和人\",\n" +
				"    \"nicknames\": [],\n" +
				"    \"about\": \"Birthday: October 7, 2008\\\\n\\r\\nAge: 14 ...\",\n" +
				"    \"member_favorites\": 41817,\n" +
				"    \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg\",\n" +
				"    \"animeography\": [\n" +
				"        {\n" +
				"            \"mal_id\": 11757,\n" +
				"            \"name\": \"Sword Art Online\",\n" +
				"            \"url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/11/39717.jpg?s=e418310b575f6afe9ac03b383527169d\",\n" +
				"            \"role\": \"Main\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"mal_id\": 16099,\n" +
				"            \"name\": \"Sword Art Online: Sword Art Offline\",\n" +
				"            \"url\": \"https://myanimelist.net/anime/16099/Sword_Art_Online__Sword_Art_Offline\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/anime/8/43461.jpg?s=c149f76995ff462358c44022c8de1790\",\n" +
				"            \"role\": \"Main\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"mangaography\": [\n" +
				"        {\n" +
				"            \"mal_id\": 21479,\n" +
				"            \"name\": \"Sword Art Online\",\n" +
				"            \"url\": \"https://myanimelist.net/manga/21479/Sword_Art_Online\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/1/34697.jpg?s=370b6226f2da5f0061b8aba1bd43ece4\",\n" +
				"            \"role\": \"Main\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"mal_id\": 23718,\n" +
				"            \"name\": \"Sword Art Online: Aincrad\",\n" +
				"            \"url\": \"https://myanimelist.net/manga/23718/Sword_Art_Online__Aincrad\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/2/77727.jpg?s=7f4e225d3587cd12de8fe02183484b42\",\n" +
				"            \"role\": \"Main\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"voice_actors\": [\n" +
				"        {\n" +
				"            \"mal_id\": 732,\n" +
				"            \"name\": \"Papenbrook, Bryce\",\n" +
				"            \"url\": \"https://myanimelist.net/people/732/Bryce_Papenbrook\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/3/29853.jpg\",\n" +
				"            \"language\": \"English\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"mal_id\": 11817,\n" +
				"            \"name\": \"Matsuoka, Yoshitsugu\",\n" +
				"            \"url\": \"https://myanimelist.net/people/11817/Yoshitsugu_Matsuoka\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg\",\n" +
				"            \"language\": \"Japanese\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/character/36765", response);

		Character character = jikan.query().character().get(36765).execute().block();

		assertNotNull(character);
		assertNotNull(character.toString());
		assertEquals(36765, character.malId);
		assertEquals("Kazuto Kirigaya", character.name);
	}

	@Test
	void fetchPictures() {
		// https://api.jikan.moe/v3/character/36765/pictures
		String response = "{\n" +
						  "    \"pictures\": [\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/characters/13/159377.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/characters/13/159377.jpg\"\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"large\": \"https://cdn.myanimelist.net/images/characters/10/204551.jpg\",\n" +
						  "            \"small\": \"https://cdn.myanimelist.net/images/characters/10/204551.jpg\"\n" +
						  "        }\n" +
						  "    ]\n" +
						  "}";

		mock(mockServer, "/character/36765/pictures", response);

		Collection<Picture> pictures = jikan.query().character().pictures(36765).execute().collectList().block();

		assertNotNull(pictures);

		/* Pictures */
		Iterator<Picture> picturesIt = pictures.iterator();

		Picture p1 = picturesIt.next();
		assertNotNull(p1.toString());
		assertEquals("https://cdn.myanimelist.net/images/characters/13/159377.jpg", p1.large);
		assertEquals("https://cdn.myanimelist.net/images/characters/13/159377.jpg", p1.small);

		Picture p2 = picturesIt.next();
		assertNotNull(p2.toString());
		assertEquals("https://cdn.myanimelist.net/images/characters/10/204551.jpg", p2.large);
		assertEquals("https://cdn.myanimelist.net/images/characters/10/204551.jpg", p2.small);
	}

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/character?q=asuna&page=1&limit=2
		String response = "{\n" +
						  "    \"results\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 92639,\n" +
						  "            \"url\": \"https://myanimelist.net/character/92639/Asuna\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/characters/12/315884.jpg?s=137d8ac45a770ba4abf6d165aea2dc3e\",\n" +
						  "            \"name\": \"Asuna\",\n" +
						  "            \"alternative_names\": [\n" +
						  "                \"Tenkoufuu\"\n" +
						  "            ],\n" +
						  "            \"anime\": [],\n" +
						  "            \"manga\": [\n" +
						  "                {\n" +
						  "                    \"mal_id\": 49361,\n" +
						  "                    \"type\": \"manga\",\n" +
						  "                    \"name\": \"Dragons Rioting\",\n" +
						  "                    \"url\": \"https://myanimelist.net/manga/49361/Dragons_Rioting\"\n" +
						  "                }\n" +
						  "            ]\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 12127,\n" +
						  "            \"url\": \"https://myanimelist.net/character/12127/Asuna\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/characters/14/253297.jpg?s=b14441bcb751783185e6bef968e5e573\",\n" +
						  "            \"name\": \"Asuna\",\n" +
						  "            \"alternative_names\": [\n" +
						  "                \"Flannery\"\n" +
						  "            ],\n" +
						  "            \"anime\": [\n" +
						  "                {\n" +
						  "                    \"mal_id\": 1564,\n" +
						  "                    \"type\": \"anime\",\n" +
						  "                    \"name\": \"Pokemon Advanced Generation\",\n" +
						  "                    \"url\": \"https://myanimelist.net/anime/1564/Pokemon_Advanced_Generation\"\n" +
						  "                }\n" +
						  "            ],\n" +
						  "            \"manga\": []\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"last_page\": 2\n" +
						  "}";

		mock(mockServer, "/search/character", response,
				Parameter.param("q", "asuna"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"));

		Collection<CharacterSub> results = jikan.query().character().search()
				.query("asuna")
				.page(1)
				.limit(1)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new CharacterSearch().toString());

		/* Results */
		Iterator<CharacterSub> resultsIt = results.iterator();

		CharacterSub r1 = resultsIt.next();
		assertNotNull(r1.toString());
		assertEquals(92639, r1.malId);
		assertEquals("https://myanimelist.net/character/92639/Asuna", r1.url);
		assertEquals("https://cdn.myanimelist.net/images/characters/12/315884.jpg?s=137d8ac45a770ba4abf6d165aea2dc3e", r1.imageUrl);
		assertEquals("Asuna", r1.name);
		assertTrue(r1.alternativeNames.contains("Tenkoufuu"));
		assertTrue(r1.anime.isEmpty());
		MalSubEntity r1Manga = r1.manga.iterator().next();
		assertEquals(49361, r1Manga.malId);
		assertEquals(Type.MANGA, r1Manga.type);
		assertEquals("Dragons Rioting", r1Manga.name);
		assertEquals("https://myanimelist.net/manga/49361/Dragons_Rioting", r1Manga.url);

		CharacterSub r2 = resultsIt.next();
		assertNotNull(r2.toString());
		assertEquals(12127, r2.malId);
		assertEquals("https://myanimelist.net/character/12127/Asuna", r2.url);
		assertEquals("https://cdn.myanimelist.net/images/characters/14/253297.jpg?s=b14441bcb751783185e6bef968e5e573", r2.imageUrl);
		assertEquals("Asuna", r2.name);
		assertTrue(r2.alternativeNames.contains("Flannery"));
		MalSubEntity r2Anime = r2.anime.iterator().next();
		assertEquals(1564, r2Anime.malId);
		assertEquals(Type.ANIME, r2Anime.type);
		assertEquals("Pokemon Advanced Generation", r2Anime.name);
		assertEquals("https://myanimelist.net/anime/1564/Pokemon_Advanced_Generation", r2Anime.url);
		assertTrue(r2.manga.isEmpty());

		assertFalse(resultsIt.hasNext());
	}

}
