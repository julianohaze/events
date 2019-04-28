package com.mycompany.events.api.rest.webhook.processor;

import com.mycompany.events.api.rest.webhook.EventType;
import org.springframework.stereotype.Component;

/**
 * @author Juliano Silva
 */
@Component
public class PingEventProcessor implements EventProcessor {

    @Override
    public void process(final String payload) {
        // do nothing
    }

    @Override
    public boolean supports(final String eventType) {
        return EventType.PING.isSameAs(eventType);
    }
}
