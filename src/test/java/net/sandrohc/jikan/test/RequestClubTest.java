package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.user.*;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"UnusedAssignment", "ConstantConditions"})
public class RequestClubTest extends RequestTest {

	@Test
	void fetchClub() {
		// https://api.jikan.moe/v3/club/1
		String response = "{\n" +
				"	\"request_hash\": \"request:club:0d278555cf663789e1b81d22b553266caadba53f\",\n" +
				"	\"request_cached\": true,\n" +
				"	\"request_cache_expiry\": 79410,\n" +
				"	\"mal_id\": 1,\n" +
				"	\"url\": \"https://myanimelist.net/clubs.php?cid=1\",\n" +
				"	\"image_url\": \"https://cdn.myanimelist.net/images/clubs/16/222057.jpg\",\n" +
				"	\"title\": \"Cowboy Bebop\",\n" +
				"	\"members_count\": 100,\n" +
				"	\"pictures_count\": 200,\n" +
				"	\"category\": \"Anime\",\n" +
				"	\"created\": \"2007-03-29T00:00:00+00:00\",\n" +
				"	\"type\": \"public\",\n" +
				"	\"staff\": [\n" +
				"		{\n" +
				"			\"mal_id\": 10,\n" +
				"			\"type\": \"profile\",\n" +
				"			\"name\": \"STAFF_1\",\n" +
				"			\"url\": \"https://myanimelist.net/profile/STAFF_1\"\n" +
				"		}\n" +
				"	],\n" +
				"	\"anime_relations\": [\n" +
				"		{\n" +
				"			\"mal_id\": 20,\n" +
				"			\"type\": \"anime\",\n" +
				"			\"name\": \"Cowboy Bebop\",\n" +
				"			\"url\": \"https://myanimelist.net/anime/20\"\n" +
				"		}\n" +
				"	],\n" +
				"	\"manga_relations\": [\n" +
				"		{\n" +
				"			\"mal_id\": 30,\n" +
				"			\"type\": \"manga\",\n" +
				"			\"name\": \"Cowboy Bebop\",\n" +
				"			\"url\": \"https://myanimelist.net/manga/30\"\n" +
				"		}\n" +
				"	],\n" +
				"	\"character_relations\": [\n" +
				"		{\n" +
				"			\"mal_id\": 40,\n" +
				"			\"type\": \"character\",\n" +
				"			\"name\": \"Wen\",\n" +
				"			\"url\": \"https://myanimelist.net/character/40\"\n" +
				"		}\n" +
				"	]\n" +
				"}";

		mock(mockServer, "/club/1", response);

		Club club = jikan.query().club(1).get().execute().block();

		assertNotNull(club);
		assertNotNull(club.toString());
		assertEquals(1, club.malId);
		assertEquals("https://myanimelist.net/clubs.php?cid=1", club.url);
		assertEquals("https://cdn.myanimelist.net/images/clubs/16/222057.jpg", club.imageUrl);
		assertEquals("Cowboy Bebop", club.title);
		assertEquals(100, club.membersCount);
		assertEquals(200, club.picturesCount);
		assertEquals("Anime", club.category);
		assertEquals(OffsetDateTime.parse("2007-03-29T00:00:00+00:00"), club.created);
		assertEquals("public", club.type);

		assertEquals(club, club);
		assertEquals(club, SerializationUtils.clone(club));
		assertNotEquals(club, new Object());
		assertNotEquals(club, null);
		int hash = club.hashCode();


		MalSubEntity staff = club.staff.get(0);
		assertNotNull(staff.toString());
		assertEquals(10, staff.malId);
		assertEquals(Type.PROFILE, staff.type);
		assertEquals("STAFF_1", staff.name);
		assertEquals("https://myanimelist.net/profile/STAFF_1", staff.url);

		assertEquals(staff, staff);
		assertEquals(staff, SerializationUtils.clone(staff));
		assertNotEquals(staff, new Object());
		assertNotEquals(staff, null);
		hash = staff.hashCode();


		MalSubEntity anime = club.animeRelations.get(0);
		assertNotNull(anime.toString());
		assertEquals(20, anime.malId);
		assertEquals(Type.ANIME, anime.type);
		assertEquals("Cowboy Bebop", anime.name);
		assertEquals("https://myanimelist.net/anime/20", anime.url);

		assertEquals(anime, anime);
		assertEquals(anime, SerializationUtils.clone(anime));
		assertNotEquals(anime, new Object());
		assertNotEquals(anime, null);
		hash = anime.hashCode();


		MalSubEntity manga = club.mangaRelations.get(0);
		assertNotNull(manga.toString());
		assertEquals(30, manga.malId);
		assertEquals(Type.MANGA, manga.type);
		assertEquals("Cowboy Bebop", manga.name);
		assertEquals("https://myanimelist.net/manga/30", manga.url);

		assertEquals(manga, manga);
		assertEquals(manga, SerializationUtils.clone(manga));
		assertNotEquals(manga, new Object());
		assertNotEquals(manga, null);
		hash = manga.hashCode();


		MalSubEntity character = club.characterRelations.get(0);
		assertNotNull(character.toString());
		assertEquals(40, character.malId);
		assertEquals(Type.CHARACTER, character.type);
		assertEquals("Wen", character.name);
		assertEquals("https://myanimelist.net/character/40", character.url);

		assertEquals(character, character);
		assertEquals(character, SerializationUtils.clone(character));
		assertNotEquals(character, new Object());
		assertNotEquals(character, null);
		hash = character.hashCode();
	}

	@Test
	void fetchMembers() {
		// https://api.jikan.moe/v3/club/1/members/1
		String response = "{\n" +
				"	\"request_hash\": \"request:club:4e9a4421bc5435e28e308d44a8737abc037e5d1e\",\n" +
				"	\"request_cached\": true,\n" +
				"	\"request_cache_expiry\": 86400,\n" +
				"	\"members\": [\n" +
				"		{\n" +
				"			\"username\": \"USER_1\",\n" +
				"			\"url\": \"https://myanimelist.net/profile/USER_1\",\n" +
				"			\"image_url\": \"/images/userimages/100.jpg?t=1477233000\"\n" +
				"		},\n" +
				"		{\n" +
				"			\"username\": \"USER_2\",\n" +
				"			\"url\": \"https://myanimelist.net/profile/USER_2\",\n" +
				"			\"image_url\": \"/images/userimages/200.jpg?t=1605380400\"\n" +
				"		}\n" +
				"	]\n" +
				"}";

		mock(mockServer, "/club/1/members/1", response);

		Iterator<ClubMember> members = jikan.query().club(1).members(1)
				.execute().collectList().block().iterator();

		assertNotNull(new UserFriends().toString());

		ClubMember m1 = members.next();
		assertNotNull(m1.toString());
		assertEquals("USER_1", m1.username);
		assertEquals("https://myanimelist.net/profile/USER_1", m1.url);
		assertEquals("/images/userimages/100.jpg?t=1477233000", m1.imageUrl);

		ClubMember m2 = members.next();
		assertNotNull(m2.toString());
		assertEquals("USER_2", m2.username);
		assertEquals("https://myanimelist.net/profile/USER_2", m2.url);
		assertEquals("/images/userimages/200.jpg?t=1605380400", m2.imageUrl);

		assertFalse(members.hasNext());

		assertEquals(m2, m2);
		assertEquals(m2, SerializationUtils.clone(m2));
		assertNotEquals(m2, new Object());
		assertNotEquals(m2, null);
		assertNotEquals(m2, m1);
		int hash = m2.hashCode();

		ClubMembers l = new ClubMembers();
		assertNotNull(l.toString());
		assertEquals(l, l);
		assertEquals(l, SerializationUtils.clone(l));
		assertNotEquals(l, new Object());
		assertNotEquals(l, null);
		hash = l.hashCode();
	}

}
