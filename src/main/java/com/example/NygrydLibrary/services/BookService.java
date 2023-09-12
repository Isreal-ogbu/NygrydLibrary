package com.example.NygrydLibrary.services;

import com.example.NygrydLibrary.exception.LibraryException;
import com.example.NygrydLibrary.model.Book;
import com.example.NygrydLibrary.repository.BorrowedBookRepository;
import com.example.NygrydLibrary.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
public class BookService {
    private final LibraryRepository libraryRepository;

    @Autowired
    public BookService(LibraryRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }

    @Cacheable(value = "getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = libraryRepository.findAll().stream().sorted((b1,b2)-> b1.getTitle().compareTo(b2.getTitle())).toList();
        return ResponseEntity.ok(allBooks);
    }
    @CacheEvict(value = {"getAllBooks"})
    public ResponseEntity<String> postBook(Book book){
        libraryRepository.save(book);
        return ResponseEntity.ok("Book successfully posted");
    }
    @Cacheable(cacheNames = "getABookDetails", key = "#id")
    public ResponseEntity<Book> getABookDetailById(Integer id){
        Book book = libraryRepository.findById(id).orElseThrow(()-> new LibraryException("Book with the id does not exist"));
        return ResponseEntity.ok(book);
    }
    @Cacheable(cacheNames = "getABookDetailByTitle", key = "#title")
    public ResponseEntity<Book> getABookDetailTitle(String title){
        Book book = libraryRepository.findByTitle(title).orElseThrow(()-> new LibraryException("Book with the title does not exist"));
        return ResponseEntity.ok(book);
    }
    @Cacheable(cacheNames = "getABookDetailByAuthor", key = "#author")
    public ResponseEntity<List<Book>> getABookDetailByAuthor(String author){
        List<Book> book = libraryRepository.findBookByAuthor(author).orElseThrow(()-> new LibraryException("Book with the author does not exist"));
        return ResponseEntity.ok(book);
    }
    @CacheEvict(value = {"getAllBooks", "getABookDetails"}, key = "#id")
    public ResponseEntity<String> deleteBook(int id){
        libraryRepository.deleteById(id);
        return ResponseEntity.ok("Deleted book for the library");
    }
    @CacheEvict(value = {"getAllBooks", "getABookDetails"})
    public ResponseEntity<String> deleteBookByIsbn(String isbn){
        libraryRepository.findAndRemoveBookByIsbn(isbn);
        return ResponseEntity.ok("Deleted book for the library");
    }
    @CacheEvict(value = {"getAllBooks", "getABookDetails"})
    public ResponseEntity<String> deleteAllBooks(){
        libraryRepository.deleteAll();
        return ResponseEntity.ok("Deleted all resource from the library");
    }

    @Cacheable(cacheNames = "getABookDetailByAuthorAndtitle")
    public ResponseEntity<Book> getABookDetailByAuthorAndBook(String title, String author){
        Book book = libraryRepository.findByTitleAndAuthor(title, author).orElseThrow(()-> new LibraryException("Book with the author and name does not exist"));
        return ResponseEntity.ok(book);
    }
}
