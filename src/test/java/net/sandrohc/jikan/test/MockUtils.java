package net.sandrohc.jikan.test;

import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.Parameter;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockUtils {

	public static final int MOCK_PORT = 8080;
	public static final String MOCK_HOST = "localhost";
	public static final String MOCK_URL = MOCK_HOST + ':' + MOCK_PORT;

	public static ClientAndServer createMockServer() {
		return ClientAndServer.startClientAndServer(MOCK_PORT);
	}

	public static void mockFromFile(ClientAndServer mockServer, String uri, String jsonFile, Parameter... queryParameters) {
		String response = readFileToString(jsonFile);
		mock(mockServer, uri, response);
	}

	public static void mock(ClientAndServer mockServer, String uri, String response, Parameter... queryParameters) {
		mockServer
				.when(request().withMethod("GET").withPath(uri).withQueryStringParameters(queryParameters))
				.respond(response()
						.withStatusCode(200)
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody(response, StandardCharsets.UTF_8)
				);
	}

	public static void mockError(ClientAndServer mockServer, HttpResponseStatus status) {
		String body = "{" +
				" \"status\": " + status.code() + "," +
				" \"message\": \"" + status.reasonPhrase() + "\"," +
				" \"error\": \"" + status.reasonPhrase() + "\"," +
				" \"type\": \"ErrorException\"" +
				" }";

		mockServer
				.when(request())
				.respond(response()
						.withStatusCode(status.code())
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody(body, StandardCharsets.UTF_8)
				);
	}

	public static String readFileToString(String filename) {
		try {
			Path path = Paths.get(Objects.requireNonNull(MockUtils.class.getResource("/responses/" + filename)).toURI());
			byte[] bytes = Files.readAllBytes(path);
			return new String(bytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("Unable to read test file '" + filename + "'", e);
		}
	}
}
