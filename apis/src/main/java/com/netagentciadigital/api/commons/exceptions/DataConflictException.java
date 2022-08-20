package com.netagentciadigital.api.commons.exceptions;

import lombok.Getter;

@Getter
public class DataConflictException extends RuntimeException {

    private final String message;

    public DataConflictException(String message) {
        super(message);
        this.message = message;
    }

}

