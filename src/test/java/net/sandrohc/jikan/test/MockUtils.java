package net.sandrohc.jikan.test;

import java.nio.charset.*;

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

	public static void mock(ClientAndServer mockServer, String uri, String json, Parameter... queryParameters) {
		mockServer.clear(null);
		mockServer
				.when(request().withMethod("GET").withPath(uri).withQueryStringParameters(queryParameters))
				.respond(response()
						.withStatusCode(200)
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody(json, StandardCharsets.UTF_8)
				);
	}

	public static void mockError(ClientAndServer mockServer, HttpResponseStatus status) {
		String body = "{" +
				" \"status\": " + status.code() + "," +
				" \"message\": \"" + status.reasonPhrase() + "\"," +
				" \"error\": \"" + status.reasonPhrase() + "\"," +
				" \"type\": \"ErrorException\"" +
				" }";

		mockServer.clear(null);
		mockServer
				.when(request())
				.respond(response()
						.withStatusCode(status.code())
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody(body, StandardCharsets.UTF_8)
				);
	}

}
