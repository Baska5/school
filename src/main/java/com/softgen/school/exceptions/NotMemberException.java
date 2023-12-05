package com.softgen.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotMemberException extends RuntimeException {
    public NotMemberException(String message) {
        super(message);
    }

    public NotMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}