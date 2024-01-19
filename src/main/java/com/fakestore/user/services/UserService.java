package com.fakestore.user.services;

import com.fakestore.user.dto.UserLoginDto;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import com.fakestore.user.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getSingleUser(Long id) throws UserNotFoundException;
    User addUser(User user);
    User updateUser(Long id, User user) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
    String loginUser(UserLoginDto userLoginDto) throws UsernamePasswordIncorrectException;
}
