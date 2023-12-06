package com.scm.studentcoursemanagement.service;


import com.scm.studentcoursemanagement.models.Student;

import java.util.List;

public interface StudentService {

    Long addStudent(Student student);

    Student getById(Long id);

    List<Student> getStudents();

    Student updateStudent(Student student);

    List<Student> getStudentsByCourseId(Long courseId);

    void deleteStudent(Long id);
}
