package net.sandrohc.jikan.test;

import java.io.*;
import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.person.PersonPicturesQuery;
import net.sandrohc.jikan.query.person.PersonSearchQuery;
import net.sandrohc.jikan.query.person.PersonTopQuery;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RequestPersonTest extends RequestTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchPerson() {
		// https://api.jikan.moe/v3/person/11817

		mock(mockServer, "/person/11817", response);

		Person person = jikan.query().person().get(11817).execute().block();

		assertThat(person).isNotNull();
		assertThat(person.toString()).isNotNull();
		assertThat(person.malId).isEqualTo(11817);
		assertThat(person.name).isEqualTo("Yoshitsugu Matsuoka");


		Iterator<PersonVoiceActingRole> voiceActingRolesIt = person.voiceActingRoles.iterator();

		PersonVoiceActingRole va1 = voiceActingRolesIt.next();
		assertThat(va1.toString()).isNotNull();
		assertThat(va1.role).isEqualTo("Main");
		assertThat(va1.anime.malId).isEqualTo(11757);
		assertThat(va1.anime.name).isEqualTo("Sword Art Online");
		assertThat(va1.character.malId).isEqualTo(36765);
		assertThat(va1.character.name).isEqualTo("Kirigaya, Kazuto");

		PersonVoiceActingRole va2 = voiceActingRolesIt.next();
		assertThat(va2.toString()).isNotNull();
		assertThat(va2.role).isEqualTo("Supporting");
		assertThat(va2.anime.malId).isEqualTo(31765);
		assertThat(va2.anime.name).isEqualTo("Sword Art Online Movie: Ordinal Scale");
		assertThat(va2.character.malId).isEqualTo(36765);
		assertThat(va2.character.name).isEqualTo("Kirigaya, Kazuto");


		PersonAnimePosition a1 = person.animeStaffPositions.iterator().next();
		assertThat(a1.toString()).isNotNull();
		assertThat(a1.position).isEqualTo("Theme Song Performance");
		assertThat(a1.anime.malId).isEqualTo(30205);
		assertThat(a1.anime.name).isEqualTo("Aoharu x Kikanjuu");


		PersonMangaPosition m1 = person.publishedManga.iterator().next();
		assertThat(m1.toString()).isNotNull();
		assertThat(m1.position).isEqualTo("Story");
		assertThat(m1.manga.malId).isEqualTo(98568);
		assertThat(m1.manga.name).isEqualTo("CV. Ore!");
	}

	@Test
	void fetchPictures() {
		// https://api.jikan.moe/v3/person/11817/pictures

		mock(mockServer, "/person/11817/pictures", response);

		PersonPicturesQuery query = jikan.query().person().pictures(11817);
		Collection<Images> pictures = query.execute().collectList().block();

		assertThat(pictures).isNotNull();

		// Pictures
		Iterator<Images> picturesIt = pictures.iterator();

		Images p1 = picturesIt.next();
		assertThat(p1.toString()).isNotNull();
		assertThat(p1.large).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg");
		assertThat(p1.small).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/3/21725.jpg");

		Images p2 = picturesIt.next();
		assertThat(p2.toString()).isNotNull();
		assertThat(p2.large).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg");
		assertThat(p2.small).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/2/40132.jpg");
	}

	@Test
	void fetchSearch() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/person?q=asuna&page=1&limit=2

		mock(mockServer, "/search/person", response,
				Parameter.param("q", "asuna"),
				Parameter.param("page", "1"),
				Parameter.param("limit", "1"));

		PersonSearchQuery query = jikan.query().person().search()
				.query("asuna")
				.page(1)
				.limit(1);
		Collection<Person> results = query.execute().collectList().block();

		assertThat(results).isNotNull();

		// Results
		Iterator<Person> resultsIt = results.iterator();

		Person r1 = resultsIt.next();
		assertThat(r1.toString()).isNotNull();
		assertThat(r1.malId).isEqualTo(2209);
		assertThat(r1.url).isEqualTo("https://myanimelist.net/people/2209/Kei_Yasunaga");
		assertThat(r1.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/questionmark_23.gif");
		assertThat(r1.name).isEqualTo("Yasunaga, Kei");
		assertTrue(r1.alternativeNames.isEmpty());

		Person r2 = resultsIt.next();
		assertThat(r2.toString()).isNotNull();
		assertThat(r2.malId).isEqualTo(41501);
		assertThat(r2.url).isEqualTo("https://myanimelist.net/people/41501/Asuna_Tomari");
		assertThat(r2.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/3/54025.jpg?s=f04a9fb60e828c9fbbdd66890029b06c");
		assertThat(r2.name).isEqualTo("Tomari, Asuna");
		assertTrue(r2.alternativeNames.isEmpty());

		assertFalse(resultsIt.hasNext());
	}

	@Test
	void fetchTop() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/top/people/1

		mock(mockServer, "/top/people/1", response);

		PersonTopQuery query = jikan.query().person().top().page(1).limit(1);
		Collection<Person> results = query.execute().collectList().block();

		SoftAssertions softly;
		assertThat(results).isNotNull();

		// Results
		Person result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(185);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/people/185/Kana_Hanazawa");
		softly.assertThat(result.name).isEqualTo("Hanazawa, Kana");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/voiceactors/3/57913.jpg?s=99bd07415a909e608ece2ef9bd1867ec");
		softly.assertThat(result.websiteUrl).isEqualTo("https://myanimelist.net/people/185/Kana_Hanazawa");
		softly.assertThat(result.givenName).isEqualTo("花澤 香菜");
		softly.assertThat(result.familyName).isEqualTo("花澤 香菜");
		softly.assertThat(result.alternativeNames).containsExactly("NICK");
		softly.assertThat(result.birthday).isEqualTo(LocalDateTime.of(1989, Month.FEBRUARY, 25, 0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.favorites).isEqualTo(82202);
		softly.assertThat(result.about).isEqualTo("ABOUT");
		softly.assertAll();
	}

}
