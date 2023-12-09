package com.scm.studentcoursemanagement.service.impl;

import com.scm.studentcoursemanagement.exceptions.RecordNotFound;
import com.scm.studentcoursemanagement.models.Course;
import com.scm.studentcoursemanagement.models.Student;
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
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        }else {
            throw new RecordNotFound("Course with the provided id does not exist.");
        }
    }

    @Override
    public List<Course> getCourses() {

        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course) {
        Optional<Course> oldCourse = courseRepository.findById(course.getId());
        if(oldCourse.isPresent()){
            return courseRepository.save(course);
        }else {
            throw new RecordNotFound("Course with the provided id does not exist.");
        }
    }

    @Override
    public void deleteCourse(Long id) {
        Course oldCourse = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(oldCourse);
    }
}
