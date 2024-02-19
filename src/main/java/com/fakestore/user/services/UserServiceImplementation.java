package com.fakestore.user.services;

import com.fakestore.user.exceptions.EmailPasswordIncorrectException;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.models.Address;
import com.fakestore.user.models.Name;
import com.fakestore.user.models.Token;
import com.fakestore.user.models.User;
import com.fakestore.user.repositories.TokenRepositories;
import com.fakestore.user.repositories.UserRepositories;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    public UserRepositories userRepositories;
    public TokenRepositories tokenRepositories;
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImplementation(TokenRepositories tokenRepositories, UserRepositories userRepositories, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepositories = userRepositories;
        this.tokenRepositories = tokenRepositories;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public User updateUser(Long id, User user) throws UserNotFoundException {
        User oldUser = getSingleUser(id);
        if(user.getHashedPassword() != null){
            oldUser.setHashedPassword(oldUser.getHashedPassword());
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
    public Token loginUser(String email, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepositories.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Email not found");
        }
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new EmailPasswordIncorrectException("Email or Password incorrect");
        }
        Token token = getToken(user);

        return tokenRepositories.save(token);
    }

    private static Token getToken(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setDeleted(false);
        token.setUser(user);
        token.setExpiryAt(expiryDate);
        return token;
    }

    @Override
    public User signUpUser(Name name, String email, String hashedPassword, String phone, Address address) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(hashedPassword));
        user.setPhone(phone);
        return userRepositories.save(user);
    }

    @Override
    public void logoutUser(String token) throws RuntimeException{
        Optional<Token> optionalToken = tokenRepositories.findTokenByValueAndDeleted(token, false);
        if(optionalToken.isEmpty()){
            throw new RuntimeException("Already Logged Out");
        }
        Token retrievedToken = optionalToken.get();
        retrievedToken.setDeleted(true);
        tokenRepositories.save(retrievedToken);
    }

    @Override
    public User validateToken(String token) throws RuntimeException {
        Optional<Token> tokenOptional = tokenRepositories.findByValueAndDeletedEqualsAndExpiryGreaterThan(token, false, new Date());
        if(tokenOptional.isEmpty()){
            throw new RuntimeException("Token expired, please login again");
        }
        return tokenOptional.get().getUser();
    }
}