package com.scm.studentcoursemanagement.service.impl;

import com.scm.studentcoursemanagement.exceptions.RecordNotFound;
import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.models.repository.StudentRepository;
import com.scm.studentcoursemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else {
            throw new RecordNotFound("Student with the provided id does not exist.");
        }
    }

    @Override
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> oldStudent = studentRepository.findById(student.getId());
        if(oldStudent.isPresent()){
            return studentRepository.save(student);
        }else {
            throw new RecordNotFound("Student with the provided id does not exist.");
        }
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
