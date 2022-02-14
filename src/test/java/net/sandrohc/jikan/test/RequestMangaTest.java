package net.sandrohc.jikan.test;

import java.io.*;
import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.common.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.manga.*;
import net.sandrohc.jikan.model.manga.*;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestMangaTest extends RequestTest {

	@Test
	void fetchManga() {
		// https://api.jikan.moe/v3/manga/96792
		String response = "{\n" +
				"    \"mal_id\": 96792,\n" +
				"    \"url\": \"https://myanimelist.net/manga/96792/Kimetsu_no_Yaiba\",\n" +
				"    \"title\": \"Kimetsu no Yaiba\",\n" +
				"    \"title_english\": \"Demon Slayer: Kimetsu no Yaiba\",\n" +
				"    \"title_synonyms\": [\n" +
				"        \"Blade of Demon Destruction\"\n" +
				"    ],\n" +
				"    \"title_japanese\": \"鬼滅の刃\",\n" +
				"    \"status\": \"Finished\",\n" +
				"    \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/179023.jpg\",\n" +
				"    \"type\": \"Manga\",\n" +
				"    \"volumes\": 1,\n" +
				"    \"chapters\": 207,\n" +
				"    \"publishing\": false,\n" +
				"    \"published\": {\n" +
				"        \"from\": \"2016-02-15T00:00:00+00:00\",\n" +
				"        \"to\": \"2020-05-18T00:00:00+00:00\"\n" +
				"    },\n" +
				"    \"rank\": 185,\n" +
				"    \"score\": 8.38,\n" +
				"    \"scored_by\": 62398,\n" +
				"    \"popularity\": 27,\n" +
				"    \"members\": 118299,\n" +
				"    \"favorites\": 10370,\n" +
				"    \"synopsis\": \"Tanjirou Kamado lives with his impoverished family on a remote mountain...\",\n" +
				"    \"background\": \"As a part of the JUMP START initiative...\",\n" +
				"    \"related\": {\n" +
				"        \"Adaptation\": [\n" +
				"            {\n" +
				"                \"mal_id\": 38000,\n" +
				"                \"type\": \"anime\",\n" +
				"                \"name\": \"Kimetsu no Yaiba\",\n" +
				"                \"url\": \"https://myanimelist.net/anime/38000/Kimetsu_no_Yaiba\"\n" +
				"            }\n" +
				"        ]\n" +
				"    },\n" +
				"    \"genres\": [\n" +
				"        {\n" +
				"            \"mal_id\": 1,\n" +
				"            \"type\": \"manga\",\n" +
				"            \"name\": \"Action\",\n" +
				"            \"url\": \"https://myanimelist.net/manga/genre/1/Action\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"authors\": [\n" +
				"        {\n" +
				"            \"mal_id\": 40398,\n" +
				"            \"type\": \"people\",\n" +
				"            \"name\": \"Gotouge, Koyoharu\",\n" +
				"            \"url\": \"https://myanimelist.net/people/40398/Koyoharu_Gotouge\"\n" +
				"        }\n" +
				"    ],\n" +
				"    \"serializations\": [\n" +
				"        {\n" +
				"            \"mal_id\": 83,\n" +
				"            \"type\": \"manga\",\n" +
				"            \"name\": \"Shounen Jump (Weekly)\",\n" +
				"            \"url\": \"https://myanimelist.net/manga/magazine/83/Shounen_Jump_Weekly\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792", response);

		Manga manga = jikan.query().manga().get(96792).execute().block();

		assertNotNull(manga);
		assertNotNull(manga.toString());
		assertEquals(96792, manga.malId);
		assertEquals("https://myanimelist.net/manga/96792/Kimetsu_no_Yaiba", manga.url);
		assertEquals("Kimetsu no Yaiba", manga.title);
		assertEquals("Demon Slayer: Kimetsu no Yaiba", manga.titleEnglish);
		assertEquals("鬼滅の刃", manga.titleJapanese);
		assertEquals("Blade of Demon Destruction", manga.titleSynonyms.iterator().next());
		assertEquals(MangaStatus.COMPLETED, manga.status);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/179023.jpg", manga.imageUrl);
		assertEquals(MangaType.MANGA, manga.type);
		assertEquals(1, manga.volumes);
		assertEquals(207, manga.chapters);
		assertFalse(manga.publishing);
		assertEquals(LocalDate.parse("2016-02-15"), manga.published.from);
		assertEquals(LocalDate.parse("2020-05-18"), manga.published.to);
		assertEquals(185, manga.rank);
		assertEquals(8.38F, manga.score);
		assertEquals(62398, manga.scoredBy);
		assertEquals(27, manga.popularity);
		assertEquals(118299, manga.members);
		assertEquals(10370, manga.favorites);
		assertEquals("Tanjirou Kamado lives with his impoverished family on a remote mountain...", manga.synopsis);
		assertEquals("As a part of the JUMP START initiative...", manga.background);


		MalSubEntity adaptation = manga.related.adaptations.iterator().next();
		assertEquals(38000, adaptation.malId);
		assertEquals("Kimetsu no Yaiba", adaptation.name);
		assertEquals(Type.ANIME, adaptation.type);

		assertTrue(manga.related.prequels.isEmpty());
		assertTrue(manga.related.alternativeVersions.isEmpty());
		assertTrue(manga.related.alternativeSettings.isEmpty());
		assertTrue(manga.related.characters.isEmpty());
		assertTrue(manga.related.spinOffs.isEmpty());
		assertTrue(manga.related.others.isEmpty());
		assertTrue(manga.related.sequels.isEmpty());
		assertTrue(manga.related.summaries.isEmpty());
		assertTrue(manga.related.sideStories.isEmpty());
		assertTrue(manga.related.parentStories.isEmpty());
		assertTrue(manga.related.fullStories.isEmpty());


		GenreEntity<MangaGenre> genre = manga.genres.iterator().next();
		assertEquals(1, genre.malId);
		assertEquals(MangaGenre.ACTION, genre.name);
		assertEquals(Type.MANGA, genre.type);


		MalSubEntity author = manga.authors.iterator().next();
		assertEquals(40398, author.malId);
		assertEquals(Type.PERSON, author.type);
		assertEquals("Gotouge, Koyoharu", author.name);
		assertEquals("https://myanimelist.net/people/40398/Koyoharu_Gotouge", author.url);


		MalSubEntity serialization = manga.serializations.iterator().next();
		assertEquals(83, serialization.malId);
		assertEquals(Type.MANGA, serialization.type);
		assertEquals("Shounen Jump (Weekly)", serialization.name);
		assertEquals("https://myanimelist.net/manga/magazine/83/Shounen_Jump_Weekly", serialization.url);
	}

	@Test
	void fetchCharacters() {
		// https://api.jikan.moe/v3/manga/96792/characters
		String response = "{\n" +
				"    \"characters\": [\n" +
				"        {\n" +
				"            \"mal_id\": 146157,\n" +
				"            \"url\": \"https://myanimelist.net/character/146157/Nezuko_Kamado\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/characters/15/384829.jpg?s=0d2ee584ea2186ad5ad05b0ddecb376d\",\n" +
				"            \"name\": \"Kamado, Nezuko\",\n" +
				"            \"role\": \"Main\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"mal_id\": 146156,\n" +
				"            \"url\": \"https://myanimelist.net/character/146156/Tanjirou_Kamado\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/characters/6/386735.jpg?s=7327e90f2310ececd18696cd4aa2ff4e\",\n" +
				"            \"name\": \"Kamado, Tanjirou\",\n" +
				"            \"role\": \"Main\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/characters", response);

		Collection<MangaCharacter> characters = jikan.query().manga().characters(96792).execute().collectList().block();

		assertNotNull(characters);
		assertNotNull(new MangaCharacters().toString());

		/* Characters */
		Iterator<MangaCharacter> charactersIt = characters.iterator();

		MangaCharacter character1 = charactersIt.next();
		assertNotNull(character1.toString());
		assertEquals(146157, character1.malId);
		assertEquals("Kamado, Nezuko", character1.name);
		assertEquals("Main", character1.role);

		MangaCharacter character2 = charactersIt.next();
		assertNotNull(character2.toString());
		assertEquals(146156, character2.malId);
		assertEquals("Kamado, Tanjirou", character2.name);
		assertEquals("Main", character2.role);

		assertFalse(charactersIt.hasNext());
	}

	@Test
	void fetchNews() {
		// https://api.jikan.moe/v3/manga/96792/news
		String response = "{\n" +
				"    \"articles\": [\n" +
				"        {\n" +
				"            \"url\": \"https://myanimelist.net/news/60161703\",\n" +
				"            \"title\": \"North American Anime & Manga Releases for July\",\n" +
				"            \"date\": \"2020-07-07T15:39:00+00:00\",\n" +
				"            \"author_name\": \"ImperfectBlue\",\n" +
				"            \"author_url\": \"https://myanimelist.net/profile/ImperfectBlue\",\n" +
				"            \"forum_url\": \"https://myanimelist.net/forum/?topicid=1850747\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/s/common/uploaded_files/1594161493-e76e48dafd1b0f67ece6f1fa065db158.jpeg?s=e24b9ee8cfa8d123bf27fbbd9aef0d27\",\n" +
				"            \"comments\": 0,\n" +
				"            \"intro\": \"Here are the North American anime & manga releases...\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"url\": \"https://myanimelist.net/news/59888183\",\n" +
				"            \"title\": \"Japan's Yearly Manga and Light Novel Rankings for 2020 (First Half)\",\n" +
				"            \"date\": \"2020-05-28T03:00:00+00:00\",\n" +
				"            \"author_name\": \"Snow\",\n" +
				"            \"author_url\": \"https://myanimelist.net/profile/Snow\",\n" +
				"            \"forum_url\": \"https://myanimelist.net/forum/?topicid=1843163\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/s/common/uploaded_files/1590659927-9b5430bf163b788d5df19a4f37901b83.jpeg?s=e07e5ba98224f4e8fad5fafa60f25ad3\",\n" +
				"            \"comments\": 79,\n" +
				"            \"intro\": \"Here are the manga and light novel sales rankings...\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/news", response);

		Collection<NewsArticle> newsArticles = jikan.query().manga().news(96792).execute().collectList().block();

		assertNotNull(newsArticles);

		/* Articles */
		Iterator<NewsArticle> articlesIt = newsArticles.iterator();

		NewsArticle a1 = articlesIt.next();
		assertNotNull(a1.toString());
		assertEquals("https://myanimelist.net/news/60161703", a1.url);
		assertEquals("North American Anime & Manga Releases for July", a1.title);
		assertEquals(OffsetDateTime.parse("2020-07-07T15:39:00+00:00"), a1.date);
		assertEquals("ImperfectBlue", a1.authorName);
		assertEquals("https://myanimelist.net/forum/?topicid=1850747", a1.forumUrl);
		assertEquals(0, a1.comments);
		assertEquals("Here are the North American anime & manga releases...", a1.intro);

		NewsArticle a2 = articlesIt.next();
		assertNotNull(a2.toString());
		assertEquals("https://myanimelist.net/news/59888183", a2.url);
		assertEquals("Japan's Yearly Manga and Light Novel Rankings for 2020 (First Half)", a2.title);
		assertEquals(OffsetDateTime.parse("2020-05-28T03:00:00+00:00"), a2.date);
		assertEquals("Snow", a2.authorName);
		assertEquals("https://myanimelist.net/forum/?topicid=1843163", a2.forumUrl);
		assertEquals(79, a2.comments);
		assertEquals("Here are the manga and light novel sales rankings...", a2.intro);
	}

	@Test
	void fetchPictures() {
		// https://api.jikan.moe/v3/manga/96792/pictures
		String response = "{\n" +
				"    \"pictures\": [\n" +
				"        {\n" +
				"            \"large\": \"https://cdn.myanimelist.net/images/manga/1/172790l.jpg\",\n" +
				"            \"small\": \"https://cdn.myanimelist.net/images/manga/1/172790.jpg\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"large\": \"https://cdn.myanimelist.net/images/manga/1/172791l.jpg\",\n" +
				"            \"small\": \"https://cdn.myanimelist.net/images/manga/1/172791.jpg\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/pictures", response);

		Collection<Picture> pictures = jikan.query().manga().pictures(96792).execute().collectList().block();

		assertNotNull(pictures);

		/* Pictures */
		Iterator<Picture> picturesIt = pictures.iterator();

		Picture p1 = picturesIt.next();
		assertNotNull(p1.toString());
		assertEquals("https://cdn.myanimelist.net/images/manga/1/172790l.jpg", p1.large);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/172790.jpg", p1.small);

		Picture p2 = picturesIt.next();
		assertNotNull(p2.toString());
		assertEquals("https://cdn.myanimelist.net/images/manga/1/172791l.jpg", p2.large);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/172791.jpg", p2.small);
	}

	@Test
	void fetchStats() {
		// https://api.jikan.moe/v3/manga/96792/stats
		String response = "{\n" +
				"    \"reading\": 54058,\n" +
				"    \"completed\": 42711,\n" +
				"    \"on_hold\": 3209,\n" +
				"    \"dropped\": 1906,\n" +
				"    \"plan_to_read\": 16489,\n" +
				"    \"total\": 118373,\n" +
				"    \"scores\": {\n" +
				"        \"1\": { \"votes\": 209, \"percentage\": 0.3 },\n" +
				"        \"2\": { \"votes\": 111, \"percentage\": 0.2 },\n" +
				"        \"3\": { \"votes\": 243, \"percentage\": 0.4 },\n" +
				"        \"4\": { \"votes\": 489, \"percentage\": 0.8 },\n" +
				"        \"5\": { \"votes\": 1245, \"percentage\": 2 },\n" +
				"        \"6\": { \"votes\": 2863, \"percentage\": 4.6 },\n" +
				"        \"7\": { \"votes\": 7971, \"percentage\": 12.8 },\n" +
				"        \"8\": { \"votes\": 16494, \"percentage\": 26.4 },\n" +
				"        \"9\": { \"votes\": 18747, \"percentage\": 30 },\n" +
				"        \"10\": { \"votes\": 14086, \"percentage\": 22.6 }\n" +
				"    }\n" +
				"}";

		mock(mockServer, "/manga/96792/stats", response);

		Statistics statistics = jikan.query().manga().stats(96792).execute().block();

		assertNotNull(statistics);
		assertNotNull(statistics.toString());
		assertEquals(54058,   statistics.seeing);
		assertEquals(42711,  statistics.completed);
		assertEquals(3209,   statistics.onHold);
		assertEquals(1906,   statistics.dropped);
		assertEquals(16489,  statistics.planToSee);
		assertEquals(118373, statistics.total);
		assertEquals(209,   statistics.scores.get(1).votes);  assertEquals(0.3F,  statistics.scores.get(1).percentage);
		assertEquals(111,   statistics.scores.get(2).votes);  assertEquals(0.2F,  statistics.scores.get(2).percentage);
		assertEquals(243,   statistics.scores.get(3).votes);  assertEquals(0.4F,  statistics.scores.get(3).percentage);
		assertEquals(489,   statistics.scores.get(4).votes);  assertEquals(0.8F,  statistics.scores.get(4).percentage);
		assertEquals(1245,  statistics.scores.get(5).votes);  assertEquals(2.0F,  statistics.scores.get(5).percentage);
		assertEquals(2863,  statistics.scores.get(6).votes);  assertEquals(4.6F,  statistics.scores.get(6).percentage);
		assertEquals(7971,  statistics.scores.get(7).votes);  assertEquals(12.8F, statistics.scores.get(7).percentage);
		assertEquals(16494, statistics.scores.get(8).votes);  assertEquals(26.4F, statistics.scores.get(8).percentage);
		assertEquals(18747, statistics.scores.get(9).votes);  assertEquals(30.0F, statistics.scores.get(9).percentage);
		assertEquals(14086, statistics.scores.get(10).votes); assertEquals(22.6F, statistics.scores.get(10).percentage);
		assertNotNull(statistics.scores.values().iterator().next().toString());

		int totalScores = statistics.scores.values().stream().mapToInt(s -> s.votes).sum();
		assertTrue(statistics.total >= totalScores);
	}

	@Test
	void fetchForum() {
		// https://api.jikan.moe/v3/manga/96792/forum
		String response = "{\n" +
				"    \"topics\": [\n" +
				"        {\n" +
				"            \"topic_id\": 1731050,\n" +
				"            \"url\": \"https://myanimelist.net/forum/?topicid=1731050\",\n" +
				"            \"title\": \"Kimetsu no Yaiba Chapter 110 Discussion\",\n" +
				"            \"date_posted\": \"2018-05-22T00:00:00+00:00\",\n" +
				"            \"author_name\": \"AUTHOR\",\n" +
				"            \"author_url\": \"https://myanimelist.net/profile/AUTHOR\",\n" +
				"            \"replies\": 8,\n" +
				"            \"last_post\": {\n" +
				"                \"url\": \"https://myanimelist.net/forum/?topicid=1731050&goto=lastpost\",\n" +
				"                \"author_name\": \"LAST POST\",\n" +
				"                \"author_url\": \"https://myanimelist.net/profile/LAST POST\",\n" +
				"                \"date_posted\": \"2020-07-16T09:12:21+00:00\"\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/forum", response);

		Collection<ForumTopic> forumTopics = jikan.query().manga().forum(96792).execute().collectList().block();

		assertNotNull(forumTopics);
		assertNotNull(forumTopics.toString());

		/* Topics */
		Iterator<ForumTopic> topicsIt = forumTopics.iterator();

		ForumTopic t1 = topicsIt.next();
		assertNotNull(t1.toString());
		assertEquals(1731050, t1.topicId);
		assertEquals("https://myanimelist.net/forum/?topicid=1731050", t1.url);
		assertEquals("Kimetsu no Yaiba Chapter 110 Discussion", t1.title);
		assertEquals(OffsetDateTime.parse("2018-05-22T00:00:00+00:00"), t1.datePosted);
		assertEquals("AUTHOR", t1.authorName);
		assertEquals(8, t1.replies);

		ForumTopicPost t1Last = t1.lastPost;
		assertNotNull(t1Last.toString());
		assertEquals("https://myanimelist.net/forum/?topicid=1731050&goto=lastpost", t1Last.url);
		assertEquals("LAST POST", t1Last.authorName);
		assertEquals(OffsetDateTime.parse("2020-07-16T09:12:21+00:00"), t1Last.datePosted);

		assertFalse(topicsIt.hasNext());
	}

	@Test
	void fetchMoreInfo() {
		// https://api.jikan.moe/v3/manga/96792/moreinfo
		String response = "{\n" +
				"    \"moreinfo\": \"Volume 7 contains a side story...\"\n" +
				"}";

		mock(mockServer, "/manga/96792/moreinfo", response);

		MoreInfo moreInfo = jikan.query().manga().moreInfo(96792).execute().block();

		assertNotNull(moreInfo);
		assertNotNull(moreInfo.toString());
		assertEquals("Volume 7 contains a side story...", moreInfo.moreInfo);
	}

	@Test
	void fetchReviews() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/manga/96792/reviews/1
		String response = "{\n" +
				"    \"reviews\": [\n" +
				"        {\n" +
				"            \"mal_id\": 340220,\n" +
				"            \"url\": \"https://myanimelist.net/reviews.php?id=340220\",\n" +
				"            \"type\": null,\n" +
				"            \"helpful_count\": 259,\n" +
				"            \"date\": \"2020-05-17T08:50:00+00:00\",\n" +
				"            \"reviewer\": {\n" +
				"                \"url\": \"https://myanimelist.net/profile/abystoma2\",\n" +
				"                \"image_url\": \"https://cdn.myanimelist.net/images/userimages/3336425.jpg?t=1594912200\",\n" +
				"                \"username\": \"abystoma2\",\n" +
				"                \"chapters_read\": 207,\n" +
				"                \"scores\": {\n" +
				"                    \"overall\": 5,\n" +
				"                    \"story\": 5,\n" +
				"                    \"art\": 7,\n" +
				"                    \"character\": 6,\n" +
				"                    \"enjoyment\": 5\n" +
				"                }\n" +
				"            },\n" +
				"            \"content\": \"As of the time of writing this review...\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/reviews/1", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().manga().reviews(10, 0), "page starts at index 1");

		Collection<Review> reviews = jikan.query().manga().reviews(96792, 1).execute().collectList().block();

		assertNotNull(reviews);

		/* Reviews */
		Iterator<Review> reviewsIt = reviews.iterator();

		Review review = reviewsIt.next();
		assertNotNull(review.toString());
		assertEquals(340220, review.malId);
		assertEquals("https://myanimelist.net/reviews.php?id=340220", review.url);
		assertEquals(259, review.helpfulCount);
		assertEquals(OffsetDateTime.parse("2020-05-17T08:50:00+00:00"), review.date);
		assertEquals("As of the time of writing this review...", review.content);

		Reviewer reviewer = review.reviewer;
		assertNotNull(reviewer.toString());
		assertEquals("https://myanimelist.net/profile/abystoma2", reviewer.url);
		assertEquals("abystoma2", reviewer.username);
		assertEquals(207, reviewer.read);

		ReviewScores scores = reviewer.scores;
		assertNotNull(scores);
		assertNotNull(scores.toString());
		assertEquals(5, scores.overall);
		assertEquals(5, scores.story);
		assertEquals(7, scores.art);
		assertEquals(6, scores.character);
		assertEquals(5, scores.enjoyment);

		assertFalse(reviewsIt.hasNext());
	}

	@Test
	void fetchRecommendations() {
		// https://api.jikan.moe/v3/manga/96792/recommendations
		String response = "{\n" +
				"    \"recommendations\": [\n" +
				"        {\n" +
				"            \"mal_id\": 25,\n" +
				"            \"url\": \"https://myanimelist.net/manga/25/Fullmetal_Alchemist\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/1/27600.jpg?s=9d0238ae01b9fbab777e64b023b7d654\",\n" +
				"            \"recommendation_url\": \"https://myanimelist.net/recommendations/manga/25-96792\",\n" +
				"            \"title\": \"Fullmetal Alchemist\",\n" +
				"            \"recommendation_count\": 3\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/recommendations", response);

		Collection<Recommendation> recommendations = jikan.query().manga().recommendations(96792).execute().collectList().block();

		assertNotNull(recommendations);

		/* Recommendations */
		Iterator<Recommendation> recommendationsIt = recommendations.iterator();

		Recommendation recommendation = recommendationsIt.next();
		assertNotNull(recommendation.toString());
		assertEquals(25, recommendation.malId);
		assertEquals("https://myanimelist.net/manga/25/Fullmetal_Alchemist", recommendation.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/27600.jpg?s=9d0238ae01b9fbab777e64b023b7d654", recommendation.imageUrl);
		assertEquals("https://myanimelist.net/recommendations/manga/25-96792", recommendation.recommendationUrl);
		assertEquals("Fullmetal Alchemist", recommendation.title);
		assertEquals(3, recommendation.recommendationCount);

		assertFalse(recommendationsIt.hasNext());
	}

	@Test
	void fetchUserUpdates() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/manga/96792/userupdates/1
		String response = "{\n" +
				"    \"users\": [\n" +
				"        {\n" +
				"            \"username\": \"Vincent1307\",\n" +
				"            \"url\": \"https://myanimelist.net/profile/Vincent1307\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/questionmark_50.gif\",\n" +
				"            \"score\": 8.5,\n" +
				"            \"status\": \"Completed\",\n" +
				"            \"chapters_seen\": 10,\n" +
				"            \"chapters_total\": 10,\n" +
				"            \"date\": \"2020-07-16T20:15:05+00:00\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/manga/96792/userupdates/1", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().manga().userUpdates(10, 0), "page starts at index 1");

		Collection<UserUpdate> userUpdates = jikan.query().manga().userUpdates(96792, 1).execute().collectList().block();

		assertNotNull(userUpdates);

		/* User Updates */
		Iterator<UserUpdate> usersIt = userUpdates.iterator();

		UserUpdate userUpdate = usersIt.next();
		assertNotNull(userUpdate.toString());
		assertEquals("Vincent1307", userUpdate.username);
		assertEquals("https://myanimelist.net/profile/Vincent1307", userUpdate.url);
		assertEquals(8.5F, userUpdate.score);
		assertEquals("Completed", userUpdate.status);
		assertEquals(10, userUpdate.seen);
		assertEquals(10, userUpdate.total);
		assertEquals(OffsetDateTime.parse("2020-07-16T20:15:05+00:00"), userUpdate.date);

		assertFalse(usersIt.hasNext());
	}

	@Test
	void fetchSearch() throws UnsupportedEncodingException, JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/search/manga?q=test&page=1&limit=2
		String response = "{\n" +
						  "    \"results\": [\n" +
						  "        {\n" +
						  "            \"mal_id\": 2915,\n" +
						  "            \"url\": \"https://myanimelist.net/manga/2915/Testarotho\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/200365.jpg?s=2925c3aa1fca9d2e89f5480cfbf95984\",\n" +
						  "            \"title\": \"Testarotho\",\n" +
						  "            \"publishing\": false,\n" +
						  "            \"synopsis\": \"Capria has spent her life sheltered...\",\n" +
						  "            \"type\": \"Manga\",\n" +
						  "            \"chapters\": 26,\n" +
						  "            \"volumes\": 4,\n" +
						  "            \"score\": 6.6,\n" +
						  "            \"start_date\": \"2000-06-09T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2002-10-09T00:00:00+00:00\",\n" +
						  "            \"members\": 612\n" +
						  "        },\n" +
						  "        {\n" +
						  "            \"mal_id\": 12087,\n" +
						  "            \"url\": \"https://myanimelist.net/manga/12087/Testify\",\n" +
						  "            \"image_url\": \"https://cdn.myanimelist.net/images/manga/1/16557.jpg?s=a0a3be348323d317207d9aa35db7c3a3\",\n" +
						  "            \"title\": \"Testify\",\n" +
						  "            \"publishing\": false,\n" +
						  "            \"synopsis\": \"\",\n" +
						  "            \"type\": \"One-shot\",\n" +
						  "            \"chapters\": 1,\n" +
						  "            \"volumes\": 0,\n" +
						  "            \"score\": 5.73,\n" +
						  "            \"start_date\": \"2004-11-17T00:00:00+00:00\",\n" +
						  "            \"end_date\": \"2004-11-17T00:00:00+00:00\",\n" +
						  "            \"members\": 404\n" +
						  "        }\n" +
						  "    ],\n" +
						  "    \"last_page\": 20\n" +
						  "}";

		mock(mockServer, "/search/manga", response,
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

		Collection<MangaSearchSub> results = jikan.query().manga().search()
				.query("test")
				.page(1)
				.limit(1)
				.genres(MangaGenre.ACTION, MangaGenre.ADVENTURE)
				.status(MangaStatus.COMPLETED)
				.orderBy(MangaOrderBy.ID)
				.sort(Sort.ASCENDING)
				.rated(AgeRating.PG)
				.score(1.0F)
				.startDate(LocalDate.parse("2020-01-01"))
				.endDate(LocalDate.parse("2020-12-31"))
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new MangaSearch().toString());

		/* Results */
		Iterator<MangaSearchSub> resultsIt = results.iterator();

		MangaSearchSub m1 = resultsIt.next();
		assertNotNull(m1.toString());
		assertEquals(2915, m1.malId);
		assertEquals("https://myanimelist.net/manga/2915/Testarotho", m1.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/200365.jpg?s=2925c3aa1fca9d2e89f5480cfbf95984", m1.imageUrl);
		assertEquals("Testarotho", m1.title);
		assertFalse(m1.publishing);
		assertEquals("Capria has spent her life sheltered...", m1.synopsis);
		assertEquals(MangaType.MANGA, m1.type);
		assertEquals(26, m1.chapters);
		assertEquals(4, m1.volumes);
		assertEquals(6.60F, m1.score);
		assertEquals(LocalDate.parse("2000-06-09"), m1.published.from);
		assertEquals(LocalDate.parse("2002-10-09"), m1.published.to);
		assertEquals(612, m1.members);

		MangaSearchSub m2 = resultsIt.next();
		assertNotNull(m2.toString());
		assertEquals(12087, m2.malId);
		assertEquals("https://myanimelist.net/manga/12087/Testify", m2.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/16557.jpg?s=a0a3be348323d317207d9aa35db7c3a3", m2.imageUrl);
		assertEquals("Testify", m2.title);
		assertFalse(m2.publishing);
		assertEquals("", m2.synopsis);
		assertEquals(MangaType.ONESHOT, m2.type);
		assertEquals(1, m2.chapters);
		assertEquals(0, m2.volumes);
		assertEquals(5.73F, m2.score);
		assertEquals(LocalDate.parse("2004-11-17"), m2.published.from);
		assertEquals(LocalDate.parse("2004-11-17"), m2.published.to);
		assertEquals(404, m2.members);

		assertFalse(resultsIt.hasNext());
	}

	@Test
	void fetchSearch_excludeGenres() {
		// https://api.jikan.moe/v3/search/manga?genre[]=1&genre_exclude=1
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/manga", response,
				Parameter.param("genre[]", "1"),
				Parameter.param("genre_exclude", "1"));

		Collection<MangaSearchSub> results = jikan.query().manga().search()
				.genres(MangaGenre.ACTION)
				.excludeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
	}

	@Test
	void fetchSearch_includeGenres() {
		// https://api.jikan.moe/v3/search/manga?genre_exclude=1
		String response = "{\n" +
				"    \"results\": [],\n" +
				"    \"last_page\": 0\n" +
				"}";

		mock(mockServer, "/search/manga", response,
				Parameter.param("genre_exclude", "1"));

		Collection<MangaSearchSub> results = jikan.query().manga().search()
				.genres(MangaGenre.ACTION)
				.includeGivenGenres()
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
	}

	@Test
	void fetchTop() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/top/manga/1/manhwa
		String response = "{\n" +
				"    \"top\": [\n" +
				"        {\n" +
				"            \"mal_id\": 121496,\n" +
				"            \"rank\": 1,\n" +
				"            \"title\": \"Solo Leveling\",\n" +
				"            \"url\": \"https://myanimelist.net/manga/121496/Solo_Leveling\",\n" +
				"            \"type\": \"Manhwa\",\n" +
				"            \"volumes\": null,\n" +
				"            \"start_date\": \"Mar 2018\",\n" +
				"            \"end_date\": null,\n" +
				"            \"members\": 88737,\n" +
				"            \"score\": 8.94,\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/3/222295.jpg?s=b3abea95ceaccea8adf223bd0e4047b6\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/top/manga/1/manhwa", response);

		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().manga().top(0), "page starts at index 1");

		Collection<MangaTopSub> results = jikan.query().manga().top(1).subtype(MangaSubType.MANHWA)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new MangaTop().toString());

		/* Results */
		MangaTopSub result = results.iterator().next();
		assertNotNull(result.toString());
		assertEquals(121496, result.malId);
		assertEquals(1, result.rank);
		assertEquals("Solo Leveling", result.title);
		assertEquals("https://myanimelist.net/manga/121496/Solo_Leveling", result.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/3/222295.jpg?s=b3abea95ceaccea8adf223bd0e4047b6", result.imageUrl);
		assertEquals(MangaType.MANHWA, result.type);
		assertEquals(0, result.volumes);
		assertEquals("Mar 2018", result.startDate);
		assertNull(result.endDate);
		assertEquals(88737, result.members);
		assertEquals(8.94F, result.score);
	}

	@Test
	void fetchGenre() throws JikanInvalidArgumentException {
		// https://api.jikan.moe/v3/genre/manga/1/1
		String response = "{\n" +
				"    \"last_page\": 1,\n" +
				"    \"mal_url\": {\n" +
				"        \"mal_id\": 0,\n" +
				"        \"type\": \"manga\",\n" +
				"        \"name\": \"\",\n" +
				"        \"url\": \"\"\n" +
				"    },\n" +
				"    \"item_count\": 14,\n" +
				"    \"manga\": [\n" +
				"        {\n" +
				"            \"mal_id\": 118156,\n" +
				"            \"url\": \"https://myanimelist.net/manga/118156/Arata_Primal\",\n" +
				"            \"title\": \"Arata Primal\",\n" +
				"            \"image_url\": \"https://cdn.myanimelist.net/images/manga/1/220252.jpg\",\n" +
				"            \"type\": \"Manga\",\n" +
				"            \"volumes\": 4,\n" +
				"            \"score\": 6.33,\n" +
				"            \"members\": 761,\n" +
				"            \"synopsis\": \"The key to save the world is in the primeval period!?\",\n" +
				"            \"genres\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 1,\n" +
				"                    \"type\": \"manga\",\n" +
				"                    \"name\": \"Action\",\n" +
				"                    \"url\": \"https://myanimelist.net/manga/genre/1/Action\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"authors\": [\n" +
				"                {\n" +
				"                    \"mal_id\": 4640,\n" +
				"                    \"type\": \"people\",\n" +
				"                    \"name\": \"Murase, Katsutoshi\",\n" +
				"                    \"url\": \"https://myanimelist.net/people/4640/Katsutoshi_Murase\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"serialization\": [\n" +
				"                \"Shounen Jump+\"\n" +
				"            ]\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		mock(mockServer, "/genre/manga/1/1", response);

		Collection<MangaGenreSub> results = jikan.query().manga().genre(MangaGenre.ACTION, 1)
				.execute()
				.collectList()
				.block();

		assertNotNull(results);
		assertNotNull(new MangaGenreList().toString());

		/* Results */
		MangaGenreSub result = results.iterator().next();
		assertNotNull(result.toString());
		assertEquals(118156, result.malId);
		assertEquals("Arata Primal", result.title);
		assertEquals("https://myanimelist.net/manga/118156/Arata_Primal", result.url);
		assertEquals("https://cdn.myanimelist.net/images/manga/1/220252.jpg", result.imageUrl);
		assertEquals("The key to save the world is in the primeval period!?", result.synopsis);
		assertEquals(MangaType.MANGA, result.type);
		assertEquals(4, result.volumes);
		assertEquals(761, result.members);
		assertEquals(6.33F, result.score);
		assertTrue(result.serialization.contains("Shounen Jump+"));

		GenreEntity<MangaGenre> genre = result.genres.iterator().next();
		assertEquals(1, genre.malId);
		assertEquals(Type.MANGA, genre.type);
		assertEquals(MangaGenre.ACTION, genre.name);
		assertEquals("https://myanimelist.net/manga/genre/1/Action", genre.url);

		MalSubEntity author = result.authors.iterator().next();
		assertEquals(4640, author.malId);
		assertEquals(Type.PERSON, author.type);
		assertEquals("Murase, Katsutoshi", author.name);
		assertEquals("https://myanimelist.net/people/4640/Katsutoshi_Murase", author.url);
	}

	@Test
	void fetchGenre_invalidParameters() {
		assertThrows(JikanInvalidArgumentException.class, () -> jikan.query().manga().genre(MangaGenre.ACTION, 0), "page starts at index 1");
	}

}
