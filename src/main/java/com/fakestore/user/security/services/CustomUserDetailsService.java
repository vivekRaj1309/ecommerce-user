package com.fakestore.user.security.services;

import com.fakestore.user.models.User;
import com.fakestore.user.repositories.UserRepositories;
import com.fakestore.user.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepositories userRepositories;

    public CustomUserDetailsService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepositories.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new CustomUserDetails(optionalUser.get());
    }
}
