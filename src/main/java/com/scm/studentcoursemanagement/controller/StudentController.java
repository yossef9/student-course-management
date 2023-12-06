package com.scm.studentcoursemanagement.controller;

import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "")
    public ResponseEntity<Void> registerStudent(@RequestBody Student student) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(studentService.addStudent(student))
                .toUri();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getStudentById(@PathVariable("id") Long studentId) {
        Student student = new Student();
        if (studentId != null) {
            student = studentService.getById(studentId);
        }

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        //return new ResponseEntity<>(studentList, HttpStatus.OK);
        ModelAndView mv = new ModelAndView("student");
        mv.addObject("students",studentList);
        mv.addObject("student",new Student());
        mv.addObject("jwt","dn");
        return mv;

    }

    @GetMapping(value = "")
    public ModelAndView getALlStudent() {
        List<Student> studentList = studentService.getStudents();
        //return new ResponseEntity<>(studentList, HttpStatus.OK);
        ModelAndView mv = new ModelAndView("student");
        mv.addObject("students",studentList);
        mv.addObject("student",new Student());
        mv.addObject("jwt","hw");
        return mv;
    }

    @GetMapping(value = "/course/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getStudentByCourseId(@PathVariable("courseId") Long courseId) {
        List<Student> studentList = studentService.getStudentsByCourseId(courseId);
        //return new ResponseEntity<>(studentList, HttpStatus.OK);
        ModelAndView mv = new ModelAndView("student");
        mv.addObject("students",studentList);
        mv.addObject("jwt","hw");
        return mv;
    }

    @PutMapping(value = "")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(studentService.updateStudent(student))
                .toUri();
        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
