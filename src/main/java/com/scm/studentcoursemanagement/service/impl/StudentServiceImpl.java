package com.scm.studentcoursemanagement.service.impl;

import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.models.repository.StudentRepository;
import com.scm.studentcoursemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Long addStudent(Student student) {
        System.out.println("adding "+student.getId());
        Student saved = studentRepository.save(student);
        return saved.getId();
    }

    @Override
    public Student getById(Long id) {

        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        Student oldStudent = studentRepository.findById(student.getId()).orElseThrow();

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        Student oldStudent = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(oldStudent);
    }
}
