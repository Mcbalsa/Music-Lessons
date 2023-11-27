package com._f_geo.music_lessons.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com._f_geo.music_lessons.models.User;
import com._f_geo.music_lessons.services.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("New")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNew(@RequestBody User user) {
        return userService.createNew(user);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("GetAllActive")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getActiveTeachersWithStudents() {
        return userService.getAllActive();
    }

    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deleteById(@RequestParam String id) {
        return userService.deleteById(id);
    }

    @PostMapping("Login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestParam String email, @RequestParam String pass) {
        return userService.login(email, pass);
    }
    
    @PutMapping ("Inactive")
    @ResponseStatus(HttpStatus.OK)
    public User setInactive(@RequestBody User user) {
        return userService.setInactive(user);
    }

    @PutMapping ("Active")
    @ResponseStatus(HttpStatus.OK)
    public User setActive(@RequestBody User user) {
        return userService.setActive(user);
    }
}
