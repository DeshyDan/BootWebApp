package com.tutorial.bootwebapp;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

public class BookRouteConfig {
    record Book(String name, String author) {
    }

    public RouterFunction<ServerResponse> routerFunction() {

        BookHandler bookHandler = new BookHandler();
        return RouterFunctions.route()
                .GET("/api/v1/persons/", bookHandler::getAllBooks)
                .build();
    }
}
