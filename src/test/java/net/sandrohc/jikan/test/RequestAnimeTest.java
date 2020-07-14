package net.sandrohc.jikan.test;

import net.sandrohc.jikan.model.anime.Anime;
import org.junit.jupiter.api.Test;

import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.junit.jupiter.api.Assertions.*;

public class RequestAnimeTest extends RequestTest {

	@Test
	void fetchAnimeById() {
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
						  "  \"source\": \"Light novel\"," +
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
						  "  \"scored_by\": 1000," +
						  "  \"rank\": 50," +
						  "  \"popularity\": 60," +
						  "  \"members\": 2000," +
						  "  \"favorites\": 3000," +
						  "  \"synopsis\": \"SYNOPSIS\"," +
						  "  \"background\": \"BACKGROUND\"," +
						  "  \"premiered\": \"Summer 2012\"," +
						  "  \"broadcast\": \"Sundays at 00:00 (JST)\"," +
						  "  \"related\": {" +
						  "    \"Other\": [" +
						  "      {" +
						  "        \"mal_id\": 10000," +
						  "        \"type\": \"anime\"," +
						  "        \"name\": \"RELATED_OTHER\"," +
						  "        \"url\": \"https://example.com/related_other\"" +
						  "      }" +
						  "    ]," +
						  "    \"Sequel\": [" +
						  "      {" +
						  "        \"mal_id\": 11000," +
						  "        \"type\": \"anime\"," +
						  "        \"name\": \"RELATED_SEQUEL\"," +
						  "        \"url\": \"https://example.com/related_sequel\"" +
						  "      }" +
						  "    ]" +
						  "  }," +
						  "  \"producers\": [" +
						  "    {" +
						  "      \"mal_id\": 1000," +
						  "      \"type\": \"anime\"," +
						  "      \"name\": \"PRODUCER\"," +
						  "      \"url\": \"https://example.com/producer\"" +
						  "    }" +
						  "  ]," +
						  "  \"licensors\": [" +
						  "    {" +
						  "      \"mal_id\": 2000," +
						  "      \"type\": \"anime\"," +
						  "      \"name\": \"LICENSOR\"," +
						  "      \"url\": \"https://example.com/licensor\"" +
						  "    }" +
						  "  ]," +
						  "  \"studios\": [" +
						  "    {" +
						  "      \"mal_id\": 3000," +
						  "      \"type\": \"anime\"," +
						  "      \"name\": \"STUDIO\"," +
						  "      \"url\": \"https://example.com/studio\"" +
						  "    }" +
						  "  ]," +
						  "  \"genres\": [" +
						  "    {" +
						  "      \"mal_id\": 1," +
						  "      \"type\": \"anime\"," +
						  "      \"name\": \"Action\"," +
						  "      \"url\": \"https://myanimelist.net/anime/genre/1/Action\"" +
						  "    }," +
						  "    {" +
						  "      \"mal_id\": 2," +
						  "      \"type\": \"anime\"," +
						  "      \"name\": \"Adventure\"," +
						  "      \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\"" +
						  "    }" +
						  "  ]," +
						  "  \"opening_themes\": [ \"OPENING THEME\" ]," +
						  "  \"ending_themes\":  [ \"ENDING THEME\" ]" +
						  "}";

		mock(mockServer, "/anime/1", response);

		Anime anime = jikan.query().anime().get(1).execute().block();

		assertNotNull(anime);
		assertEquals("TITLE", anime.title);
	}

}
