package com.example.NygrydLibrary.repository;

import com.example.NygrydLibrary.model.BorrowedBooks;
import com.example.NygrydLibrary.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBooks, Integer> {
    Optional<BorrowedBooks> findBorrowedBooksByUserIdAndTitleAndAuthor(Users userId, String title, String author);
}
