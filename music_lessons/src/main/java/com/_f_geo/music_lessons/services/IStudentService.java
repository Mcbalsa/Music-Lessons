package com._f_geo.music_lessons.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Lesson;
import com._f_geo.music_lessons.models.Student;
import com._f_geo.music_lessons.models.User;


@Service
public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(String studentId);
    Student createStudent(Student student);
    Student updateStudent(Student updatedStudent);
    boolean deleteById(String studentId);
    List <User> getTeachers(String StudentId);
    List<Lesson> getLessonsById(String StudentId) throws Exception;
    List<Student> getAssignedStudents(String userId);
    Student assignTeacher(String studentId, String teacherId);
    Student unassignTeacher(String studentId, String teacherId);
    Student setInactive(String studentId);
    Student setActive(String studentId);
}

