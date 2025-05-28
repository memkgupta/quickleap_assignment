package com.quickleap_assignment.user_service.repositories;

import com.quickleap_assignment.user_service.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,String> {
    Optional<Token> findByToken(String token);
}
