package com.fakestore.user.services;

import com.fakestore.user.dto.UserLoginDto;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import com.fakestore.user.models.Address;
import com.fakestore.user.models.Name;
import com.fakestore.user.models.Token;
import com.fakestore.user.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getSingleUser(Long id) throws UserNotFoundException;
    User updateUser(Long id, User user) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
    Token loginUser(String email, String password) throws UserNotFoundException;
    User signUpUser(Name name, String email, String hashedPassword, String phone, Address address);
    void logoutUser(String token);
}
