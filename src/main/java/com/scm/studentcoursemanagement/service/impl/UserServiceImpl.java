package com.scm.studentcoursemanagement.service.impl;

import com.scm.studentcoursemanagement.models.User;
import com.scm.studentcoursemanagement.models.repository.UserRepository;
import com.scm.studentcoursemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Long register(User user) {

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()){

            throw new RuntimeException(String.format("User already exist with username %s.",user.getUsername()));
        }

        //encrypting user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return null;
    }

    @Override
    public String generateToken(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }
}
