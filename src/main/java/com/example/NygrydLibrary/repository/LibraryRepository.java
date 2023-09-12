package com.example.NygrydLibrary.repository;

import com.example.NygrydLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    Optional<List<Book>> findBookByAuthor(String author);
    Optional<Book> findAndRemoveBookByIsbn(String isbn);
    Optional<Book> findByTitleAndAuthor(String title, String author);
}
