module jikan {
	/* Dependencies */
	// Logging
	requires org.slf4j;
	// Reactive Streams & Project Reactor
	requires org.reactivestreams;
	requires reactor.core;
	requires reactor.netty.core;
	requires reactor.netty.http;
	requires io.netty.codec.http;
	// JSON deserializer - Jackson
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;

	/* Exports */
	exports net.sandrohc.jikan;
	exports net.sandrohc.jikan.exception;
	exports net.sandrohc.jikan.factory;
	exports net.sandrohc.jikan.model;
	exports net.sandrohc.jikan.query;
}