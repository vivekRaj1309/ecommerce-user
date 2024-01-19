package com.fakestore.user.controllers;

import com.fakestore.user.dto.LoginResponseDto;
import com.fakestore.user.dto.UserLoginDto;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import com.fakestore.user.models.User;
import com.fakestore.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getSingleUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserLoginDto userLoginDto) throws UsernamePasswordIncorrectException {
        String token = userService.loginUser(userLoginDto);
        return new ResponseEntity<>(new LoginResponseDto(token), HttpStatus.OK);
    }
}
