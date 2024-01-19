package com.fakestore.user.services;

import com.fakestore.user.dto.UserLoginDto;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import com.fakestore.user.models.User;
import com.fakestore.user.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    public UserRepositories userRepositories;

    @Autowired
    public UserServiceImplementation(UserRepositories userRepositories){
        this.userRepositories = userRepositories;
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }

    @Override
    public User getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepositories.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found with id:" + id);
        }
        return userOptional.get();
    }

    @Override
    public User addUser(User user) {
        return userRepositories.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        User oldUser = getSingleUser(id);
        if(user.getUsername() != null){
            oldUser.setUsername(user.getUsername());
        }
        if(user.getPassword() != null){
            oldUser.setPassword(oldUser.getPassword());
        }
        if(user.getName() != null){
            oldUser.setName(user.getName());
        }
        if(user.getAddress() != null){
            oldUser.setAddress(user.getAddress());
        }
        return userRepositories.save(oldUser);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = getSingleUser(id);
        userRepositories.delete(user);
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) throws UsernamePasswordIncorrectException {
        Optional<User> userOptional = userRepositories.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        if(userOptional.isEmpty()){
            throw new UsernamePasswordIncorrectException("Username or Password is incorrect");
        }
        return "new token given";
    }


}
