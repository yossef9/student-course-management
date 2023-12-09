package com.scm.studentcoursemanagement.exceptions;

public class BadBodyRequest extends RuntimeException {
    public BadBodyRequest(String message) {
        super(message);
    }
}
