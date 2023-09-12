package com.example.NygrydLibrary.controller;

import com.example.NygrydLibrary.exception.LibraryException;
import com.example.NygrydLibrary.model.Book;
import com.example.NygrydLibrary.repository.LibraryRepository;
import com.example.NygrydLibrary.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class LibraryController {
    private final BookService bookService;

    @Autowired
    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        return bookService.getABookDetailById(id);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        return bookService.getABookDetailByAuthor(author);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> getBook(@PathVariable String title) {
        return bookService.getABookDetailTitle(title);
    }

    @PostMapping("/")
    public ResponseEntity<String> postBook(@RequestBody @Valid Book book) {
        return bookService.postBook(book);
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<String> deleteBookByIsbn(@PathVariable String isbn) {
        return bookService.deleteBookByIsbn(isbn);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllBook() {
        return bookService.deleteAllBooks();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
}
