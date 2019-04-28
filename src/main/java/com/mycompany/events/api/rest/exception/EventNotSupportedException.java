package com.mycompany.events.api.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Juliano Silva
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EventNotSupportedException extends RuntimeException {

    public EventNotSupportedException(final String eventType) {
        super("The event " + eventType + " is not supported.");
    }
}
