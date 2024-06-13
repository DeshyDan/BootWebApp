package com.tutorial.bootwebapp.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    private List<Book> books = new ArrayList<>();

    {
        books.add(new Book("Design patterns", "Jonh"));
        books.add(new Book("Loops", "Steve"));
        books.add(new Book("While", "Paul"));
        books.add(new Book("Java", "Paul"));
    }


    public List<Book> getBooks() {
        return books;
    }
}
