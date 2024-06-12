package com.tutorial.bootwebapp;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

public class BookHandler {

    public ServerResponse getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(
                List.of(new BookRouteConfig.Book("Hello", "Deshy"))
        );
    }
}
