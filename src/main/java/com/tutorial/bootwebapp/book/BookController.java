package com.tutorial.bootwebapp.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books/")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);

    }

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.getBooks();

    }

    @GetMapping("/{author}")
    public List<Book> getBookByAuthor(@PathVariable("author") String author) {
        return bookService.getBookByAuthor(author);
    }

    @PutMapping("/{book}")
    public void updateBookByName(@PathVariable("book") String book, @RequestBody BookUpdateRequest request) {

        bookService.updateBookByName(book, request.name());
    }

    @DeleteMapping("/{name}")
    public void deleteBookByName(@PathVariable("name") String bookName) {
        bookService.deleteBookByName(bookName);

    }


}
