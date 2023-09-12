package com.example.NygrydLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "author","pub_year", "edition"}))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int book_id;

    @NotNull
    @NotBlank
    private String author;

    @NotBlank
    @Length(max = 15, message = "Message should not be greater than 15. Be consist")
    private String title;

    @Column(name = "pub_year")
    @NotNull
    private int publicationYear;

    @NotNull
    @NotNull
    @Length(min=10, max = 13)
    private String isbn;

    @NotNull
    @NotBlank
    public String edition;
    public String category;
}
