package com.example.NygrydLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private Boolean borrowed = false;

    @ManyToOne
    private Users userId;

    @NotBlank
    @NotNull
    private String author;

    @NotBlank
    @NotNull
    private String title;
}
