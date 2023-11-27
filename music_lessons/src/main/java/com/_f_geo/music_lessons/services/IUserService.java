package com._f_geo.music_lessons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.User;

@Service
public interface IUserService {
    User createNew(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    Boolean deleteById(String id);
    User login(String email, String password);
    User setInactive(User user);
    User setActive(User user);
    // User getStatus(User user);
    List<User> getAllActive();
}
