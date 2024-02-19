package com.fakestore.user.controllers;

import com.fakestore.user.dto.*;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import com.fakestore.user.models.Token;
import com.fakestore.user.models.User;
import com.fakestore.user.services.UserService;
import jakarta.annotation.Nonnull;
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

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpDto> addUser(@RequestBody UserSignUpDto userSignUpDetails){
        User user = userService.signUpUser(userSignUpDetails.getName(),
                userSignUpDetails.getEmail(), userSignUpDetails.getHashedPassword(),
                userSignUpDetails.getPhone(), userSignUpDetails.getAddress());
        UserSignUpDto userSignUpDto = new UserSignUpDto();
        userSignUpDto.setName(user.getName());
        userSignUpDto.setEmail(user.getEmail());
        userSignUpDto.setPhone(user.getPhone());
        return new ResponseEntity<>(userSignUpDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody UserLoginDto userLoginDto) throws UserNotFoundException {
        Token token = userService.loginUser(userLoginDto.getEmail(), userLoginDto.getPassword());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUserEmail(token.getUser().getEmail());
        loginResponseDto.setUserName(token.getUser().getName().getFirstName());
        loginResponseDto.setToken(token.getValue());
        loginResponseDto.setExpiryAt(token.getExpiryAt());
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody LogoutRequestDto requestDto) throws RuntimeException{
        userService.logoutUser(requestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<UserDto> validateToken(@PathVariable("token") @Nonnull String token){
        return new ResponseEntity<>(UserDto.from(userService.validateToken(token)), HttpStatus.OK);
    }
}