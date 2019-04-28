package com.mycompany.events.api.rest.webhook;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Juliano Silva
 */
@RequiredArgsConstructor
public enum EventType {

    PING("ping"),
    ISSUES("issues");

    @Getter
    private final String id;

    public boolean isSameAs(final String eventType) {
        return this.id.equalsIgnoreCase(eventType);
    }
}
