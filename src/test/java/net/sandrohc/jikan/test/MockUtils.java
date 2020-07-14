package net.sandrohc.jikan.test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockUtils {

	public static final String MOCK_HOST = "127.0.0.1:8080";

	public static ClientAndServer createMockServer() {
		return ClientAndServer.startClientAndServer(8080);
	}

	public static void mock(ClientAndServer mockServer, String uri, String json) {
		mockServer
				.when(request().withMethod("GET").withPath(uri))
				.respond(response()
						.withStatusCode(200)
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody(json, StandardCharsets.UTF_8)
						.withDelay(TimeUnit.MILLISECONDS, 500)
				);
	}

	public static void mockAccessDenied(ClientAndServer mockServer) {
		mockServer
				.when(request().withMethod("GET"))
				.respond(response()
						.withStatusCode(401) // TODO
						.withHeader(new Header(HttpHeaderNames.CONTENT_TYPE.toString(), HttpHeaderValues.APPLICATION_JSON.toString()))
						.withBody("{ message: 'incorrect username and password combination' }") // TODO
						.withDelay(TimeUnit.MILLISECONDS, 500)
				);
	}

}
