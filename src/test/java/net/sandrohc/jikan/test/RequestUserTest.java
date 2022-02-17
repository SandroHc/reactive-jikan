package net.sandrohc.jikan.test;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.user.UserFriendsQuery;
import net.sandrohc.jikan.query.user.UserProfileQuery;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestUserTest extends RequestTest {

	@Test
	void fetchProfile() {
		// https://api.jikan.moe/v3/user/USERNAME

		mock(mockServer, "/user/USERNAME", response);

		UserProfileQuery query = jikan.query().user().profile("USERNAME");
		User user = query.execute().block();

		assertThat(user).isNotNull();
		assertThat(user.toString()).isNotNull();
		assertThat(user.userId).isEqualTo(12345);
		assertThat(user.username).isEqualTo("USERNAME");
		assertThat(user.url).isEqualTo("https://myanimelist.net/profile/USERNAME");
		assertThat(user.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/12345.jpg?t=1605457800");
		assertThat(user.lastOnline).isEqualTo(OffsetDateTime.parse("2020-11-15T12:25:35+00:00"));
		assertThat(user.gender).isEqualTo(UserGender.MALE);
		assertThat(user.birthday.toLocalDate()).isEqualTo(LocalDate.parse("2020-01-30"));
		assertThat(user.location).isEqualTo("North Pole");
		assertThat(user.joined.toLocalDate()).isEqualTo(LocalDate.parse("2020-01-29"));
		assertThat(user.about).isEqualTo("<b>Example HTML</b>");

		assertThat(user).isEqualTo(user);
		assertThat(SerializationUtils.clone(user)).isEqualTo(user);
		assertThat(new Object()).isNotEqualTo(user);
		assertThat(null).isNotEqualTo(user);
		int hash = user.hashCode();


		UserAnimeStatistics animeStats = user.animeStats;
		assertThat(animeStats.toString()).isNotNull();
		assertThat(animeStats.daysWatched).isEqualTo(10.1D);
		assertThat(animeStats.meanScore).isEqualTo(5.5D);
		assertThat(animeStats.watching).isEqualTo(100);
		assertThat(animeStats.completed).isEqualTo(200);
		assertThat(animeStats.onHold).isEqualTo(300);
		assertThat(animeStats.dropped).isEqualTo(400);
		assertThat(animeStats.planToWatch).isEqualTo(500);
		assertThat(animeStats.totalEntries).isEqualTo(600);
		assertThat(animeStats.rewatched).isEqualTo(700);
		assertThat(animeStats.episodesWatched).isEqualTo(800);

		assertThat(animeStats).isEqualTo(animeStats);
		assertThat(SerializationUtils.clone(animeStats)).isEqualTo(animeStats);
		assertThat(new Object()).isNotEqualTo(animeStats);
		assertThat(null).isNotEqualTo(animeStats);
		hash = animeStats.hashCode();


		UserMangaStatistics mangaStats = user.mangaStats;
		assertThat(mangaStats.toString()).isNotNull();
		assertThat(mangaStats.daysRead).isEqualTo(20.1D);
		assertThat(mangaStats.meanScore).isEqualTo(10.5D);
		assertThat(mangaStats.reading).isEqualTo(900);
		assertThat(mangaStats.completed).isEqualTo(1000);
		assertThat(mangaStats.onHold).isEqualTo(1100);
		assertThat(mangaStats.dropped).isEqualTo(1200);
		assertThat(mangaStats.planToRead).isEqualTo(1300);
		assertThat(mangaStats.totalEntries).isEqualTo(1400);
		assertThat(mangaStats.reread).isEqualTo(1500);
		assertThat(mangaStats.chaptersRead).isEqualTo(1600);
		assertThat(mangaStats.volumesRead).isEqualTo(1700);

		assertThat(mangaStats).isEqualTo(mangaStats);
		assertThat(SerializationUtils.clone(mangaStats)).isEqualTo(mangaStats);
		assertThat(new Object()).isNotEqualTo(mangaStats);
		assertThat(null).isNotEqualTo(mangaStats);
		hash = mangaStats.hashCode();


		UserFavorites favourites = user.favorites;
		assertThat(favourites.toString()).isNotNull();
		assertThat(favourites).isEqualTo(favourites);
		assertThat(SerializationUtils.clone(favourites)).isEqualTo(favourites);
		assertThat(new Object()).isNotEqualTo(favourites);
		assertThat(null).isNotEqualTo(favourites);
		hash = favourites.hashCode();

		Iterator<UserFavoritesSub> favCharacters = favourites.characters.iterator();
		UserFavoritesSub fc1 = favCharacters.next();
		assertThat(fc1.toString()).isNotNull();
		assertThat(fc1.malId).isEqualTo(34470);
		assertThat(fc1.url).isEqualTo("https://myanimelist.net/character/34470/Kurisu_Makise");
		assertThat(fc1.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/10/114399.jpg?s=8bdbe2cd8303e475f27cbf5b4b99d394");
		assertThat(Kurisu", fc1.name).isEqualTo("Makise);

		UserFavoritesSub fc2 = favCharacters.next();
		assertThat(fc2.toString()).isNotNull();
		assertThat(fc2.malId).isEqualTo(20170);
		assertThat(fc2.url).isEqualTo("https://myanimelist.net/character/20170/Victorique_de_Blois");
		assertThat(fc2.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/characters/5/108860.jpg?s=8098d8b3ee4bba8ba648f683115f2b65");
		assertThat(Victorique", fc2.name).isEqualTo("de Blois);

		assertThat(fc2).isEqualTo(fc2);
		assertThat(SerializationUtils.clone(fc2)).isEqualTo(fc2);
		assertThat(new Object()).isNotEqualTo(fc2);
		assertThat(fc1).isNotEqualTo(fc2);
		hash = fc2.hashCode();

		assertThat(favCharacters.hasNext()).isFalse();
	}

	@Test
	void fetchFriends() {
		// https://api.jikan.moe/v3/user/USERNAME/friends/1

		mock(mockServer, "/user/USERNAME/friends/1", response);

		UserFriendsQuery query = jikan.query().user().friends("USERNAME").page(1);
		Collection<UserFriend> friends = query.execute().collectList().block();

		UserFriend f1 = friends.next();
		assertThat(f1.toString()).isNotNull();
		assertThat(f1.username).isEqualTo("FRIEND_1");
		assertThat(f1.url).isEqualTo("https://myanimelist.net/profile/FRIEND_1");
		assertThat(f1.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/userimages/100.jpg?t=1605453000");
		assertThat(f1.lastOnline).isEqualTo(OffsetDateTime.parse("2020-01-30T12:25:35+00:00"));
		assertThat(f1.friendsSince).isEqualTo(OffsetDateTime.parse("2020-02-20T14:35:45+00:00"));

		assertThat(friends.hasNext()).isFalse();

		assertThat(f2).isEqualTo(f2);
		assertThat(SerializationUtils.clone(f2)).isEqualTo(f2);
		assertThat(new Object()).isNotEqualTo(f2);
		assertThat(null).isNotEqualTo(f2);
		assertThat(f1).isNotEqualTo(f2);
		int hash = f2.hashCode();

		UserFriends l = new UserFriends();
		assertThat(l).isEqualTo(l);
		assertThat(SerializationUtils.clone(l)).isEqualTo(l);
		assertThat(new Object()).isNotEqualTo(l);
		assertThat(null).isNotEqualTo(l);
		hash = l.hashCode();
	}
}
