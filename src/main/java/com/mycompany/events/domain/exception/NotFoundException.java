package com.mycompany.events.domain.exception;

/**
 * @author Juliano Silva
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }
}
