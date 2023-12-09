package com.scm.studentcoursemanagement.exceptions;

public class RecordAlreadyExists extends RuntimeException {

    public RecordAlreadyExists(String message) {
        super(message);
    }
}
