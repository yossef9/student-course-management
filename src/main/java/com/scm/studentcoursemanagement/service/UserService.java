package com.scm.studentcoursemanagement.service;

import com.scm.studentcoursemanagement.models.User;

public interface UserService {

    Long register(User user);

    String generateToken(String username);
}
