package com.example.NygrydLibrary.repository;

import com.example.NygrydLibrary.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByFullName(String fullName);

//    @Query("select e.id, e.email, e.fullName from Users e where e.fullName=null")
//    Optional<Object[]> findEmailAndFullName();
}
