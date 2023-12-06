package com.scm.studentcoursemanagement.models.repository;

import com.scm.studentcoursemanagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
