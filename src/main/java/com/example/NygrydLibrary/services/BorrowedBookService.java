package com.example.NygrydLibrary.services;

import com.example.NygrydLibrary.model.Book;
import com.example.NygrydLibrary.model.BorrowedBooks;
import com.example.NygrydLibrary.model.Users;
import com.example.NygrydLibrary.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Configuration
public class BorrowedBookService {
    private final BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository){
        this.borrowedBookRepository = borrowedBookRepository;
    }
    public ResponseEntity<String> borrowedBook(BorrowedBooks borrowedBooks){
        Users user = userService.getAUser(borrowedBooks.getUserId().getId());
        ResponseEntity<Book> book = bookService.getABookDetailByAuthorAndBook(borrowedBooks.getTitle(), borrowedBooks.getAuthor());
        Optional<BorrowedBooks> bookState = borrowedBookRepository.findBorrowedBooksByUserIdAndTitleAndAuthor(borrowedBooks.getUserId(), borrowedBooks.getTitle(), borrowedBooks.getAuthor());
        if(bookState.isPresent()){
            return ResponseEntity.ok("This book has already been borrowed");
        }
        borrowedBooks.setBorrowed(true);
        borrowedBookRepository.save(borrowedBooks);
        return ResponseEntity.ok("Congratulation, you borrowed a book");
    }
}
