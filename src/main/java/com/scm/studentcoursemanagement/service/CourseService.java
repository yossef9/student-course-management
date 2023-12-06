package com.scm.studentcoursemanagement.service;

import com.scm.studentcoursemanagement.models.Course;
import com.scm.studentcoursemanagement.service.impl.CourseServiceImpl;

import java.util.List;

public interface CourseService {

    Long addCourse(Course course);

    Course getById(Long id);

    List<Course> getCourses();

    Course updateCourse(Course course);

    void deleteCourse(Long id);

}
