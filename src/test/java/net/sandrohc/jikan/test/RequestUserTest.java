package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.model.user.*;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection"})
public class RequestUserTest extends RequestTest {

	@Test
	void fetchProfile() {
		// https://api.jikan.moe/v3/user/USERNAME

		mock(mockServer, "/user/USERNAME", response);

		UserProfile user = jikan.query().user("USERNAME").profile()
				.execute().block();

		assertNotNull(user);
		assertNotNull(user.toString());
		assertEquals(12345, user.userId);
		assertEquals("USERNAME", user.username);
		assertEquals("https://myanimelist.net/profile/USERNAME", user.url);
		assertEquals("https://cdn.myanimelist.net/images/userimages/12345.jpg?t=1605457800", user.imageUrl);
		assertEquals(OffsetDateTime.parse("2020-11-15T12:25:35+00:00"), user.lastOnline);
		assertEquals(UserGender.MALE, user.gender);
		assertEquals(LocalDate.parse("2020-01-30"), user.birthday.toLocalDate());
		assertEquals("North Pole", user.location);
		assertEquals(LocalDate.parse("2020-01-29"), user.joined.toLocalDate());
		assertEquals("<b>Example HTML</b>", user.about);

		assertEquals(user, user);
		assertEquals(user, SerializationUtils.clone(user));
		assertNotEquals(user, new Object());
		assertNotEquals(user, null);
		int hash = user.hashCode();


		UserAnimeStatistics animeStats = user.animeStats;
		assertNotNull(animeStats.toString());
		assertEquals(10.1D, animeStats.daysWatched);
		assertEquals(5.5D, animeStats.meanScore);
		assertEquals(100, animeStats.watching);
		assertEquals(200, animeStats.completed);
		assertEquals(300, animeStats.onHold);
		assertEquals(400, animeStats.dropped);
		assertEquals(500, animeStats.planToWatch);
		assertEquals(600, animeStats.totalEntries);
		assertEquals(700, animeStats.rewatched);
		assertEquals(800, animeStats.episodesWatched);

		assertEquals(animeStats, animeStats);
		assertEquals(animeStats, SerializationUtils.clone(animeStats));
		assertNotEquals(animeStats, new Object());
		assertNotEquals(animeStats, null);
		hash = animeStats.hashCode();


		UserMangaStatistics mangaStats = user.mangaStats;
		assertNotNull(mangaStats.toString());
		assertEquals(20.1D, mangaStats.daysRead);
		assertEquals(10.5D, mangaStats.meanScore);
		assertEquals(900, mangaStats.reading);
		assertEquals(1000, mangaStats.completed);
		assertEquals(1100, mangaStats.onHold);
		assertEquals(1200, mangaStats.dropped);
		assertEquals(1300, mangaStats.planToRead);
		assertEquals(1400, mangaStats.totalEntries);
		assertEquals(1500, mangaStats.reread);
		assertEquals(1600, mangaStats.chaptersRead);
		assertEquals(1700, mangaStats.volumesRead);

		assertEquals(mangaStats, mangaStats);
		assertEquals(mangaStats, SerializationUtils.clone(mangaStats));
		assertNotEquals(mangaStats, new Object());
		assertNotEquals(mangaStats, null);
		hash = mangaStats.hashCode();


		UserFavorites favourites = user.favorites;
		assertNotNull(favourites.toString());
		assertEquals(favourites, favourites);
		assertEquals(favourites, SerializationUtils.clone(favourites));
		assertNotEquals(favourites, new Object());
		assertNotEquals(favourites, null);
		hash = favourites.hashCode();

		Iterator<UserFavoritesSub> favCharacters = favourites.characters.iterator();
		UserFavoritesSub fc1 = favCharacters.next();
		assertNotNull(fc1.toString());
		assertEquals(34470, fc1.malId);
		assertEquals("https://myanimelist.net/character/34470/Kurisu_Makise", fc1.url);
		assertEquals("https://cdn.myanimelist.net/images/characters/10/114399.jpg?s=8bdbe2cd8303e475f27cbf5b4b99d394", fc1.imageUrl);
		assertEquals("Makise, Kurisu", fc1.name);

		UserFavoritesSub fc2 = favCharacters.next();
		assertNotNull(fc2.toString());
		assertEquals(20170, fc2.malId);
		assertEquals("https://myanimelist.net/character/20170/Victorique_de_Blois", fc2.url);
		assertEquals("https://cdn.myanimelist.net/images/characters/5/108860.jpg?s=8098d8b3ee4bba8ba648f683115f2b65", fc2.imageUrl);
		assertEquals("de Blois, Victorique", fc2.name);

		assertEquals(fc2, fc2);
		assertEquals(fc2, SerializationUtils.clone(fc2));
		assertNotEquals(fc2, new Object());
		assertNotEquals(fc2, fc1);
		hash = fc2.hashCode();

		assertFalse(favCharacters.hasNext());
	}

	@Test
	void fetchHistoryAnime() {
		// https://api.jikan.moe/v3/user/USERNAME/history/anime

		mock(mockServer, "/user/USERNAME/history/anime", response);

		Iterator<UserHistoryEntry> history = jikan.query().user("USERNAME").anime().updates()
				.execute().collectList().block().iterator();

		assertNotNull(new UserHistoryList().toString());

		UserHistoryEntry h1 = history.next();
		assertNotNull(h1.toString());
		assertNotNull(h1.meta.toString());
		assertEquals(790, h1.meta.malId);
		assertEquals(UserHistoryType.ANIME, h1.meta.type);
		assertEquals("Ergo Proxy", h1.meta.name);
		assertEquals("https://myanimelist.net/anime/790", h1.meta.url);
		assertEquals(10, h1.increment);
		assertEquals(OffsetDateTime.parse("2020-01-30T12:25:35+00:00"), h1.date);

		UserHistoryEntry h2 = history.next();
		assertNotNull(h2.toString());
		assertNotNull(h2.meta.toString());
		assertEquals(39491, h2.meta.malId);
		assertEquals(UserHistoryType.ANIME, h2.meta.type);
		assertEquals("Psycho-Pass 3", h2.meta.name);
		assertEquals("https://myanimelist.net/anime/39491", h2.meta.url);
		assertEquals(20, h2.increment);
		assertEquals(OffsetDateTime.parse("2020-02-20T14:35:45+00:00"), h2.date);

		assertFalse(history.hasNext());

		assertEquals(h2, h2);
		assertEquals(h2, SerializationUtils.clone(h2));
		assertNotEquals(h2, h1);
		assertNotEquals(h2, new Object());
		assertNotEquals(h2, null);
		int hash = h2.hashCode();

		UserHistoryList l = new UserHistoryList();
		assertEquals(l, SerializationUtils.clone(l));
		assertNotEquals(l, null);
		hash = l.hashCode();
	}

	@Test
	void fetchHistoryManga() {
		// https://api.jikan.moe/v3/user/USERNAME/history/manga

		mock(mockServer, "/user/USERNAME/history/manga", response);

		Iterator<UserHistoryEntry> history = jikan.query().user("USERNAME").manga().updates()
				.execute().collectList().block().iterator();

		assertNotNull(new UserHistoryList().toString());

		UserHistoryEntry h1 = history.next();
		assertNotNull(h1.toString());
		assertNotNull(h1.meta.toString());
		assertEquals(119792, h1.meta.malId);
		assertEquals(UserHistoryType.MANGA, h1.meta.type);
		assertEquals("Hirasaka Hinako ga Erokawaii Koto wo Ore dake ga Shitteiru.", h1.meta.name);
		assertEquals("https://myanimelist.net/manga/119792", h1.meta.url);
		assertEquals(10, h1.increment);
		assertEquals(OffsetDateTime.parse("2020-01-30T12:25:35+00:00"), h1.date);

		UserHistoryEntry h2 = history.next();
		assertNotNull(h2.toString());
		assertNotNull(h2.meta.toString());
		assertEquals(25, h2.meta.malId);
		assertEquals(UserHistoryType.MANGA, h2.meta.type);
		assertEquals("TEST", h2.meta.name);
		assertEquals("https://myanimelist.net/manga/25", h2.meta.url);
		assertEquals(20, h2.increment);
		assertEquals(OffsetDateTime.parse("2020-02-20T14:35:45+00:00"), h2.date);

		assertFalse(history.hasNext());

		assertEquals(h2, h2);
		assertEquals(h2, SerializationUtils.clone(h2));
		assertNotEquals(h2, new Object());
		assertNotEquals(h2, null);
		assertNotEquals(h2, h1);
		int hash = h2.hashCode();
	}

	@Test
	void fetchFriends() {
		// https://api.jikan.moe/v3/user/USERNAME/friends/1

		mock(mockServer, "/user/USERNAME/friends/1", response);

		Iterator<UserFriend> friends = jikan.query().user("USERNAME").friends(1)
				.execute().collectList().block().iterator();

		assertNotNull(new UserFriends().toString());

		UserFriend f1 = friends.next();
		assertNotNull(f1.toString());
		assertEquals("FRIEND_1", f1.username);
		assertEquals("https://myanimelist.net/profile/FRIEND_1", f1.url);
		assertEquals("https://cdn.myanimelist.net/images/userimages/100.jpg?t=1605453000", f1.imageUrl);
		assertEquals(OffsetDateTime.parse("2020-01-30T12:25:35+00:00"), f1.lastOnline);
		assertEquals(OffsetDateTime.parse("2020-02-20T14:35:45+00:00"), f1.friendsSince);

		UserFriend f2 = friends.next();
		assertNotNull(f2.toString());
		assertEquals("FRIEND_2", f2.username);
		assertEquals("https://myanimelist.net/profile/FRIEND_2", f2.url);
		assertEquals("https://cdn.myanimelist.net/images/userimages/200.jpg?t=1605444000", f2.imageUrl);
		assertEquals(OffsetDateTime.parse("2020-11-30T12:25:35+00:00"), f2.lastOnline);
		assertEquals(OffsetDateTime.parse("2020-12-30T14:35:45+00:00"), f2.friendsSince);

		assertFalse(friends.hasNext());

		assertEquals(f2, f2);
		assertEquals(f2, SerializationUtils.clone(f2));
		assertNotEquals(f2, new Object());
		assertNotEquals(f2, null);
		assertNotEquals(f2, f1);
		int hash = f2.hashCode();

		UserFriends l = new UserFriends();
		assertEquals(l, l);
		assertEquals(l, SerializationUtils.clone(l));
		assertNotEquals(l, new Object());
		assertNotEquals(l, null);
		hash = l.hashCode();
	}

	@Test
	void fetchAnimeList() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/user/USERNAME/animelist/all/1

		mock(mockServer, "/user/USERNAME/animelist/all/1", response);

		Iterator<UserAnime> anime = jikan.query().user("USERNAME").anime().list(1)
				.status(UserAnimeWatchingStatus.ALL)
				.execute().collectList().block().iterator();

		assertNotNull(new UserAnimeList().toString());

		UserAnime a1 = anime.next();
		assertNotNull(a1.toString());
		assertEquals(11757, a1.malId);
		assertEquals("Sword Art Online", a1.title);
		assertEquals("https://myanimelist.net/anime/11757/Sword_Art_Online/video", a1.videoUrl);
		assertEquals("https://myanimelist.net/anime/11757/Sword_Art_Online", a1.url);
		assertEquals("https://cdn.myanimelist.net/images/anime/11/39717.jpg?s=05cc3cdfc451395e2c3b3dcfb46c5d7d", a1.image_url);
		assertEquals(AnimeType.TV, a1.type);
		assertEquals(UserAnimeWatchingStatus.COMPLETED, a1.watchingStatus);
		assertEquals(7, a1.score);
		assertEquals(20, a1.watchedEpisodes);
		assertEquals(30, a1.totalEpisodes);
		assertEquals(UserAnimeAiringStatus.AIRED, a1.airingStatus);
		assertNull(a1.seasonName);
		assertNull(a1.seasonYear);
		assertFalse(a1.hasEpisodeVideo);
		assertTrue(a1.hasPromoVideo);
		assertTrue(a1.hasVideo);
		assertFalse(a1.isRewatching);
		assertEquals("TAG", a1.tags);
		assertEquals(AgeRating.PG13, a1.rating);
		assertEquals(LocalDate.parse("2012-07-08"), a1.startDate.toLocalDate());
		assertEquals(LocalDate.parse("2012-12-23"), a1.endDate.toLocalDate());
		assertEquals(LocalDate.parse("2012-12-30"), a1.watchStartDate.toLocalDate());
		assertEquals(LocalDate.parse("2012-12-31"), a1.watchEndDate.toLocalDate());
		assertEquals(1, a1.days);
		assertEquals(UserAnimeStorage.BLURAY, a1.storage);
		assertEquals(UserPriority.LOW, a1.priority);
		assertFalse(a1.addedToList);
		assertTrue(a1.studios.containsAll(Arrays.asList("STUDIO 1", "STUDIO 2")));
		assertTrue(a1.licensors.containsAll(Arrays.asList("LICENSOR 1", "LICENSOR 2")));

		assertFalse(anime.hasNext());

		assertEquals(a1, a1);
		assertEquals(a1, SerializationUtils.clone(a1));
		assertNotEquals(a1, new Object());
		assertNotEquals(a1, null);
		int hash = a1.hashCode();

		UserAnimeList l = new UserAnimeList();
		assertEquals(l, l);
		assertEquals(l, SerializationUtils.clone(l));
		assertNotEquals(l, new Object());
		assertNotEquals(l, null);
		hash = l.hashCode();

		assertNull(UserAnimeAiringStatus.fromNumber(-1));
		assertNull(UserAnimeWatchingStatus.fromNumber(-1));
	}

	@Test
	void fetchAnimeList_invalidStatus() {
		try {
			jikan.query()
					.user("USERNAME")
					.anime()
					.list(1)
					.status(null);

			fail("accepted null status");
		} catch (JikanInvalidArgumentException e) {
			assertEquals("status must not be null", e.getMessage());
		}
	}

	@Test
	void fetchMangaList() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/user/USERNAME/animelist/all/1

		mock(mockServer, "/user/USERNAME/mangalist/reading/1", response);

		Iterator<UserManga> manga = jikan.query().user("USERNAME").manga().list(1)
				.status(UserMangaReadingStatus.READING)
				.execute().collectList().block().iterator();

		assertNotNull(new UserMangaList().toString());

		UserManga m1 = manga.next();
		assertNotNull(m1.toString());
		assertEquals(11, m1.malId);
		assertEquals("Naruto", m1.title);
		assertEquals("https://myanimelist.net/manga/11/Naruto", m1.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/117681.jpg?s=8fac7e520a634aab9b2c25d0df18a5f1", m1.image_url);
		assertEquals(MangaType.MANGA, m1.type);
		assertEquals(UserMangaReadingStatus.READING, m1.readingStatus);
		assertEquals(0, m1.score);
		assertEquals(688, m1.readChapters);
		assertEquals(0, m1.readVolumes);
		assertEquals(700, m1.totalChapters);
		assertEquals(72, m1.totalVolumes);
		assertEquals(UserMangaPublishingStatus.PUBLISHED, m1.publishingStatus);
		assertFalse(m1.isRereading);
		assertEquals("TAG", m1.tags);
		assertEquals(LocalDate.parse("2000-09-09"), m1.startDate.toLocalDate());
		assertEquals(LocalDate.parse("2014-10-11"), m1.endDate.toLocalDate());
		assertEquals(LocalDate.parse("2010-01-01"), m1.readStartDate.toLocalDate());
		assertEquals(LocalDate.parse("2010-01-03"), m1.readEndDate.toLocalDate());
		assertEquals(2, m1.days);
		assertNull(m1.retail);
		assertEquals(UserPriority.LOW, m1.priority);
		assertFalse(m1.addedToList);
		assertTrue(m1.magazines.containsAll(Arrays.asList("MAGAZINE 1", "MAGAZINE 2")));

		assertFalse(manga.hasNext());

		assertEquals(m1, m1);
		assertEquals(m1, SerializationUtils.clone(m1));
		assertNotEquals(m1, new Object());
		assertNotEquals(m1, null);
		int hash = m1.hashCode();

		UserMangaList l = new UserMangaList();
		assertEquals(l, l);
		assertEquals(l, SerializationUtils.clone(l));
		assertNotEquals(l, new Object());
		assertNotEquals(l, null);
		hash = l.hashCode();

		assertNull(UserMangaReadingStatus.fromNumber(-1));
		assertNull(UserMangaPublishingStatus.fromNumber(-1));
	}

	@Test
	void fetchMangaList_invalidStatus() {
		try {
			jikan.query()
					.user("USERNAME")
					.manga()
					.list(1)
					.status(null);

			fail("accepted null status");
		} catch (JikanInvalidArgumentException e) {
			assertEquals("status must not be null", e.getMessage());
		}
	}

}
