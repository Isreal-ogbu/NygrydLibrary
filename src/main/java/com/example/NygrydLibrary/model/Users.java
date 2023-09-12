package com.example.NygrydLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "library_users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    @NotNull
    @NotBlank
    @Length(min = 6, max = 15, message = "Full name cannot be greater than 15, or less than 6")
    private String fullName;

    @NonNull
    @NotNull
    @NotBlank
    @Email(message = "User must enter a valid email address")
    private String email;
    @NonNull
    @NotBlank
    @NotEmpty
    private String address;
    @NonNull
    @Min(value = 18, message = "User must be an adult")
    @Max(value = 70, message = "User cannot be older than 70")
    private int age;

    private LocalDate created = LocalDate.now();
}
