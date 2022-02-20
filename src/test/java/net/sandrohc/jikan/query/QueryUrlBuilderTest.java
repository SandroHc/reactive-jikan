/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;

import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.enums.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueryUrlBuilderTest extends QueryTest {

	@Test
	void emptyUrl() throws JikanUrlException {
		String url = QueryUrlBuilder.create().build();
		assertThat(url).as("empty URL").isEqualTo("");
	}

	@Test
	void invalidUrl() {
		assertThatThrownBy(() -> QueryUrlBuilder.create().path("//").build()).isInstanceOf(JikanUrlException.class);
	}

	@Test
	void absolutePath() throws JikanUrlException {
		String url = QueryUrlBuilder.create().path("/example").build();
		assertThat(url).as("absolute path").isEqualTo("/example");
	}

	@Test
	void absolutePathWithProtocol() throws JikanUrlException {
		String url = QueryUrlBuilder.create().path("https://google.com").build();
		assertThat(url).as("absolute path with protocol").isEqualTo("https://google.com");
	}

	@Test
	void relativePath() throws JikanUrlException {
		String url = QueryUrlBuilder.create().path("example").build();
		assertThat(url).as("relative path").isEqualTo("example");
	}

	@Test
	void pathWithQueryParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().path("example").param("key", "value").build();
		assertThat(url).as("path with query param").isEqualTo("example?key=value");
	}

	@Test
	void queryParamOnly() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("key", "value").build();
		assertThat(url).as("query param only").isEqualTo("?key=value");
	}

	@Test
	void multipleQueryParams() throws JikanUrlException {
		String url = QueryUrlBuilder.create()
				.param("key1", "value1")
				.param("key2", "value2").build();
		assertThat(url).as("multiple query params").isEqualTo("?key1=value1&key2=value2");
	}

	@Test
	void emptyParamValue() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("empty", "").build();
		assertThat(url).as("empty param value").isEqualTo("?empty=");
	}

	@Test
	void nullParamValue() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("null", null).build();
		assertThat(url).as("null param value").isEqualTo("");
	}

	@Test
	void listParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("list", Arrays.asList(1, 2)).build();
		assertThat(url).as("list param").isEqualTo("?list[]=1,2");
	}

	@Test
	void objectListParam() throws JikanUrlException {
		Object obj1 = new Object() {
			@Override
			public String toString() {
				return "a";
			}
		};

		Object obj2 = new Object() {
			@Override
			public String toString() {
				return "b";
			}
		};

		Object obj3 = new Object() {
			@Override
			public String toString() {
				return null;
			}
		};

		String url = QueryUrlBuilder.create().param("list", Arrays.asList(obj1, obj2, obj3, null)).build();
		assertThat(url).as("object list param").isEqualTo("?list[]=a,b,null,null");
	}

	@Test
	void intArrayParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("array", new int[] { 1, 2 }).build();
		assertThat(url).as("int array param").isEqualTo("?array[]=1,2");
	}

	@Test
	void longArrayParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("array", new long[] { 1L, 2L }).build();
		assertThat(url).as("long array param").isEqualTo("?array[]=1,2");
	}

	@Test
	void doubleArrayParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("array", new double[] { 1.1D, 1.2D }).build();
		assertThat(url).as("double array param").isEqualTo("?array[]=1.1,1.2");
	}

	@Test
	void objectArrayParam() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("array", new Object[] { "a", "b" }).build();
		assertThat(url).as("object array param").isEqualTo("?array[]=a,b");
	}

	@Test
	void encodeSpace() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("encode", "test space").build();
		assertThat(url).as("encode space").isEqualTo("?encode=test%20space");
	}

	@Test
	void encodePercent() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("encode", "%").build();
		assertThat(url).as("encode percent").isEqualTo("?encode=%25");
	}

	@Test
	void encodeSpecialCharacters() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("encode", "áàâã").build();
		assertThat(url).as("encode special characters").isEqualTo("?encode=áàâã");
	}

	@Test
	void encodeKanjiCharacters() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("encode", "世界").build();
		assertThat(url).as("encode kanji").isEqualTo("?encode=世界");
	}

	@Test
	void map() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("enum", Type.ANIME, Enum::name).build();
		assertThat(url).as("map enum").isEqualTo("?enum=ANIME");
	}

	@Test
	void mapNull() throws JikanUrlException {
		String url = QueryUrlBuilder.create().param("enum", (Enum<?>) null, Enum::name).build();
		assertThat(url).as("map null value - check for NPE").isEqualTo("");
	}
}
