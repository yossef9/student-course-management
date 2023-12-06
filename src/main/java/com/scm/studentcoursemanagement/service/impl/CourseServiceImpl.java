package com.scm.studentcoursemanagement.service.impl;

import com.scm.studentcoursemanagement.models.Course;
import com.scm.studentcoursemanagement.models.repository.CourseRepository;
import com.scm.studentcoursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Long addCourse(Course course) {
        Course saved = courseRepository.save(course);
        return saved.getId();
    }

    @Override
    public Course getById(Long id) {

        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getCourses() {

        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course) {
        courseRepository.findById(course.getId()).orElseThrow();

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course oldCourse = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(oldCourse);
    }
}
