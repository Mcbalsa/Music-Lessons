package com._f_geo.music_lessons.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com._f_geo.music_lessons.models.Student;
import com._f_geo.music_lessons.models.User;
import com._f_geo.music_lessons.repository.StudentRepository;
import com._f_geo.music_lessons.repository.UserRepository;
import com._f_geo.music_lessons.services.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public User createNew(User user) {
        return userRepository.save(
                User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNum(user.getPhoneNum())
                        .dob(user.getDob())
                        .role(user.getRole())
                        .genres(user.getGenres())
                        .instruments(user.getInstruments())
                        .students(user.getStudents())
                        .status(user.getStatus())
                        .bio(user.getBio())
                        .photoUrl(user.getPhotoUrl())
                        .dateleft(null)
                        .build());
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", user.getId()));
        existingUser.get().setFirstName(user.getFirstName());
        existingUser.get().setLastName(user.getLastName());
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPassword(user.getPassword());
        existingUser.get().setPhoneNum(user.getPhoneNum());
        existingUser.get().setDob(user.getDob());
        existingUser.get().setRole(user.getRole());
        existingUser.get().setGenres(user.getGenres());
        existingUser.get().setInstruments(user.getInstruments());
        existingUser.get().setStudents(user.getStudents());
        existingUser.get().setStatus(user.getStatus());
        existingUser.get().setBio(user.getBio());
        existingUser.get().setPhotoUrl(user.getPhotoUrl());
        existingUser.get().setDateleft(user.getDateleft());

        return userRepository.save(existingUser.get());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Boolean deleteById(String id) {
        userRepository.deleteById(id);
        if (userRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (existingUser.get().getPassword().equals(password))
            return existingUser.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public User setInactive(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", user.getId()));
        existingUser.get().setDateleft(LocalDate.now());
        existingUser.get().setStatus(user.getStatus());
        if (existingUser.get().getStudents().size() > 0)
            removeTeacherFromStudents(existingUser.get().getStudents(), existingUser.get().getId());
        existingUser.get().getStudents().clear();
        return userRepository.save(existingUser.get());
    }

    @Override
    public User setActive(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", user.getId()));
        existingUser.get().setDateleft(null);
        existingUser.get().setStatus(user.getStatus());
        return userRepository.save(existingUser.get());
    }

    @Override
    public List<User> getAllActive() {
        List<User> activeTeachersWithStudents = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if ("ACTIVE".equals(user.getStatus()) && user.getStudents() != null && !user.getStudents().isEmpty()) {
                activeTeachersWithStudents.add(user);
            }
        }

        return activeTeachersWithStudents;
    }

    private void removeTeacherFromStudents(List<String> students, String teacherId) {
        for (String studentId: students) {
            Optional<Student> existingStudent = studentRepository.findById(studentId);
            if (existingStudent.isEmpty())
                throw new RuntimeException(String.format("No student found for id %s", existingStudent.get().getId()));
            existingStudent.get().getTeachers().remove(teacherId);
            studentRepository.save(existingStudent.get());
        }
    }


}
