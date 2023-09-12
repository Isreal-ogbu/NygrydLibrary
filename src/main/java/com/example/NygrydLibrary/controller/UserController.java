package com.example.NygrydLibrary.controller;

import com.example.NygrydLibrary.model.Users;
import com.example.NygrydLibrary.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers(){
        return userService.getAllPowerUser();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Users getUser(@PathVariable Integer id){
        return userService.getAUser(id);
    }

    @GetMapping("/e/{email}")
    @ResponseBody
    public ResponseEntity<Users> getPowerUserByEmail(@PathVariable String email){
        return userService.getAUserByEmail(email);
    }

    @GetMapping("/f/{fullName}")
    @ResponseBody
    public ResponseEntity<Users> getPowerUserByFullName(@PathVariable String fullName){
        return userService.getAUserFullName(fullName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePowerUser( @PathVariable Integer id, @RequestBody @Valid Users user ){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePowerUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
