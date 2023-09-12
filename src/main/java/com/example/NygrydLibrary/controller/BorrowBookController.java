package com.example.NygrydLibrary.controller;

import com.example.NygrydLibrary.model.BorrowedBooks;
import com.example.NygrydLibrary.services.BorrowedBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/borrow")
public class BorrowBookController {
    private final BorrowedBookService borrowedBookService;
    @Autowired
    public BorrowBookController(BorrowedBookService bookService){
        this.borrowedBookService = bookService;
    }
    @PostMapping("/")
    public ResponseEntity<String> borrowBook(@Valid @RequestBody BorrowedBooks borrowedBooks){
        return borrowedBookService.borrowedBook(borrowedBooks);
    }
}
