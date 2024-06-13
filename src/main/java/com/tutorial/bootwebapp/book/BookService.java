package com.tutorial.bootwebapp.book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        System.out.println(book);
        bookRepository.getBooks().add(book);
    }

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.getBooks().stream()
                .filter(b -> b.getAuthor().equals(author))
                .collect(Collectors.toList());

    }

    public void updateBookByName(String oldName, String newName) {
        for (var book : bookRepository.getBooks()) {
            if (book.getName().equals(oldName)) {

                book.setName(newName);
            }
        }
    }

    public void deleteBookByName(String name) {
        bookRepository.getBooks().removeIf(book -> book.getName().equals(name));
    }


}
