package com.softgen.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateMembershipException extends RuntimeException {
    public DuplicateMembershipException(String message) {
        super(message);
    }

    public DuplicateMembershipException(String message, Throwable cause) {
        super(message, cause);
    }
}