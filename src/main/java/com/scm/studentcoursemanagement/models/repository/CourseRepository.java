package com.scm.studentcoursemanagement.models.repository;

import com.scm.studentcoursemanagement.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
