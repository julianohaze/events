package com.mycompany.events.api.rest.webhook.processor;

import com.mycompany.events.api.rest.exception.EventNotSupportedException;
import com.mycompany.events.api.rest.webhook.processor.command.ProcessEventCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Juliano Silva
 */
@Component
@RequiredArgsConstructor
public class EventManager {

    private final List<EventProcessor> processors;

    public void process(final ProcessEventCommand command) {
        final EventProcessor processor = getProcessorFor(command.getEventType());
        processor.process(command.getPayload());
    }

    private EventProcessor getProcessorFor(final String eventType) {
        return processors
                .stream()
                .filter(p -> p.supports(eventType))
                .findFirst()
                .orElseThrow(() -> new EventNotSupportedException(eventType));
    }
}
