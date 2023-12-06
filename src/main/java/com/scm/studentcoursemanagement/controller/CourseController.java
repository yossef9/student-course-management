package com.scm.studentcoursemanagement.controller;

import com.scm.studentcoursemanagement.models.Course;
import com.scm.studentcoursemanagement.models.Student;
import com.scm.studentcoursemanagement.service.CourseService;
import com.scm.studentcoursemanagement.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtService jwt;

    @PostMapping(value = "",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerCourse(@RequestBody Course course) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(courseService.addCourse(course))
                .toUri();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getCourseById(@PathVariable("id") Long courseId) {
        Course course = new Course();
        if (courseId != null) {
            course = courseService.getById(courseId);
        }
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        //return new ResponseEntity<>(studentList, HttpStatus.OK);
        ModelAndView mv = new ModelAndView("course");
        mv.addObject("courses",courseList);
        mv.addObject("course",new Course());
        mv.addObject("jwt","ng");
        return mv;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getALlCourse() {
        List<Course> courseList = courseService.getCourses();

        ModelAndView mv = new ModelAndView("course");
        mv.addObject("courses",courseList);
        mv.addObject("course",new Course());
        mv.addObject("jwt","hg");
        return mv;
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCourse(@RequestBody Course course) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{courseId}")
                .buildAndExpand(courseService.updateCourse(course))
                .toUri();
        return ResponseEntity.status(OK).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
