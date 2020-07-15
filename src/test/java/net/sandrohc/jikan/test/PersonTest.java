package net.sandrohc.jikan.test;

import net.sandrohc.jikan.model.person.*;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonTest extends BaseTest {

	@Test
	void fetchById() {
		// https://api.jikan.moe/v3/character/36765
		String response = "{\n" +
				"    \"request_hash\": \"request:person:cbd59b95af90c14277375ca57503ca9d3cd54278\",\n" +
				"    \"request_cached\": true,\n" +
				"    \"request_cache_expiry\": 3935,\n" +
				"    \"mal_id\": 11817,\n" +
				"    \"url\": \"https://myanimelist.net/people/11817/Yoshitsugu_Matsuoka\",\n" +
				"    \"image_url\": \"https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg\",\n" +
				"    \"website_url\": null,\n" +
				"    \"name\": \"Yoshitsugu Matsuoka\",\n" +
				"    \"given_name\": \"禎丞\",\n" +
				"    \"family_name\": \"松岡\",\n" +
				"    \"alternate_names\": [],\n" +
				"    \"birthday\": \"1986-09-17T00:00:00+00:00\",\n" +
				"    \"member_favorites\": 25992,\n" +
				"    \"about\": \"Hometown: Hokkaido, Japan\\\\n\\r\\nHeight: 165 cm ...\",\n" +
				"    \"voice_acting_roles\": [\n" +
				"        {\n" +
				"            \"role\": \"Main\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 11757,\n" +
				"                \"url\": \"https://myanimelist.net/anime/11757/Sword_Art_Online\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/11/39717.jpg?s=0d39a46a21e7dac6a04cb02a3263c72e\",\n" +
				"                \"name\": \"Sword Art Online\"\n" +
				"            },\n" +
				"            \"character\": {\n" +
				"                \"mal_id\": 36765,\n" +
				"                \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg?s=256042ba88a8ba04279940785bef9dc6\",\n" +
				"                \"name\": \"Kirigaya, Kazuto\"\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"role\": \"Main\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 31765,\n" +
				"                \"url\": \"https://myanimelist.net/anime/31765/Sword_Art_Online_Movie__Ordinal_Scale\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/4/83811.jpg?s=0f5f07a5eaca4bcfaa4c792d874dcec0\",\n" +
				"                \"name\": \"Sword Art Online Movie: Ordinal Scale\"\n" +
				"            },\n" +
				"            \"character\": {\n" +
				"                \"mal_id\": 36765,\n" +
				"                \"url\": \"https://myanimelist.net/character/36765/Kazuto_Kirigaya\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/characters/7/204821.jpg?s=256042ba88a8ba04279940785bef9dc6\",\n" +
				"                \"name\": \"Kirigaya, Kazuto\"\n" +
				"            }\n" +
				"        }\n" +
				"    ],\n" +
				"    \"anime_staff_positions\": [\n" +
				"        {\n" +
				"            \"position\": \"Theme Song Performance\",\n" +
				"            \"anime\": {\n" +
				"                \"mal_id\": 30205,\n" +
				"                \"url\": \"https://myanimelist.net/anime/30205/Aoharu_x_Kikanjuu\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/anime/4/76271.jpg?s=f5bbc906562d79cadb58efa015a86f20\",\n" +
				"                \"name\": \"Aoharu x Kikanjuu\"\n" +
				"            }\n" +
				"        }\n" +
				"    ],\n" +
				"    \"published_manga\": [\n" +
				"        {\n" +
				"            \"position\": \"Story\",\n" +
				"            \"manga\": {\n" +
				"                \"mal_id\": 98568,\n" +
				"                \"url\": \"https://myanimelist.net/manga/98568/CV_Ore\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/179349.jpg?s=6d366ebaf5a1357cb8b67e070f119db1\",\n" +
				"                \"name\": \"CV. Ore!\"\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/person/11817", response);

		Person person = jikan.query().person().get(11817).execute().block();

		assertNotNull(person);
		assertEquals(11817, person.malId);
		assertEquals("Yoshitsugu Matsuoka", person.name);
	}

}
