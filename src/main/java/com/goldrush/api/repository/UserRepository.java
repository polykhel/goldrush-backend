package com.goldrush.api.repository;

import com.goldrush.api.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByEmail(String email);
}
