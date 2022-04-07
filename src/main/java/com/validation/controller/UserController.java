package com.validation.controller;


import com.validation.dto.UserRequest;
import com.validation.entity.User;
import com.validation.exception.UserNotFoundException;
import com.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest request) {
        return new ResponseEntity<>(service.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.getUser(id));
    }
}
