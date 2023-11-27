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

import com._f_geo.music_lessons.models.Lesson;
import com._f_geo.music_lessons.models.Student;
import com._f_geo.music_lessons.models.User;
import com._f_geo.music_lessons.services.IStudentService;

import lombok.RequiredArgsConstructor;

//controller

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Student")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    IStudentService studentService;

    @PostMapping("Student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createNew(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllPerson() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return studentService.deleteById(id);
    }

    @PutMapping("Update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent (@RequestBody Student updatedStudent) {
        return studentService.updateStudent( updatedStudent);
    }

    @GetMapping("studentID")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@RequestParam String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("GetTeachers")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getTeachers(@RequestParam String studentId) {
        return studentService.getTeachers(studentId);
    }

    @GetMapping("GetAssigned")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAssignedStudents(@RequestParam String userId) {
        return studentService.getAssignedStudents(userId);
    }

    @PutMapping("AssignTeacher")
    @ResponseStatus(HttpStatus.OK)
    public Student assignTeacher (@RequestParam String studentId, @RequestParam String teacherId) {
        return studentService.assignTeacher(studentId, teacherId);
    }

    @PutMapping("UnassignTeacher")
    @ResponseStatus(HttpStatus.OK)
    public Student unassignTeacher (@RequestParam String studentId, @RequestParam String teacherId) {
        return studentService.unassignTeacher(studentId, teacherId);
    }

    @PutMapping("SetInactive")
    @ResponseStatus(HttpStatus.OK)
    public Student setInactive (@RequestParam String studentId) {
        return studentService.setInactive(studentId);
    }

    @PutMapping("SetActive")
    @ResponseStatus(HttpStatus.OK)
    public Student setActive (@RequestParam String studentId) {
        return studentService.setActive(studentId);
    }

    @GetMapping("GetLessons")
    @ResponseStatus(HttpStatus.OK)
    public List<Lesson> getLessons (@RequestParam String studentId) throws Exception {
        return studentService.getLessonsById(studentId);
    }

}
