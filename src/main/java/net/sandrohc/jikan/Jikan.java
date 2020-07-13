/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan;

import java.io.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.anime.AnimeQuery;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class Jikan {

	private static final Logger LOG = LoggerFactory.getLogger(Jikan.class);
	public static final Marker MARKER_REQUEST = MarkerFactory.getMarker("REQUEST");

	private static final ObjectMapper JSON_PARSER = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	private static final String JIKAN_BASE_URL = "https://api.jikan.moe/v3/";
	private final String baseUrl;


	public Jikan() {
		this(JIKAN_BASE_URL);
	}

	public Jikan(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Mono<Anime> getAnime(int id) {
		return request(new AnimeQuery(id));
	}

	public <T> Mono<T> request(Query<T> query) {
		final String url = baseUrl + query.getUri();
		LOG.atDebug().addMarker(MARKER_REQUEST).addArgument(url).log("Fetching request: {}");

		return HttpClient.create()
				.headers(h -> h
						.add("Accept", "application/json")
						.add("User-Agent", "reactor-jikan/0.1.0")) // TODO: dynamically get the version
				.get()
				.uri(url)
				.responseContent()
				.aggregate()
				.asString()
				.flatMap(response -> decode(response, query.getRequestClass()));
	}

	private <T> Mono<T> decode(String json, Class<T> clazz) {
		try {
			return Mono.just(JSON_PARSER.readValue(json, clazz));
		} catch (IOException e) {
			return Mono.error(new JikanException("Error decoding JSON for class: " + clazz.getName(), e));
		}
	}

}
