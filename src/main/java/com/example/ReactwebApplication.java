package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReactwebApplication {

	public static void main(String[] args) throws InterruptedException {
		HandlerFunction handlerFunction = request -> ServerResponse.ok()
				.body(Mono.just("Hello"),String.class);

		RouterFunction router = route(GET("/"),handlerFunction);

		HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);

		HttpServer.create("localhost",8080)
				.newHandler(new ReactorHttpHandlerAdapter(httpHandler))
				.block();
		Thread.currentThread().join();
	}
}
