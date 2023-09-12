package com.example.NygrydLibrary.services;

import com.example.NygrydLibrary.exception.LibraryException;
import com.example.NygrydLibrary.model.Users;
import com.example.NygrydLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Cacheable("getAllUser")
    public ResponseEntity<List<Users>> getAllPowerUser(){
        List<Users> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    @CacheEvict(value = {"getAllUser"}, allEntries = true)
    public ResponseEntity<Users> addUser(Users user){
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
    @Cacheable("getUserById")
    public Users getAUser(Integer id){
        String msg = String.format("User with the Id: %s does not exist", id);
        return userRepository.findById(id).orElseThrow(() -> new LibraryException(msg));
    }
    @Cacheable("getUserByEmail")
    public ResponseEntity<Users> getAUserByEmail(String email) {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new LibraryException("User not found"));
        return ResponseEntity.ok(user);
    }
    @Cacheable("getUserByFullName")
    public ResponseEntity<Users> getAUserFullName(String fullName) {
        Users user = userRepository.findByFullName(fullName).orElseThrow(() -> new LibraryException("User not found"));
        return ResponseEntity.ok(user);
    }
    @CacheEvict(value = "getUserById", key = "#id",allEntries = true)
    public ResponseEntity<String> updateUser(Integer id, Users user1 ){
        Users user = userRepository.findById(id).orElseThrow(() -> new LibraryException("User not found"));
        user.setFullName(user1.getFullName());
        user.setAddress(user1.getAddress());
        user.setAge(user1.getAge());
        user.setEmail(user1.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok("User successfully updated");
    }
    @CacheEvict(value = { "getUserById"}, key = "#id", allEntries = true)
    public ResponseEntity<String> deleteUser(Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("User successfully deleted");
    }
}
