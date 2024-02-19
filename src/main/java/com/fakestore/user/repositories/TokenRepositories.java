package com.fakestore.user.repositories;

import com.fakestore.user.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepositories extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByValueAndDeleted(String token, boolean isDeleted);
    Optional<Token> findByValueAndDeletedEqualsAndExpiryGreaterThan(String token, boolean deleted,Date date);
}
