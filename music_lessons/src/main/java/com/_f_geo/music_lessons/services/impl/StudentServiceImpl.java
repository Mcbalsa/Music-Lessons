package com._f_geo.music_lessons.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._f_geo.music_lessons.models.Lesson;
import com._f_geo.music_lessons.models.Student;
import com._f_geo.music_lessons.models.User;
import com._f_geo.music_lessons.repository.LessonRepository;
import com._f_geo.music_lessons.repository.StudentRepository;
import com._f_geo.music_lessons.repository.UserRepository;
import com._f_geo.music_lessons.services.IStudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {
    // implementation
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(
                Student.builder()
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .guardName(student.getGuardName())
                        .DOB(student.getDOB())
                        .instruments(student.getInstruments())
                        .teachers(student.getTeachers())
                        .phoneNum(student.getPhoneNum())
                        .status(student.getStatus())
                        .address(student.getAddress())
                        .creditCard(student.getCreditCard())
                        .dateLeft(null)
                        .build()

        );
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        if (existingStudent.isEmpty())
            throw new RuntimeException(String.format("No student found for id %s", student.getId()));
        existingStudent.get().setFirstName(student.getFirstName());
        existingStudent.get().setLastName(student.getLastName());
        existingStudent.get().setGuardName(student.getGuardName());
        existingStudent.get().setDOB(student.getDOB());
        existingStudent.get().setInstruments(student.getInstruments());
        existingStudent.get().setTeachers(student.getTeachers());
        existingStudent.get().setPhoneNum(student.getPhoneNum());
        existingStudent.get().setStatus(student.getStatus());
        existingStudent.get().setDateLeft(student.getDateLeft());
        existingStudent.get().setAddress(student.getAddress());
        existingStudent.get().setCreditCard(student.getCreditCard());
        return studentRepository.save(existingStudent.get());
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students;
    }

    @Override
    public boolean deleteById(String id) {
        studentRepository.deleteById(id);
        if (studentRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Student getStudentById(String id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty())
            throw new RuntimeException(String.format("No student found for username %s", id));
        return existingStudent.get();
    }

    @Override
    public List<User> getTeachers(String StudentId) {
        Optional<Student> existingStudent = studentRepository.findById(StudentId);
        if (existingStudent.isEmpty())
            throw new RuntimeException(String.format("No student found for username %s", StudentId));
        List<User> teachers = new ArrayList();
        for (String userId : existingStudent.get().getTeachers()) {
            Optional<User> existingUser = userRepository.findById(userId);
            if (existingUser.isEmpty())
                throw new RuntimeException(String.format("No user found for id %s", userId));
            teachers.add(existingUser.get());
        }
        return teachers;
    }

    @Override
    public List<Lesson> getLessonsById(String studentId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isEmpty()) {
            throw new RuntimeException(String.format("Student not found for ID %s", studentId));
        }
        List<Lesson> lessons = lessonRepository.findAll();
        List<Lesson> studentLesson = new ArrayList();

        for (Lesson lesson : lessons) {
            if (lesson.getStudentId().equals(studentId)) {
                studentLesson.add(lesson);
            }

        }

        return studentLesson;
    }

    @Override
    public List<Student> getAssignedStudents(String userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", userId));
        List<Student> students = new ArrayList<Student>();
        for (String studentId : existingUser.get().getStudents()) {
            Optional<Student> existingStudent = studentRepository.findById(studentId);
            if (existingStudent.isEmpty())
                throw new RuntimeException(String.format("No student found for username %s", studentId));
            students.add(existingStudent.get());
        }
        return students;
    }

    @Override
    public Student assignTeacher(String studentId, String teacherId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isEmpty()) {
            throw new RuntimeException(String.format("Student not found for ID %s", studentId));
        }
        if (existingStudent.get().getTeachers() == null || existingStudent.get().getTeachers().isEmpty()) {
            List<String> teachers = new ArrayList<String>();
            teachers.add(teacherId);
            existingStudent.get().setTeachers(teachers);
        } else {
            existingStudent.get().getTeachers().add(teacherId);
        }

        Optional<User> existingUser = userRepository.findById(teacherId);
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", teacherId));
        existingUser.get().getStudents().add(studentId);
        if (existingUser.get().getStudents() == null || existingUser.get().getStudents().isEmpty()) {
            List<String> students = new ArrayList<String>();
            students.add(studentId);
            existingUser.get().setStudents(students);
        } else {
            existingUser.get().getStudents().add(studentId);
        }
        userRepository.save(existingUser.get());
        return studentRepository.save(existingStudent.get());
    }

    @Override
    public Student unassignTeacher(String studentId, String teacherId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isEmpty()) {
            throw new RuntimeException(String.format("Student not found for ID %s", studentId));
        }
        existingStudent.get().getTeachers().remove(teacherId);
        removeFromTeacher(studentId, teacherId);

        return studentRepository.save(existingStudent.get());
    }

    @Override
    public Student setInactive(String studentId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isEmpty())
            throw new RuntimeException(String.format("No student found for id %s", studentId));
        for(String teacherId: existingStudent.get().getTeachers()) {
            removeFromTeacher(studentId, teacherId);
            existingStudent.get().getTeachers().remove(teacherId);
        }
        existingStudent.get().setDateLeft(LocalDate.now());
        existingStudent.get().setStatus("INACTIVE");
        return studentRepository.save(existingStudent.get());
    }

    @Override
    public Student setActive(String studentId) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if (existingStudent.isEmpty())
            throw new RuntimeException(String.format("No student found for id %s", studentId));
        existingStudent.get().setStatus("ACTIVE");
        existingStudent.get().setDateLeft(null);
        return studentRepository.save(existingStudent.get());
    }

    private void removeFromTeacher(String studentId, String teacherId) {
        Optional<User> existingUser = userRepository.findById(teacherId);
        if (existingUser.isEmpty())
            throw new RuntimeException(String.format("No user found for id %s", teacherId));
        existingUser.get().getStudents().remove(studentId);
        userRepository.save(existingUser.get());
    }

}
