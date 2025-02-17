package com.goldrush.api.repository;

import com.goldrush.api.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByEmail(String email);
}
