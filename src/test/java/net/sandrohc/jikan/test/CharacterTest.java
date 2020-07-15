package net.sandrohc.jikan.test;

import net.sandrohc.jikan.model.character.Character;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest extends BaseTest {

	@Test
	void fetchById() {
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
		assertEquals(36765, character.malId);
		assertEquals("Kazuto Kirigaya", character.name);
	}

}
