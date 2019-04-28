package com.mycompany.events.api.rest.webhook.processor;

/**
 * @author Juliano Silva
 */
public interface EventProcessor {

    void process(final String payload);

    boolean supports(final String eventType);
}
