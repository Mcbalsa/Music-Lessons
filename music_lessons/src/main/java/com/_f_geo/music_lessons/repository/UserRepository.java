package com._f_geo.music_lessons.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com._f_geo.music_lessons.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByEmail(String email);
}